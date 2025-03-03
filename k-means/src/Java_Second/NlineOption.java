package Java_Second;

public class NlineOption extends Option{
	StateManager stateManager;
	int n;
	
	public NlineOption(StateManager stateManager, int n) {
		this.stateManager = stateManager;
		this.n = n;
	}
	
	public void OnOption(MyDrawing drawing) {
		drawing.setLine(n);
	}
	
	public void OffOption(MyDrawing drawing) {

	}
}
