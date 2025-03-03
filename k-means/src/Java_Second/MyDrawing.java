package Java_Second;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

public class MyDrawing implements Cloneable, Serializable{
	private int x, y, w, h, r, p;
	private Color lineColor, fillColor;
	private int lineWidth;
	private boolean isDashed;
	private boolean isShadow;
	private int line;
	private boolean islength;
	private boolean isSelected;
	final int SIZE = 7;
	private Rectangle region;
	
	public MyDrawing(int xpt, int ypt, int wpt, int hpt, int rpt, int ppt, Color LC, Color FC, int LW, boolean Dash) {
		setLocation(xpt,ypt);
		setSize(wpt,hpt);
		setRacical(rpt);
		setPoint(ppt);
		lineColor = LC;
		fillColor = FC;
		lineWidth = LW;
		isDashed = false;
		isShadow = false;
		islength = false;
		isSelected = false;
		line = 0;
	}
	
	public void draw(Graphics g) {
		if(isSelected) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.black);
			g2.fillRect(x+w/2-SIZE/2,y-SIZE/2,SIZE,SIZE);
			g2.fillRect(x-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g2.fillRect(x+w/2-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
			g2.fillRect(x+w-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g2.fillRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g2.fillRect(x+w-SIZE/2,y-SIZE/2,SIZE,SIZE);
			g2.fillRect(x-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g2.fillRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g2.drawRect((int)region.getX(), (int)region.getY(), (int)region.getWidth(), (int)region.getHeight());
		}
	}
	
	public boolean contains(int x, int y) {
		return this.region.contains(x,y);
	}
	
	public void setRegion(int x,int y,int w,int h) {
		this.region = new Rectangle(x,y,w,h);
	}
	
	public Rectangle getRegiion() {
		return this.region;
	}

	public void move(int dx, int dy) {
		this.x = this.x + dx;
		this.y = this.y + dy;
	}
	
	public void scale(int dw, int dh) {
		this.w += dw;
		this.h += dh;
	}
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setRacical(int r) {
		this.r = r;
		this.w = 2*r;
		this.h = 2*r;
	}
	
	public void setPoint(int p) {
		this.p = p;
	}
	
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
	}
	
	public void setLineColor(Color c) {
		this.lineColor = c;
	}
	
	public void setFillColor(Color c) {
		this.fillColor = c;
	}
	
	public void setLineWidth(int l) {
		this.lineWidth = l;
	}
	
	public void setDash() {
		this.isDashed = true;
	}
	
	public void setShadow(boolean b) {
		this.isShadow = b;
	}
	
	public void setLine(int l) {
		this.line = l;
	}
	
	public void setlength() {
		this.islength = true;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getW() {
		return this.w;
	}

	public int getH() {
		return this.h;	
	}
	
	public int getR(){
		return this.r;
	}
	
	public int getP() {
		return this.p;
	}
	
	public Color getLineColor() {
		return this.lineColor;
	}
	
	public Color getFillColor() {
		return this.fillColor;
	}
	
	public int getLineWidth() {
		return this.lineWidth;
	}
	
	public boolean getDash() {
		return this.isDashed;
	}
	
	public boolean getShadow() {
		return this.isShadow;
	}
	
	public int getLine() {
		return this.line;
	}
	
	public boolean getLength() {
		return this.islength;
	}
	
	public boolean getSelected() {
		return this.isSelected;
	}
	@Override
	    public Object clone() {
	        try {
	            return super.clone();
	        } catch (CloneNotSupportedException e) {
	            throw new AssertionError(); 
	        }
	    }
}