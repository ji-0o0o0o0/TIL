package java_practice.week05.thread.stat.interrupt;

//쓰레드가 start() 된 후 동작하다 interrupt() 를 만나 실행하면 interrupted 상태가 true 로 변경
public class Main {
    public static void main(String[] args) {
//        Runnable task = () -> {
//            try {
//                Thread.sleep(1000);//sleep 도중에 interrupt 발생시 -> catch!
//                System.out.println(Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("task : " + Thread.currentThread().getName());
//        };
//
//        Thread thread = new Thread(task, "Thread");//New
//        thread.start();//New-> nunnable
//
//        thread.interrupt();
//
//        System.out.println("thread.isInterrupted() = " + thread.isInterrupted());


        Runnable task = () -> {
            //interruppted 상태를 체크해서 처리! -> 오류를 방지!
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);//sleep을 안한 이유는 sleep 을 하는 도중 interrupt 되서!
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("task : " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(task, "Thread");
        thread.start();

        thread.interrupt();

        System.out.println("thread.isInterrupted() = " + thread.isInterrupted());
    }
}