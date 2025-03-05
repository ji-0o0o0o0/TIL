package java_practice.week03.CheckWeek3;

public class Calculator {
/*
    private final AddOperation addOperation;
    private final SubstractOperation substractOperation;
    private final MultiplyOperation multiplyOperation;
    private final DivideOperation divideOperation;

    public Calculator(AddOperation addOperation, SubstractOperation substractOperation, MultiplyOperation multiplyOperation, DivideOperation divideOperation) {
        this.addOperation = addOperation;
        this.substractOperation = substractOperation;
        this.multiplyOperation = multiplyOperation;
        this.divideOperation = divideOperation;
    }

    public double calculate(String operator, int firstNumber, int secondNumber){

        switch (operator){
            case  "더하기":
                return addOperation.operate(firstNumber,secondNumber);
           case  "빼기":
                return substractOperation.operate(firstNumber,secondNumber);
            case  "곱하기":
                return multiplyOperation.operate(firstNumber,secondNumber);
            case  "나누기":
                return divideOperation.operate(firstNumber,secondNumber);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);

        }

    }*/


    AbstractOperation operation;

    public Calculator(AbstractOperation operation) {
        this.operation = operation;
    }

    public void setOperation(AbstractOperation operation) {
        this.operation = operation;
    }

    double calculate(int firstNumber, int secondNumber){
        return  operation.operate(firstNumber,secondNumber);
    }

}
