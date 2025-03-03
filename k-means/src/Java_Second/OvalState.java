package Java_Second;

import java.awt.Color;

public class OvalState extends State{
	StateManager stateManager;
	MyDrawing drawing;
	
	public OvalState(StateManager stateManager) {
		this.stateManager = stateManager;
		this.stateManager.modeset(false);
	}
	
	public void mouseDown(int x,int y){
		drawing = new MyOval(x, y, 0,0, Color.black, Color.white, 1,false);
		stateManager.addDrawing(drawing);
	}
	
	public void mouseUp(int x,int y) {
		
	}
	
	public void mouseDrag(int x,int y) {
		drawing.setSize(x, y);
		stateManager.addDrawing(drawing);
		System.out.println("a");
	}
}
