package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WidthActionListener implements ActionListener {
    private Mediator mediator;
    private int width;
    private MyCanvas canvas;

    public WidthActionListener(Mediator mediator, int width, MyCanvas canvas) {
        this.mediator = mediator;
        this.width = width;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.setLineWidth(this.width);
        canvas.repaint();
    }
}
