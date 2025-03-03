package Java_Second;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyOval extends MyDrawing{
	public MyOval(int xpt, int ypt, int wpt, int hpt, Color LC, Color FC, int LW, boolean DH){
		super(xpt,ypt,wpt,hpt,0,0,LC,FC,LW,DH);
	}
	
	public void draw(Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		if(w < 0) {
			x += w;
			w *= -1;
		}
		if(h < 0) {
			y += h;
			h *= -1;
		}
		Graphics2D g2 = (Graphics2D) g;
		if(getShadow()) {
			g2.setStroke(new BasicStroke(getLineWidth()));
			g2.setColor(Color.black);
			g2.fillOval(x+5, y+5, w, h);
			g2.drawOval(x, y, w, h);
		}
		if(getDash()) {
			g2.setStroke(new MyDashStroke(getLineWidth(), getLength()));
		}else {
			g2.setStroke(new BasicStroke(getLineWidth()));
		}
		g2.setColor(getFillColor());
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawOval(x, y, w, h);
		for(int i = 0; i <= getLine(); i++) {
			g2.drawOval(x-5*i, y-5*i, w+10*i, h+10*i);
		}
		setRegion(x,y,w,h);
		super.draw(g2);
	}

}
