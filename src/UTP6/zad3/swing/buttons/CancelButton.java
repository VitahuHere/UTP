/**
 * @author Vu Cong Minh S25206
 */

package UTP6.zad3.swing.buttons;

import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButton extends JButton implements ActionListener {
    TaskList taskList;

    public CancelButton(TaskList taskList) {
        super("Cancel");
        this.taskList = taskList;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            this.taskList.getModel().getElementAt(this.taskList.getSelectedIndex()).cancel(true);
        } catch (ArrayIndexOutOfBoundsException ignore){}
    }
}
