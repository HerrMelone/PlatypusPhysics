package scripts;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import engine.SimulationInput;
import engine.SimulationOutput;
import engine.SimulationSidebar;
import engine.SimulationWindow;

public class GUIHandler {
	
	public static SimulationSidebar inputs;
	public static SimulationSidebar outputs;
	public static SimulationSidebar extras;
	
	public static SimulationInput mass;
	public static SimulationInput duration;
	public static SimulationInput radius;
	
	public static SimulationOutput speed;
	public static SimulationOutput angleSpeed;
	public static SimulationOutput force;
	
	public static JCheckBox randomness;
	public static int factor;
	
	public static JButton setup;
	public static Image img;
	

	public static void setup() {
		
		inputs = SimulationWindow.addSidebarLeft("Einstellbare Größen", 6);
		outputs = SimulationWindow.addSidebarRight("Messwerte", 7);
		extras = SimulationWindow.addSidebarLeft("Extras", 3);
		
		mass = inputs.addInput(0, "Masse (in g):", 100, 1000);
		duration = inputs.addInput(2, "Umlaufdauer (in ms):", 1500, 10000);
		radius = inputs.addInput(4, "Radius (in cm):", 100, 300);
		
		speed = outputs.addOutput(0, "Bahngeschwindigkeit:", 20, "m/s");
		angleSpeed = outputs.addOutput(2, "Winkelgeschwindigkeit:", 30, " 1/s");
		force = outputs.addOutput(4, "Zentripetalkraft:", 100, " N");
		
		mass.slider.addChangeListener(new SliderUpdate());
		duration.slider.addChangeListener(new SliderUpdate());
		radius.slider.addChangeListener(new SliderUpdate());
		
		mass.tf.getDocument().addDocumentListener(new TextFieldUpdate());
		duration.tf.getDocument().addDocumentListener(new TextFieldUpdate());
		radius.tf.getDocument().addDocumentListener(new TextFieldUpdate());
		
		randomness = new JCheckBox("Messungenauigkeit", true);
		extras.getRow(0).add(randomness);
		setup = new JButton("Versuchsaufbau");
		extras.getRow(1).add(setup);
		
		setup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SetupWindow();
			}
		});

	}
	
	public static void updateOutputs() {
		if(randomness.isSelected())
			factor = ThreadLocalRandom.current().nextInt(95, 106);
		else
			factor = 100;
		speed.value.setText(String.valueOf(Math.round((RotationHandler.speed / 100 * factor) * 100.0) / 100.0));
		if(randomness.isSelected())
			factor = ThreadLocalRandom.current().nextInt(95, 106);
		angleSpeed.value.setText(String.valueOf(Math.round((RotationHandler.angleSpeed / 100 * factor) * 100.0) / 100.0));
		if(randomness.isSelected())
			factor = ThreadLocalRandom.current().nextInt(95, 106);
		force.value.setText(String.valueOf(Math.round((RotationHandler.force / 100 * factor) * 100.0) / 100.0));
		
		//inputs.setPreferredSize(new Dimension(800,inputs.getHeight()));
	}

}
