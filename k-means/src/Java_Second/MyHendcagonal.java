package Java_Second;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class MyHendcagonal extends MyDrawing{
	public MyHendcagonal(int xpt, int ypt, int rpt,int ppt, Color LC, Color FC, int LW, boolean DH) {
		super(xpt,ypt,0,0,rpt,11,LC,FC,LW,DH);
    }
	
	public void draw(Graphics g) {
		
		int[] xPoints = new int[11];
        int[] yPoints = new int[11];
        int[] xshadow = new int[11];
        int[] yshadow = new int[11];
        int x = getX();
		int y = getY();
		int r = getR();
		int w = getW();
		int h = getH();

        for (int i = 0; i < 11; i++) {
            xPoints[i] = (int) (x + r * Math.cos(2 * Math.PI * i / 11));
            yPoints[i] = (int) (y - r * Math.sin(2 * Math.PI * i / 11));
            xshadow[i] = (int) (x + r * Math.cos(2 * Math.PI * i / 11))+5;
            yshadow[i] = (int) (y - r * Math.sin(2 * Math.PI * i / 11))+5;
        }
        Graphics2D g2 = (Graphics2D) g;
		if(getShadow()) {
			g2.setStroke(new BasicStroke(getLineWidth()));
			g2.setColor(Color.black);
			g2.fillPolygon(xshadow, yshadow, 11);
			g2.drawPolygon(xshadow, yshadow, 11);
		}
		if(getDash()) {
			g2.setStroke(new MyDashStroke(getLineWidth(), getLength()));
		}else {
			g2.setStroke(new BasicStroke(getLineWidth()));
		}
		g2.setColor(getFillColor());
		g2.fillPolygon(xPoints, yPoints, 11);
		g2.setColor(getLineColor());
		g2.drawPolygon(xPoints, yPoints, 11);
		for(int j = 0; j <= getLine(); j++) {
	        for (int i = 0; i < 11; i++) {
	            xPoints[i] = (int) (x + (r+5*j) * Math.cos(2 * Math.PI * i / 11));
	            yPoints[i] = (int) (y - (r+5*j) * Math.sin(2 * Math.PI * i / 11));
	        }
	        g2.drawPolygon(xPoints, yPoints, 11);
		}
		setRegion(x-r,y-r,w,h);
		setLocation(x-r,y-r);
		super.draw(g2);
		setLocation(x,y);
	}

}
