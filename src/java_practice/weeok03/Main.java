package java_practice.weeok03;

public class Main {
    public static void main(String[] args) {
       Car car = new Car();//객체 생성(인스턴스화)

        //메서드 호출 및 반환값을 저장
        double speed = car.gasPedal(100,'d');
        System.out.println("speed: "+speed);



    }
}
