package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSVsaveActionListener implements ActionListener {
    private PCA pca;
    private MyCanvas canvas;

    public CSVsaveActionListener(PCA pca, MyCanvas canvas) {
        this.pca = pca;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pca.downloaddata();
        canvas.repaint();
    }
}
