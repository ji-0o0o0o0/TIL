package java_practice.week05.thread;

public class Main {
    public static void main(String[] args) {
//        1. thread class 직접 사용
//        TestThread thread = new TestThread();
//        thread.start();//testThread 실행!

//        2. Runnable 통해서
        Runnable run = new TestRunnable();//-> 이게 가능한 이유는 testRunnable 클래스가 Runnable 을 implement 했기떄문에
        Thread thread = new Thread(run);
        thread.start();

//        3. 람다식을 통해서
        Runnable task = () -> {
            int sum = 0;
            for (int i = 0; i < 50; i++) {
                sum += i;
                System.out.println(sum);
            }
            System.out.println(Thread.currentThread().getName() + " 최종 합 : " + sum);
        };

        Thread thread1 = new Thread(task);
        thread1.setName("thread1");
        Thread thread2 = new Thread(task);
        thread2.setName("thread2");

        thread1.start();
        thread2.start();




    }
}
