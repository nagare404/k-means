package Java_Second;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class MyPolygon extends MyDrawing{
	public MyPolygon(int xpt, int ypt, int rpt,int ppt, Color LC, Color FC, int LW, boolean DH) {
		super(xpt,ypt,0,0,rpt,ppt,LC,FC,LW,DH);
        }
	
	public void draw(Graphics g) {

		
        int x = getX();
		int y = getY();
		int r = getR();
		int p = getP();
		int w = getW();
		int h = getH();
		int[] xPoints = new int[p];
        int[] yPoints = new int[p];
        int[] xshadow = new int[p]; 
        int[] yshadow = new int[p]; 
        
		if(r < 0) {
			r *= -1;
		}
        for (int i = 0; i < p; i++) {
            xPoints[i] = (int) (x + r * Math.cos(2 * Math.PI * i / p));
            yPoints[i] = (int) (y - r * Math.sin(2 * Math.PI * i / p));
            xshadow[i] = (int) (x + r * Math.cos(2 * Math.PI * i / p))+5;
            yshadow[i] = (int) (y - r * Math.sin(2 * Math.PI * i / p))+5;
        }
		Graphics2D g2 = (Graphics2D) g;
		if(getShadow()) {
			g2.setStroke(new BasicStroke(getLineWidth()));
			g2.setColor(Color.black);
			g2.fillPolygon(xshadow, yshadow, p);
			g2.drawPolygon(xshadow, yshadow, p);
		}
		if(getDash()) {
			g2.setStroke(new MyDashStroke(getLineWidth(), getLength()));
		}else {
			g2.setStroke(new BasicStroke(getLineWidth()));
		}
		g2.setColor(getFillColor());
		g2.fillPolygon(xPoints, yPoints, p);
		g2.setColor(getLineColor());
		g2.drawPolygon(xPoints, yPoints, p);
		for(int j = 0; j <= getLine(); j++) {
	        for (int i = 0; i < p; i++) {
	            xPoints[i] = (int) (x + (r+5*j) * Math.cos(2 * Math.PI * i / p));
	            yPoints[i] = (int) (y - (r+5*j) * Math.sin(2 * Math.PI * i / p));
	        }
	        g2.drawPolygon(xPoints, yPoints, p);
		}
		setRegion(x-r,y-r,w,h);
		setLocation(x-r,y-r);
		super.draw(g2);
		setLocation(x,y);
		
	}

}

