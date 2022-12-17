package UTP6.zad3.swing.views;

import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.*;

public class TaskPanel extends JPanel {
    public TaskPanel(TaskList taskList) {
        setLayout(new BorderLayout());
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        this.setBorder(BorderFactory.createTitledBorder("Tasks"));
    }
}
