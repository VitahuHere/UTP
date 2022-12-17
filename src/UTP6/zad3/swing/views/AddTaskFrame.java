package UTP6.zad3.swing.views;

import UTP6.zad3.task.TaskList;

import javax.swing.*;

public class AddTaskFrame extends JFrame {

    public AddTaskFrame(TaskList taskList) {
        super("Add task");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setContentPane(new AddTaskPanel(taskList, this));
        setVisible(true);
    }
}
