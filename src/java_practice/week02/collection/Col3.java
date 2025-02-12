package java_practice.week02.collection;

import java.util.Stack;

public class Col3 {
    public static void main(String[] args) {
        //stack
        //수직으로 값을 쌓아두고, 넣었다가 뺌(basket 형식)
        //push, peek, pop
        //최근거 조회해서 나열할때, 데이터 중복처리 막고 싶을때

        Stack<Integer> integerStack = new Stack<Integer>();

        //값 넣기
        integerStack.push(99);
        integerStack.push(4);
        integerStack.push(23);

        //맨 위에 값 조회
        System.out.println("맨 위에 값 조회: "+ integerStack.peek());
        System.out.println();

        //값 출력
        while (!integerStack.empty()){
            System.out.println(integerStack.pop());
        }
    }
}
