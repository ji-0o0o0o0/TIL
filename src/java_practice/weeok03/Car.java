package java_practice.weeok03;

// 클래스 만들떄 step
// 1. 만들고자 하는 설계도 선언 (클래스선언)
// 2. 객체가 가지고 있어야하는 속성(필드) 선언
// 3. 객체가 생성하는 방식 정의(생성자)
// 4. 객체가 가지고 있어야할 행위(메서드) 정의
public class Car {
    //<필드영역>
    //1. 고유데이터
    String company;
    String model;
    String color;
    double price;
    //2. 상태 데이터
    double speed;
    char gear;//기어 상태 : P R N D
    boolean lights;

    //객체데이터 영역
    Tire tire;
    Door door;
    Handle handle;

    //생성자: 처음 객체가 생성될 때(instance화) 어떤 로직을 수행해야 하며, 어떤 값이 필수로 들어와야하는지 정의
    public Car() {
        //logic
        //기본생성자 : 생략가능

        //생성자가 잘 호출되어 객체가 만들어지는지 확인
        System.out.println("생성자가 호출되었습니다! 객체가 생성됩니다. ");

    }

    //메서드 영역
    //gasPedal
    //input : kmh
    //output : speed
    double gasPedal(double kmh){
        speed = kmh;
        return speed;
    };

    //brakePedal
    //input : x
    //output : speed
    double brakePedal(){
        speed =0;
        return speed;
    }
    //changeGear
    //input : gear(char)
    //output : gear
    char changeGear(char type){
        gear = type;
        return gear;
    }

    //onOffLight
    //input : x
    //output : lights(boolean)
    boolean onOffLight(){
        return !lights;
    }
    //horn
    //input : x
    //output : x
    void  horn(){
        System.out.println("빠아아아아앙");
    }

}
