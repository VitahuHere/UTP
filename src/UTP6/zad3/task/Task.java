package UTP6.zad3.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Task<T> implements Future<T>, Runnable {
    private Status status;
    private final String name;
    private final String url;
    private final String directory;

    private String error;

    public Task(String name, String url, String directory) {
        this.name = name;
        this.status = Status.TO_DO;
        this.url = url;
        String dir = directory.equals("") ? "./" : directory;
        if (!dir.endsWith("/")) {
            this.directory = dir + "/";
        } else {
            this.directory = dir;
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (status == Status.IN_PROGRESS || status == Status.TO_DO && mayInterruptIfRunning) {
            status = Status.ABORTED;
            return true;
        }
        return false;
    }

    @Override
    public boolean isCancelled() {
        return status == Status.ABORTED;
    }

    @Override
    public boolean isDone() {
        return status == Status.DONE || status == Status.ABORTED;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        try {
            return get(0, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (status == Status.TO_DO) {
            status = Status.IN_PROGRESS;
            run();
        }
        if (status == Status.DONE) {
            return (T) Boolean.TRUE;
        } else if (status == Status.ABORTED) {
            return (T) Boolean.FALSE;
        } else {
            throw new TimeoutException();
        }
    }

    public String getStatus() {
        switch (status) {
            case TO_DO:
                return "To do";
            case IN_PROGRESS:
                return "In progress";
            case DONE:
                return "Done";
            case ABORTED:
                return "Aborted";
            case ERROR:
                return "Error";
        }
        return "Unknown status";
    }

    public String getDetails() {
        if (status == Status.ERROR) {
            return "Status: " + getStatus() + "\nError: " + error;
        }
        return "Status: " + getStatus() + "\nName: " + name + "\nFrom url: " + url + "\nTo directory: " + directory;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        if (status == Status.TO_DO) {
            status = Status.IN_PROGRESS;
            try {
                URL website = new URL(url);
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(directory + name);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                status = Status.DONE;
            } catch (IOException e) {
                error = e.getMessage();
                status = Status.ERROR;
            }
        }
    }
}
