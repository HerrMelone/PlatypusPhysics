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
	public static SimulationObject s;
	public static void Start(){
		SimulationSidebar inputs = SimulationWindow.addSidebarLeft("Einstellbare Größen", 3);
		
		//Define input GUI
		inputs.getRow(0).add(new JLabel("Masse (in g): "));
		
//		NumberFormat format = NumberFormat.getIntegerInstance();
//		NumberFormatter formatter = new NumberFormatter(format);
//		formatter.setMinimum(100);
//		formatter.setMaximum(1000);
//		JTextField tf_mass = new JFormattedTextField(formatter);
		JTextField tf_mass = new JTextField();
		tf_mass.setHorizontalAlignment(JTextField.CENTER);
		tf_mass.setText("300");
		tf_mass.setEditable(true);
		AbstractDocument ad = (AbstractDocument)tf_mass.getDocument();
		ad.setDocumentFilter(new IntFilter());
		ad.putProperty("max", 1000);
		ad.putProperty("min", 100);
		tf_mass.addFocusListener(new ClampingFilter(100, 1000, tf_mass));
		inputs.getRow(0).add(tf_mass);
		
		Hashtable l_mass = new Hashtable();
		l_mass.put(100, new JLabel("100"));
		l_mass.put(500, new JLabel("500"));
		l_mass.put(1000, new JLabel("1000"));
		JSlider s_mass = new JSlider(100,1000);
		s_mass.setLabelTable(l_mass);
		s_mass.setPaintLabels(true);
		s_mass.setMinorTickSpacing(100);
		s_mass.setMajorTickSpacing(500);
		s_mass.setPaintTicks(true);
		inputs.getRow(1).add(s_mass);
		
		s = new SimulationObject(Color.DARK_GRAY, 100, 100, PrimitiveType.Rectangle);
		SimulationScene.activeScene.addObject(s, 300, 300);
	}
	
	public static void Update(){
		
	}
	public static int deg = 0;
	
	public static void FixedUpdate(){

		if(Input.getMouseButton(MouseButton.LEFT)){
			s.setLocalRotation(Input.getMousePosition(), 361, 200);
			System.out.println(s.getLocalRotation(Input.getMousePosition()));
		}
		
		if(Input.getKey('r')){
			s.rotateAround(new Vector2(main.WIDTH / 2, main.HEIGHT /2), 0.5f);
		}
	}
	
}
