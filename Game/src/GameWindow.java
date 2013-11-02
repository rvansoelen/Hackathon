import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameWindow extends JPanel {

	Sprite Megaman;

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Megaman = new Sprite(this, ii);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("MegaMan.png"));
        Megaman = ii.getImage();
		g2d.drawImage(Megaman, 0, 0, 200, 200, 0, 0, 50, 50, Color.blue, null); 

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mario Game");
		frame.add(new GameWindow());
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.blue);
	
		
	}
	
	public GameWindow() {
		createSprites();
	}

	void createSprites() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("MegaMan.png"));
		Megaman = new Sprite(this, ii);
	}
}