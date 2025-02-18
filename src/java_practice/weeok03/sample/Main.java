package java_practice.weeok03.sample;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();//Main 을 인스턴스화 할 수 있는 이유는 Main 내부에 기본생성자 존재하기 때문에
        System.out.println(main.getNumber());
        System.out.println(main.getNumber());
        //위 의 2개의 값 똑같이 나오는 출력 값으로 지역변수 의 반증이 된다.
    }


    public int getNumber() {
        //[지역변수] <> 전역변수(static)
        //해당 메서드가 실행될 떄 마다 독립적인 값을 저장하고 관리합니다.
        //이 지역변수는 메서드 내부에서 정의될때 생성
        //이 메서드가 종료될 때 소멸

        int number =1;
        number+=1;
        return number;
    }
}
