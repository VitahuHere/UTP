/**
 * @author Vu Cong Minh S25206
 */

package UTP6.zad3.swing.buttons;

import UTP6.zad3.task.Task;
import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButton extends JButton implements ActionListener {
    TaskList taskList;
    TextField title;
    TextField url;
    TextField directory;
    JFrame frame;

    public AddButton(TaskList taskList, JFrame frame, TextField title, TextField url, TextField directory) {
        super("Add new task");
        this.addActionListener(this);
        this.taskList = taskList;
        this.frame = frame;
        this.title = title;
        this.url = url;
        this.directory = directory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (title.getText().equals("") || url.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Title and url cannot be empty");
        } else {
            taskList.addTask(new Task<>(title.getText(), url.getText(), directory.getText()));
            frame.dispose();
        }
    }
}
