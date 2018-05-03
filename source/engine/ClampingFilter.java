package engine;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class ClampingFilter implements FocusListener {

	int max = 0;
	int min = 0;
	JTextField target;
	
	public ClampingFilter(int min, int max, JTextField target) {
		this.max = max;
		this.min = min;
		this.target = target;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if(Integer.parseInt(target.getText()) < min){
//			target.setText(Integer.toString(min));
			target.setText(Integer.toString(max));
			

			System.out.println(target.getText());
		}
		
		if(Integer.parseInt(target.getText()) > max){
			target.setText(Integer.toString(max));
			System.out.println("teee");
		}
			
		
	}

}
