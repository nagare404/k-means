package Java_Second;

import java.util.Vector;

public class StateManager{
	
	private MyCanvas canvas;
	private State state;
	private MyDrawing drawing;
	private Vector<Option> option = new Vector<>();
	private boolean selectmode = true;
	private Mediator med;
	
	public StateManager(MyCanvas canvas, Mediator med) {
		this.canvas = canvas;
		this.med = med;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public void addDrawing(MyDrawing drawing) {
		this.drawing = drawing;
	}
	
	public void mouseDown(int x, int y) {
		state.mouseDown(x, y);
		option.forEach(o -> o.OnOption(drawing));
		canvas.addDrawing(drawing);
	}
	public void mouseDrag(int dx, int dy) {
		canvas.removeDrawing(drawing);
		state.mouseDrag(dx, dy);
		canvas.addDrawing(drawing);
	}
	public void mouseUp(int x,int y) {
		if(x*x<25 || y*y<25) {
			canvas.removeDrawing(drawing);
		}else {
			med.Stackpage();
		}
	}
	
	public void setOption(Option option) {
		this.option.add(option);
	}
	
	public void removeOption(Option option) {
		this.option.remove(option);
	}
	
	public void modeset(boolean b) {
		this.selectmode = b;
		if(!b) {
			med.clearDelecctedDrawings();
		}
	}
	
	public boolean modeget() {
		return this.selectmode;
	}
}
