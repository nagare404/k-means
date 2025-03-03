package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShadowActionListener implements ActionListener {
    private Mediator mediator;
    private Boolean shadow;
    private MyCanvas canvas;

    public ShadowActionListener(Mediator mediator, boolean shadow, MyCanvas canvas) {
        this.mediator = mediator;
        this.shadow = shadow;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.setShadow(this.shadow);
        canvas.repaint();
    }
}