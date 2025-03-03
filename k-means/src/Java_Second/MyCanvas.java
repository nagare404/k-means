package Java_Second;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Enumeration;

import javax.swing.JPanel;

public class MyCanvas extends JPanel{
	Mediator mediator;
	
	public MyCanvas() {
		this.mediator = new Mediator(this);
		setBackground(Color.white);
	}
	
	public Mediator getMediator() {
		return mediator;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Enumeration<MyDrawing> e = mediator.drawingsElements();
		while(e.hasMoreElements()) {
			MyDrawing d = e.nextElement();
			d.draw(g);
		}
		
	}
	
	public void addDrawing(MyDrawing d) {
		this.mediator.addDrawing(d);
	}
	
	public void removeDrawing(MyDrawing d) {
		this.mediator.removeDrawing(d);
	}
	
}
