package scripts;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderUpdate implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent arg0) {
		RotationHandler.updateInputs();
		RotationHandler.updateValues();
		GUIHandler.updateOutputs();
	}

}
