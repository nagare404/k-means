package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadActionListener implements ActionListener {
    private Mediator mediator;
    private MyCanvas canvas;

    public LoadActionListener(Mediator mediator, MyCanvas canvas) {
        this.mediator = mediator;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.loadDrawings();
        canvas.repaint();
    }
}
