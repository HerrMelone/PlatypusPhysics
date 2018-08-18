package scripts;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SetupImage extends JPanel {
	
	Image img;
	
	public SetupImage() {
		try {
		    img = ImageIO.read(new File(System.getProperty("user.dir") + "/images/setup.jpg"));
		    
		} catch (IOException e1) {
			System.out.println(e1.toString());
		}	
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
