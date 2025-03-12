package java_practice.week05.thread;

//2. Runnable을 통해서
public class TestRunnable implements Runnable{
    @Override
    public void run() {
        //쓰레드에서 실행할 메서드
        for (int i = 0; i <100 ; i++) {
            System.out.print("$");
        }

    }
}
