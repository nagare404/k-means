package Java_Second;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class DashlengthCheck extends JCheckBox{
	StateManager stateManager;	
	Option option;
	public DashlengthCheck(StateManager stateManager) {
		super("Dashlength");
		
		addItemListener(new DashlengthShadowListener());
		this.stateManager = stateManager;

	}
	
	class DashlengthShadowListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if(state == ItemEvent.SELECTED) {
				option = new DashlengthOption(stateManager);
				stateManager.setOption(option);
			}else {
				stateManager.removeOption(option);
			}
		}
	}

}

