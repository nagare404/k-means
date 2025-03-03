package Java_Second;

import java.awt.Color;

public class PolygonState extends State{
	StateManager stateManager;
	MyDrawing drawing; 
	
	public PolygonState(StateManager stateManager) {
		this.stateManager = stateManager;
		this.stateManager.modeset(false);
	}
	
	public void mouseDown(int x,int y) {
		drawing = new MyPolygon(x, y, 0,6, Color.black, Color.white, 1,false);
		stateManager.addDrawing(drawing);
	}
	
	public void mouseUp(int x,int y) {
		
	}
	
	public void mouseDrag(int x,int y) {
		if(x>y) {
			drawing.setRacical(x);
		}else {
			drawing.setRacical(y);
		}
		
		stateManager.addDrawing(drawing);
	}
}
