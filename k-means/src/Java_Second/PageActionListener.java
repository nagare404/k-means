package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageActionListener implements ActionListener {
    private Mediator mediator;
    private MyCanvas canvas;
    private int p;

    public PageActionListener(Mediator mediator, MyCanvas canvas, int p) {
        this.mediator = mediator;
        this.canvas = canvas;
        this.p = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.changepage(p);
        canvas.repaint();
    }
}
