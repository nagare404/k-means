package Java_Second;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class WidthlineComboBox extends JComboBox<String>{
	StateManager stateManager;	
	Option option;
	static final String[] n = {"1","2","3"};
	public WidthlineComboBox(StateManager stateManager) {
		
		super(n);
		addItemListener(new WidthlineListener());
		this.stateManager = stateManager;

	}
	
	class WidthlineListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
	            String selectedItem = (String) e.getItem();
				stateManager.removeOption(option);
	            if (selectedItem.equals("1")) {
					option = new WidthlineOption(stateManager,1);
	            } else if (selectedItem.equals("2")) {
					option = new WidthlineOption(stateManager,2);
	            } else if (selectedItem.equals("3")) {
					option = new WidthlineOption(stateManager,3);
	            }
				stateManager.setOption(option);
	        }
		}
	}

}
