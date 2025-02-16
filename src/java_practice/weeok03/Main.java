package java_practice.weeok03;

public class Main {
    public static void main(String[] args) {
        Car[] carArray = new Car[3];
        Car car1 = new Car();
        car1.changeGear('P');
        carArray[0]=car1;

        Car car2 = new Car();
        car2.changeGear('N');
        carArray[1]=car2;

        Car car3 = new Car();
        car3.changeGear('D');
        carArray[2]=car3;


        for (int i = 0; i <carArray.length ; i++) {
            System.out.println(carArray[i].gear);
        }
        //System.out.println(car1);
        // java_practice.weeok03.Car@119d7047-> 주소가 출력됨

    }
}
