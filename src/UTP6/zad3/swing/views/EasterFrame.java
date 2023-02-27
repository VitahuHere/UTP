/**
 * @author Vu Cong Minh S25206
 */
 
package UTP6.zad3.swing.views;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EasterFrame extends JFrame {
    public EasterFrame(String audio, String gif) {
        if (audio.contains("hehe")) {
            this.setTitle("You have been Rick Rolled!");
        } else {
            this.setTitle("You have been Taylor Swifted!");
        }
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(720, 480);
        this.setLocationRelativeTo(null);
        EasterPanel panel = new EasterPanel(audio, gif);
        this.setContentPane(panel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                panel.stop();
                super.windowClosed(e);
            }
        });
        this.setVisible(true);
    }
}
