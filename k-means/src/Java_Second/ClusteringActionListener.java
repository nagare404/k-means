package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClusteringActionListener implements ActionListener {
    private PCA pca;
    private MyCanvas canvas;
    private int c;

    public ClusteringActionListener(PCA pca, MyCanvas canvas,int c) {
        this.pca = pca;
        this.canvas = canvas;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pca.kmeans(c);
        canvas.repaint();
    }
}
