package java_practice.week04.CheckWeek4;
import java.util.regex.Pattern;

public class Parser {
    private static final String OPERATION_REG = "[+\\-*/]";
    private static final String NUMBER_REG = "^[0-9]*$";

    private final Calculator calculator = new Calculator();

    public Parser parseFirstNum(String firstInput) throws BadInputException {
        // 구현 1. 정수인지 확인
        if(!Pattern.matches(NUMBER_REG,firstInput)){
            throw new BadInputException("정수값");
        }
        this.calculator.setFirstNumber(Integer.parseInt(firstInput));
        return this;
    }

    public Parser parseSecondNum(String secondInput) throws BadInputException {
        // 구현 1.
        if(!Pattern.matches(NUMBER_REG,secondInput)){
            throw new BadInputException("정수값");
        }
        this.calculator.setSecondNumber(Integer.parseInt(secondInput));
        return this;
    }

    public Parser parseOperator(String operationInput) throws Exception {
        // 구현 1.연산자 확인
        if(!Pattern.matches(OPERATION_REG,operationInput)){
            throw new BadInputException("연산값");
        }
        switch (operationInput){
            case "+"->this.calculator.setOperation(new AddOperation());
            case "-"->this.calculator.setOperation(new SubstractOperation());
            case "/"->this.calculator.setOperation(new DivideOperation());
            case "*"->this.calculator.setOperation(new MultiplyOperation());
        }
        return this;
    }

    public double executeCalculator() {
        return calculator.calculate();
    }
}