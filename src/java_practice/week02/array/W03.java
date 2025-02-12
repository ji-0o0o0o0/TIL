package java_practice.week02.array;

public class W03 {
    public static void main(String[] args) {
        //반복문을 통한 이중 배열 초기화
        int[][] array = new int[2][3];

        for (int i = 0; i < array.length ; i++) {
            for (int j = 0; j < array[i].length ; j++) {
                System.out.println("출력값: "+i+","+j);
                array[i][j]=0; //i,j 는 위 노란색 네모박스 안에 있는 숫자를 의미, 인덱스라고 부름
            }
        }
    }
}
