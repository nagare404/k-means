package Java_Second;

public class DropOption extends Option{
	StateManager stateManager;
	
	public DropOption(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void OnOption(MyDrawing drawing) {
		drawing.setShadow(true);
	}
	
	public void OffOption(MyDrawing drawing) {

	}
}
