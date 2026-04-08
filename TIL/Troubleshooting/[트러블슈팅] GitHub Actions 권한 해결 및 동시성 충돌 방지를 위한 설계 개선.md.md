## 🤔 [트러블슈팅] GitHub Actions 권한 해결 및 동시성 충돌 방지를 위한 설계 개선

### 상황 (Context)
코드트리(Codetree) 연동 시 루트 디렉토리에 날짜별로 생성되는 문제 폴더들을 codetree/ 디렉토리 하위로 자동 이동시키기 위해 깃허브 액션(GitHub Actions) 자동화 스크립트를 구축했습니다. 이 과정에서 발생한 권한 문제와 워크플로우 간 충돌 이슈를 해결한 과정을 기록합니다.

### 문제 및 해결 과정 (Process)
#### ❌ 1단계: 권한 이슈 (Permission Denied)
- 현상: 스크립트 실행 중 git push 단계에서 Permission Denied 에러와 함께 액션이 실패함.
- 원인: 깃허브 액션의 기본 GITHUB_TOKEN 권한이 읽기 전용(Read-only)으로 설정되어 있어, 저장소에 변경 사항을 푸시할 권한이 없었음.
- 해결: YAML 파일에 쓰기 권한을 명시적으로 부여함.
  ```
  permissions:
    contents: write
  ```
#### ❌ 2단계: 동시성 충돌 (Race Condition)
- 현상: 여러 커밋을 한 번에 Push하거나 짧은 간격으로 Push가 발생할 때, 첫 번째 액션은 성공하지만 뒤따라오는 액션들이 실패함.
- 원인 분석:
  1. 첫 번째 액션: 성공적으로 폴더를 이동시키고 원격 저장소에 Push 완료.
  2. 두 번째 액션: 첫 번째 액션과 동시에 실행되었으나, 자신의 로컬 작업 환경은 첫 번째 액션이 Push 하기 전의 상태임.
  3.  충돌 발생: 두 번째 액션이 작업을 마치고 Push 하려 할 때, 원격 저장소의 상태가 이미 업데이트되어 있어 상태 불일치(Outdated)로 인해 Push가 거부됨.
  - **핵심**: 이벤트 기반(on: push) 트리거가 동시다발적인 워크플로우 실행을 유발하여 경합 상태(Race Condition)를 만든 것임.
### 최종 해결 (Final Solution)
실시간 처리가 반드시 필요한 작업이 아니라는 점에 착안하여, 이벤트 기반 방식에서 배치(Batch) 처리 방식으로 설계를 변경했습니다.

- 변경 전: `on: push` (매 커밋마다 실행)
- 변경 후: `on: schedule` (정해진 시간에 한 번 실행) 및 `workflow_dispatch` (수동 실행)
```
on:
  schedule:
    - cron: '0 15 * * *'   # 매일 한국 시간 오전 0시 정기 실행으로 충돌 원천 차단
  workflow_dispatch:       # 필요 시 수동으로 즉시 정리 가능
```

**# 최종소스**
```
name: Move Codetree folders

on:
  schedule:
    - cron: '0 15 * * *'   # 매일 자정 (한국시간)
  workflow_dispatch:       # 수동 실행 버튼도 추가

permissions:
  contents: write

jobs:
  move-folders:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3

      - name: Move codetree folders
        run: |
          mkdir -p codetree
          shopt -s dotglob
          for dir in [0-9][0-9][0-9][0-9][0-9][0-9]/ ; do
            [ -d "$dir" ] || continue
            cp -r "$dir"* codetree/"$dir" 2>/dev/null || true
            rm -rf "$dir"
          done

      - name: Commit changes
        run: |
          git config user.name "codetree-auto"
          git config user.email "codetree-auto@users.noreply.github.com"
          git add .
          git commit -m "auto: move codetree folders" || echo "No changes"
          git push origin main
```

### 의사결정 이유 (Decision)
- 데이터 정합성 확보: 작업을 순차적인 배치 방식으로 전환하여, 여러 액션이 동시에 원격 저장소 상태를 변경하려다 발생하는 충돌을 근본적으로 해결했습니다.
- 리소스 최적화: 불필요하게 잦은 액션 실행을 줄여 깃허브 액션 서버 자원을 효율적으로 사용하도록 개선했습니다.
- 안정성 우선: 실시간성보다 작업의 완결성과 안정성이 더 중요한 관리 업무임을 고려하여 스케줄러 방식을 선택했습니다.
### 배운 점 (Learning)
- CI/CD 환경에서도 웹 서비스의 동시성 문제와 유사한 경합 상태(Race Condition)가 발생할 수 있음을 체감했습니다.
- 문제 해결 시 기술적인 수정(권한 부여)도 중요하지만, 시스템의 실행 주기나 트리거 조건 자체를 변경하는 설계적 접근이 더 근본적인 해결책이 될 수 있음을 배웠습니다.
- 분산 환경에서 상태의 일관성(Consistency)을 유지하는 것이 얼마나 중요한지 다시 한번 확인하는 계기가 되었습니다.