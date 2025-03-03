package Java_Second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public class HendcagonalButton extends JButton{
	StateManager stateManager;
	
	public HendcagonalButton(StateManager stateManager) {
		super("Hendcagonal");
		
		
		addActionListener(new HendcagonalListener());
		
		
		this.stateManager = stateManager;
	}
	
	class HendcagonalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new HendcagonalState(stateManager));
		}
	}

}
