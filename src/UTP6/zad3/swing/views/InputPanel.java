package UTP6.zad3.swing.views;

import UTP6.zad3.swing.buttons.*;
import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    public InputPanel(TaskList taskList, ResultPanel resultPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add download");
        addButton.addActionListener(e -> new AddTaskFrame(taskList));
        CheckStatusButton checkStatusButton = new CheckStatusButton(taskList, resultPanel);
        CancelButton cancelButton = new CancelButton(taskList);
        StartButton startButton = new StartButton(taskList);
        DeleteButton deleteButton = new DeleteButton(taskList);
        StartAllButton startAllButton = new StartAllButton(taskList);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonsPanel.add(addButton);
        buttonsPanel.add(checkStatusButton);
        buttonsPanel.add(startButton);
        buttonsPanel.add(startAllButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(deleteButton);
        add(buttonsPanel);
    }
}
