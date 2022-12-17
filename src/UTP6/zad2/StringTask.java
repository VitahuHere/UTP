package UTP6.zad2;

public class StringTask implements Runnable {
    private final String str;
    private final int n;
    private String result;
    private final Thread thread;
    private TaskState taskState;

    public StringTask(String str, int n) {
        this.str = str;
        this.n = n;
        this.result = "";
        this.thread = new Thread(this);
        this.taskState = TaskState.CREATED;
    }

    public void start() {
        this.thread.start();
    }

    public void abort() {
        this.thread.interrupt();
    }

    public boolean isDone() {
        return this.taskState == TaskState.READY || this.taskState == TaskState.ABORTED;
    }

    public TaskState getState() {
        return this.taskState;
    }

    public String getResult() {
        return this.result;
    }

    @Override
    public void run() {
        this.taskState = TaskState.RUNNING;
        for (int i = 0; i < this.n; i++) {
            this.result += this.str;
            if (Thread.interrupted()) {
                this.taskState = TaskState.ABORTED;
                return;
            }
        }
        this.taskState = TaskState.READY;
    }
}
