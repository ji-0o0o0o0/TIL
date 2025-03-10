package java_practice.week04.CheckWeek4;
public class Main {
    public static void main(String[] args) {
        boolean calculateEnded = false;
        // 구현 2.
        //calculateEnded = true 면 stop
        while (!calculateEnded){
            try {
                calculateEnded = CalculatorApp.start();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}