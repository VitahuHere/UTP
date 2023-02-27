/**
 * @author Vu Cong Minh S25206
 */

package UTP6.zad3.swing.views;

import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new GridBagLayout());
        ResultPanel resultPanel = new ResultPanel();
        TaskList taskList = new TaskList();
        InputPanel inputPanel = new InputPanel(taskList, resultPanel);
        TaskPanel taskPanel = new TaskPanel(taskList);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(taskPanel, c);
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        add(inputPanel, c);
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        add(resultPanel, c);
    }
}
