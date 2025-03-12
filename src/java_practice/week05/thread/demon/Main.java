package java_practice.week05.thread.demon;

public class Main {
    public static void main(String[] args) {
        Runnable demon = () -> {
            for (int i = 0; i < 1000000; i++) { //원래 백만번 찍혀야하지만 task 작업이 끝나면 중간에 멈춤
                System.out.println("demon");
            }
        };

        // 우선순위 낮음 -> 상대적으로 다른 쓰레드에 비해 리소스를 적게 할당!
        Thread thread = new Thread(demon);
        thread.setDaemon(true); // true로 설정시 데몬스레드로 실행됨

        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("task");
        }
    }
}