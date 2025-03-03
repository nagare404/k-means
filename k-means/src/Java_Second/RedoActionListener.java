package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoActionListener implements ActionListener {
    private Mediator mediator;
    private MyCanvas canvas;

    public RedoActionListener(Mediator mediator, MyCanvas canvas) {
        this.mediator = mediator;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.redo();
        canvas.repaint();
    }
}
