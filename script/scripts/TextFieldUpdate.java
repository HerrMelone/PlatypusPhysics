package scripts;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldUpdate implements DocumentListener {

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		RotationHandler.updateInputs();
		RotationHandler.updateValues();
		GUIHandler.updateOutputs();

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		RotationHandler.updateInputs();
		RotationHandler.updateValues();
		GUIHandler.updateOutputs();

	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		RotationHandler.updateInputs();
		RotationHandler.updateValues();
		GUIHandler.updateOutputs();

	}

}
