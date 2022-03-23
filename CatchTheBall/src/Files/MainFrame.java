package Files;

import java.awt.Image;
import javax.swing.*;

public class MainFrame extends JFrame {
	
	MainFrame(){
		
		this.add(new MainPanel());
		ImageIcon image = new ImageIcon(this.getClass().getClassLoader().getResource("ball.png").getFile());
		Image img = image.getImage();
		this.setIconImage(img);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Catch the ball");
	}
	
	public static void main(String [] args) {
		new MainFrame();
	}
	
}
