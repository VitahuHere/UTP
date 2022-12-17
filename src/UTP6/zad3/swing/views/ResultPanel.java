package UTP6.zad3.swing.views;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    JTextArea textArea;
    public ResultPanel() {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(textArea, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createTitledBorder("Task status"));
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
