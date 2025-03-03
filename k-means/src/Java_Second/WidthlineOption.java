package Java_Second;

public class WidthlineOption extends Option{
	StateManager stateManager;
	int w;
	
	public WidthlineOption(StateManager stateManager, int w) {
		this.stateManager = stateManager;
		this.w = w;
	}
	
	public void OnOption(MyDrawing drawing) {
		drawing.setLineWidth(w);
	}
	
	public void OffOption(MyDrawing drawing) {

	}
}
