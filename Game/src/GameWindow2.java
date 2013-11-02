import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameWindow2 extends JPanel {

	Image megaman;

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		ImageIcon ii = new ImageIcon(this.getClass().getResource("MegaMan.png"));
        megaman = ii.getImage();
		g2d.drawImage(megaman, 0, 0, 200, 200, 0, 0, 10, 10, Color.CYAN, null); 

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mario Game");
		frame.add(new GameWindow2());
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}