package Java_Second;

public class DashOption extends Option{
	StateManager stateManager;
	
	public DashOption(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void OnOption(MyDrawing drawing) {
		drawing.setDash();
	}
	
	public void OffOption(MyDrawing drawing) {

	}
}
