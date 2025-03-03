package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoActionListener implements ActionListener {
    private Mediator mediator;
    private MyCanvas canvas;

    public UndoActionListener(Mediator mediator, MyCanvas canvas) {
        this.mediator = mediator;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.undo();
        canvas.repaint();
    }
}
