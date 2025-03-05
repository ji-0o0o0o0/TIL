package java_practice.week03.CheckWeek3;

public class Main {
    public static void main(String[] args) {
        /*Calculator calculator = new Calculator(
                new AddOperation(),
                new SubstractOperation(),
                new MultiplyOperation(),
                new DivideOperation()
        );

        System.out.println("더하기: "+calculator.calculate("더하기",40,10));
        System.out.println("빼기: "+calculator.calculate("빼기",40,10));
        System.out.println("곱하기: "+calculator.calculate("곱하기",40,10));
        System.out.println("나누기: "+calculator.calculate("나누기",40,10));*/

        //더하기로 세팅
        Calculator calculator = new Calculator(new AddOperation());
        System.out.println(calculator.calculate(1,3));

        //뺴기로 변경
        calculator.setOperation(new SubstractOperation());
        System.out.println(calculator.calculate(5,2));

    }
}
