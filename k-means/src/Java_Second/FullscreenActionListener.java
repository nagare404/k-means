package Java_Second;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class FullscreenActionListener implements ActionListener {
    private JFrame f;
    private boolean isFullscreen = false;
    private GraphicsDevice d;

    public FullscreenActionListener(JFrame f) {
        this.f = f;
        d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isFullscreen = !isFullscreen;
        f.dispose();
        f.setUndecorated(isFullscreen);
        if (isFullscreen) {
            d.setFullScreenWindow(f);
        } else {
            d.setFullScreenWindow(null);
            f.setVisible(true);
        }
        System.out.println(f.getSize());
    }
}