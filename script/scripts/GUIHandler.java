package scripts;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import engine.SimulationInput;
import engine.SimulationOutput;
import engine.SimulationSidebar;
import engine.SimulationWindow;

public class GUIHandler {
	
	public static SimulationSidebar inputs;
	public static SimulationSidebar outputs;
	
	public static SimulationInput mass;
	public static SimulationInput duration;
	public static SimulationInput radius;
	
	public static SimulationOutput speed;
	public static SimulationOutput angleSpeed;
	public static SimulationOutput force;

	public static void setup() {
		inputs = SimulationWindow.addSidebarLeft("Einstellbare Größen", 6);
		outputs = SimulationWindow.addSidebarRight("Messwerte", 7);
		
		mass = inputs.addInput(0, "Masse (in g):", 100, 1000);
		duration = inputs.addInput(2, "Umlaufdauer (in ms):", 1000, 10000);
		radius = inputs.addInput(4, "Radius (in cm):", 100, 300);
		
		speed = outputs.addOutput(0, "Bahngeschwindigkeit:", 20, "m/s");
		angleSpeed = outputs.addOutput(2, "Winkelgeschwindigkeit:", 30, " 1/s");
		force = outputs.addOutput(4, "Zentripetalkraft:", 100, " N");
	}
	
	public static void updateOutputs() {
		speed.value.setText(String.valueOf(Math.round(RotationHandler.speed * 100.0) / 100.0));
		angleSpeed.value.setText(String.valueOf(Math.round(RotationHandler.angleSpeed * 100.0) / 100.0));
		force.value.setText(String.valueOf(Math.round(RotationHandler.force * 100.0) / 100.0));
		
		//inputs.setPreferredSize(new Dimension(800,inputs.getHeight()));
	}

}
