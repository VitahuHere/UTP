package UTP6.zad3.swing.views;

import UTP6.zad3.swing.buttons.AddButton;
import UTP6.zad3.task.TaskList;

import javax.swing.*;
import java.awt.*;

public class AddTaskPanel extends JPanel {
    public AddTaskPanel(TaskList taskList, JFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Save as name:"));
        TextField title = new TextField();
        add(title);
        add(new JLabel("Insert URL of the file to download:"));
        TextField url = new TextField();
        add(url);
        add(new JLabel("Download to directory (empty saves in cwd):"));
        TextField directory = new TextField();
        add(directory);
        add(new AddButton(taskList, frame, title, url, directory));
    }
}
