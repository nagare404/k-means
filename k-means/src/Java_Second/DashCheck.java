package Java_Second;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class DashCheck extends JCheckBox{
	StateManager stateManager;	
	Option option;
	public DashCheck(StateManager stateManager) {
		super("Dash");
		
		addItemListener(new DashShadowListener());
		this.stateManager = stateManager;

	}
	
	class DashShadowListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if(state == ItemEvent.SELECTED) {
				option = new DashOption(stateManager);
				stateManager.setOption(option);
			}else {
				stateManager.removeOption(option);
			}
		}
	}

}

