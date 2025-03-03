package Java_Second;

public class OnelineOption extends Option{
	StateManager stateManager;
	
	public OnelineOption(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void OnOption(MyDrawing drawing) {
		drawing.setLine(0);
	}
	
	public void OffOption(MyDrawing drawing) {

	}
}
