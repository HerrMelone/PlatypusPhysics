package scripts;

import java.awt.Color;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.NumberFormatter;

import engine.SimulationObject;
import engine.SimulationScene;
import engine.SimulationSidebar;
import engine.SimulationWindow;
import engine.Vector2;
import engine.main;
import engine.ClampingFilter;
import engine.Input;
import engine.IntFilter;
import engine.MouseButton;
import engine.PrimitiveType;

public class Program {

	public static void Start(){
		SimulationScene main = SimulationScene.createScene("Kreisbewegung");
		SimulationScene.loadScene(main);
		
		GUIHandler.setup();
		RotationHandler.setup();
		RotationHandler.setupHelperGUI();
	}
	
	public static void Update(){
		RotationHandler.updateValues();
		RotationHandler.updateHelperGUI();
	}
	
	public static void FixedUpdate(){
		RotationHandler.updateInputs();
		RotationHandler.updateRotation();
		GUIHandler.updateOutputs();
	}
	
}
