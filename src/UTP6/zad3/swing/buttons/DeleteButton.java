package UTP6.zad3.swing.buttons;

import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends JButton implements ActionListener {
    TaskList taskList;

    public DeleteButton(TaskList taskList) {
        super("Delete task");
        this.addActionListener(this);
        this.taskList = taskList;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        taskList.deleteTask(taskList.getSelectedValue());
    }
}
