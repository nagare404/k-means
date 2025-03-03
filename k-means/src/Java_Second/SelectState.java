package Java_Second;

public class SelectState extends State{
	StateManager stateManager;
	MyDrawing drawing;
	
	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
		this.stateManager.modeset(true);
	}
	
	public void mouseDown(int x,int y) {

	}
	
	public void mouseUp(int x,int y) {
		
	}
	
	public void mouseDrag(int x,int y) {

	}
}
