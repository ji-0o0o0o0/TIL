package java_practice.week05.thread;

//1. Thread class 이용(상속)
public class TestThread extends Thread{

    @Override
    public void run() {
        //실제로 우리가 쓰레드에서 수행할 작업
        for (int i = 0; i <100 ; i++) {
            System.out.print("*");
        }
    }
}
