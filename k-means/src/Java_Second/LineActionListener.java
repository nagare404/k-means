package Java_Second;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineActionListener implements ActionListener {
    private Mediator mediator;
    private Color color;
    private MyCanvas canvas;

    public LineActionListener(Mediator mediator, Color color, MyCanvas canvas) {
        this.mediator = mediator;
        this.color = color;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.setLineColor(this.color);
        canvas.repaint();
    }
}
