package java_practice.week03.poly;



public class Main {
    public static void main(String[] args) {

        // Car car1 = new Car(new KiaTire("KIA"));
        //이렇게 들어갈 수 있는 이유는
        //Tire kiaTire = new KiaTire("KIA");
        //이렇게 쓸 수 있는 다형성 떄문
        //부모 타입으로 자동 변환됨

        // 매개변수 다형성 확인!
        Car car1 = new Car(new KiaTire("KIA"));
        Car car2 = new Car(new HankookTire("HANKOOK"));

        // 반환타입 다형성 확인!
        Tire hankookTire = car1.getHankookTire();
        KiaTire kiaTire = (KiaTire) car2.getKiaTire();

        // 오버라이딩된 메서드 호출
        car1.tire.rideComfort(); // KIA 타이어 승차감은 60
        car2.tire.rideComfort(); // HANKOOK 타이어 승차감은 100
    }
}