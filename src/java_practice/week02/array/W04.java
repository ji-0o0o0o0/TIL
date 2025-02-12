package java_practice.week02.array;

public class W04 {
    public static void main(String[] args) {
        //가변배열
        int[][] array = new int[3][];

        //배열 원소마다 각기 다른 크기로 지정
        array[0] = new int[2];
        array[1] = new int[4];
        array[2] = new int[1];

        //중괄호로 초기화를 해버릴때도 가능
        int[][] arrays = {
                {10,20},
                {10,30,20},
                {10}
        };
    }
}
