package Java_Second;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class DropCheck extends JCheckBox{
	StateManager stateManager;	
	Option option;
	public DropCheck(StateManager stateManager) {
		super("DropShadow");
		
		addItemListener(new DropShadowListener());
		this.stateManager = stateManager;

	}
	
	class DropShadowListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
			if(state == ItemEvent.SELECTED) {
				option = new DropOption(stateManager);
				stateManager.setOption(option);
			}else {
				stateManager.removeOption(option);
			}
		}
	}

}

