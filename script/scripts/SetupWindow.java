package scripts;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SetupWindow extends JFrame {
	
	
	public SetupWindow() {
		this.setTitle("Versuchsaufbau");
		this.setSize(700, 550);
		this.add(new SetupImage());
		this.setVisible(true);
	}

}
