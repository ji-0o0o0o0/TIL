package java_practice.week03.packagExample.main;


public class Main {
    public static void main(String[] args) {

        //Car car = new Car();->  같은 패키지가 아니므로 이렇게 못가져옴

        // 클래스의 일부분이며, 하위 패키지를 도트(.)로 구분한다.
        java_practice.week03.packagExample.pk1.Car car1 = new java_practice.week03.packagExample.pk1.Car();
        java_practice.week03.packagExample.pk2.Car car2 = new java_practice.week03.packagExample.pk2.Car();

        car1.horn();
        car2.horn();
    }
}
