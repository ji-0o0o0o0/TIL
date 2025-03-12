package java_practice.week05.thread.priority;
public class Main {
    public static void main(String[] args) {

        Runnable task1 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.print("$");
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.print("*");
            }
        };

        Thread thread1 = new Thread(task1);
        thread1.setPriority(8);
        int threadPriority = thread1.getPriority();
        System.out.println("threadPriority = " + threadPriority);

        Thread thread2 = new Thread(task2);
        thread2.setPriority(2);

        thread1.start();// 순서상 8인 thread1 이 먼저 실행되어야하지만 지금은 가벼운 작업이므로 크게 차이는 안남
        thread2.start();
    }
}
