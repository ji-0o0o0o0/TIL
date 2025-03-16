package java_practice.week05.thread.stat.sync;

public class Main {
    public static void main(String[] args) {
        AppleStore appleStore = new AppleStore();

        Runnable task = () -> {
            while (appleStore.getStoredApple() > 0) {
                appleStore.eatApple();
                System.out.println("남은 사과의 수 = " + appleStore.getStoredApple());
            }

        };

        // 3개의 thread를 한꺼번에 만들어서 start 를  해버림!
        // 생성(new)과 동시에 start(new-> runnable)
        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }
}

class AppleStore {
    private int storedApple = 10;

    public int getStoredApple() {
        return storedApple;
    }

    public void eatApple() {
        if (storedApple > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storedApple -= 1;
        }
    }
}
