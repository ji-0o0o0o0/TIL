package java_practice.week02.array;

public class W05 {
    public static void main(String[] args) {
        //최대값 구하기
        int[] arr = {3,1,2,5,1};
        int max =arr[0];
        for (int i:arr ) {
            if(max<i){
                max=i;
            }
        }
        System.out.println(max);
    }
}
