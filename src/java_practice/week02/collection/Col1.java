package java_practice.week02.collection;

import java.util.ArrayList;

public class Col1 {
    public static void main(String[] args) {
        //List
        //순서가 있는 데이터 집합, 동적 배열으로 크기가 가변적으로 늘어남 -> 처음 길이 몰라도 만들 수 있음
        // - 생성 시점에 작은 연속된 공간을 요청해서 참조형 변수들을 담아놓는다.
        // - 값이 추가될 때 더 큰 공간이 필요하면 더 큰 공간을 받아서 저장하니까 추가되도 상관없는것
        //
        //+ Array : 정적배열로 최초 길이를 알아야함

        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(99);
        intList.add(15);
        intList.add(4);

        //값 가져오기
        System.out.println(intList.get(1));//2번째 자리 15출력

        //2번째 자리 값 변경 15->10
        intList.set(1,10);
        System.out.println(intList.get(1));

        //첫번째 값 삭제
        intList.remove(0);
        System.out.println(intList.toString());//첫번째 99가 사라져서 10,4 만 출력

        //모두 삭제
        intList.clear();
        System.out.println(intList.toString());//모두 삭제되어 [] 만 출력

        //intList.toString() 는 배열을 한번에 출력되도록 변경
    }
}
