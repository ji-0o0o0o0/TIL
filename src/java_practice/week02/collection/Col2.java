package java_practice.week02.collection;

import java.util.LinkedList;

public class Col2 {
    public static void main(String[] args) {
        //LinkedList
        //메모리에 남는 공간을 요청해서 여기저기 나누어서 실제값 저장
        // 실제 값이 있는 주소값으로 목록을 구성하고 저장하는 자료구조

        //기본 기능은 ArrayList 와 비슷하지만
        //조회 시 현저히 느림( 여기저기 나눠져 있으므로)
        //그러나, 값추가하거나 삭제 시 빠름

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(44);
        linkedList.add(7);

        System.out.println(linkedList.get(0));

        //추가
        linkedList.add(200);

        // 2번째 자리에 20 삽입
        linkedList.add(1,76);
        System.out.println(linkedList.toString());

        //2번째 자리 76을 34로 변경
        linkedList.set(1,34);
        System.out.println(linkedList.toString());


        //3번째 자리 44 제거
        linkedList.remove(2);
        System.out.println(linkedList.toString());

        //모두 삭제
        linkedList.clear();
        System.out.println(linkedList.toString());
    }
}
