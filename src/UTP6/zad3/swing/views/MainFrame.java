/**
 * @author Vu Cong Minh S25206
 */
 
package UTP6.zad3.swing.views;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Zadanie 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setContentPane(new MainPanel());
        setVisible(true);
    }
}
