/**
 * @author Vu Cong Minh S25206
 */

package UTP6.zad1;

public class Letters{
    private final Thread[] threads;

    public Letters(String letters) {
        threads = new Thread[letters.length()];
        for (int i = 0; i < letters.length(); i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        System.out.print(letters.charAt(finalI));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Thread " + letters.charAt(i));
        }
    }

    public Thread[] getThreads() {
        return threads;
    }
}
