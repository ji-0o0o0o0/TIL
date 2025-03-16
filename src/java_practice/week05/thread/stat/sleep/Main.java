package java_practice.week05.thread.stat.sleep;

public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task : " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(task, "Thread");
        thread.start();

        try {
            thread.sleep(1000);
            // sleep() 은 static 메서드이므로 특정 쓰레드를 지정해서 sleep 할 수 없음
            // 또한 해당 메서드에 exception 처리 되어있으므로 꼭 try-catch 를 해줘야함!
            System.out.println("sleep(1000) : " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}