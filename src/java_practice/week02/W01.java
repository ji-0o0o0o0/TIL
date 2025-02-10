package java_practice.week02;

import java.util.Arrays;

public class W01 {
    public static void main(String[] args) {
        //깊은 복사
        //Arrays.copyOf() 메서드
        int[] a = {1,2,3,4};
        int[] b = Arrays.copyOf(a,a.length); //배열도 함께 길이도 넣어줌

        a[3] = 0;
        System.out.println(a[3]);
        System.out.println(b[3]);
        //=> a != b
    }
}
