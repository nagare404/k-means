package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public class PolygonButton extends JButton{
	StateManager stateManager;
	
	public PolygonButton(StateManager stateManager) {
		super("Polygon");
		
		
		addActionListener(new PolygonListener());
		
		
		this.stateManager = stateManager;
	}
	
	class PolygonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new PolygonState(stateManager));
		}
	}

}
