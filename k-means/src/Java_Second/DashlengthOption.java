package Java_Second;

public class DashlengthOption extends Option{
	StateManager stateManager;
	
	public DashlengthOption(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void OnOption(MyDrawing drawing) {
		drawing.setlength();
	}
	
	public void OffOption(MyDrawing drawing) {

	}
}
