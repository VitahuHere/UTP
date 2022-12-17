package UTP6.zad3.task;


import UTP6.zad3.swing.views.EasterFrame;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskList extends JList<Task<Boolean>> {
    ExecutorService executorService;

    public TaskList() {
        super(new DefaultListModel<>());
        executorService = Executors.newCachedThreadPool();
    }

    public void addTask(Task<Boolean> task) {
        ((DefaultListModel<Task<Boolean>>) getModel()).addElement(task);
        if (this.getModel().getSize() == 5){
            engageProtocolRick();
        }
//        if (this.getModel().getSize() == 13){
//            engageProtocolTS();
//        }
    }

    public void startTask(Task<Boolean> task) {
        executorService.submit(task);
    }

    public void deleteTask(Task<Boolean> task) {
        ((DefaultListModel<Task<Boolean>>) getModel()).removeElement(task);
    }

    public void startAllTasks() {
        for (int i = 0; i < getModel().getSize(); i++) {
            executorService.submit(getModel().getElementAt(i));
        }
    }

    private void engageProtocolRick(){
        new EasterFrame("hehe.wav", "./rick.gif");
    }

//    private void engageProtocolTS(){
//        new EasterFrame("ts.wav", "./ts.gif");
//    }
}
