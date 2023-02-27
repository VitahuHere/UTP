/**
 * @author Vu Cong Minh S25206
 */

package UTP6.zad3.swing.views;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EasterPanel extends JPanel {
    AudioInputStream audioInputStream;
    Clip clip;
    public EasterPanel(String audio, String gif) {
        this.setLayout(new BorderLayout());
        URL url = this.getClass().getResource(gif);
        assert url != null;
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        this.add(label, BorderLayout.CENTER);
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/zad3/swing/views/" + audio).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
