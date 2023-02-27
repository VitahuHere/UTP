/**
 * @author Vu Cong Minh S25206
 */

package UTP6.zad3.swing.buttons;

import UTP6.zad3.swing.views.ResultPanel;
import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckStatusButton extends JButton implements ActionListener {
    TaskList taskList;
    ResultPanel resultPanel;

    public CheckStatusButton(TaskList taskList, ResultPanel resultPanel) {
        super("Check status");
        this.addActionListener(this);
        this.taskList = taskList;
        this.resultPanel = resultPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String status = taskList.getModel().getElementAt(this.taskList.getSelectedIndex()).getDetails();
            resultPanel.getTextArea().setText(status);
        } catch (Exception ex) {
            resultPanel.getTextArea().setText("No task selected");
        }
    }
}
