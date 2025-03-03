package Java_Second;

public class TreelineOption extends Option{
	StateManager stateManager;
	
	public TreelineOption(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void OnOption(MyDrawing drawing) {
		drawing.setLine(2);
	}
	
	public void OffOption(MyDrawing drawing) {

	}
}
