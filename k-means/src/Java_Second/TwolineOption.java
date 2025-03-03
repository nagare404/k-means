package Java_Second;

public class TwolineOption extends Option{
	StateManager stateManager;
	
	public TwolineOption(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void OnOption(MyDrawing drawing) {
		drawing.setLine(1);
	}
	
	public void OffOption(MyDrawing drawing) {

	}

}
