package UTP6.zad3.swing.buttons;

import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton implements ActionListener {
    TaskList taskList;
    public StartButton(TaskList taskList) {
        super("Start task");
        this.addActionListener(this);
        this.taskList = taskList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(taskList.getSelectedValue() != null){
            taskList.startTask(taskList.getSelectedValue());
        }
        else {
            JOptionPane.showMessageDialog(null, "You have to select a task first!");
        }
    }
}
