package Java_Second;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class NlineComboBox extends JComboBox<String>{
	StateManager stateManager;	
	Option option;
	static final String[] n = {"1","2","3"};
	public NlineComboBox(StateManager stateManager) {
		
		super(n);
		addItemListener(new NlineListener());
		this.stateManager = stateManager;

	}
	
	class NlineListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
	            String selectedItem = (String) e.getItem();
				stateManager.removeOption(option);
	            if (selectedItem.equals("1")) {
					option = new NlineOption(stateManager,0);
	            } else if (selectedItem.equals("2")) {
					option = new NlineOption(stateManager,1);
	            } else if (selectedItem.equals("3")) {
					option = new NlineOption(stateManager,2);
	            }
				stateManager.setOption(option);
	        }
		}
	}

}
