import javax.swing.JFrame;

public class GameWindow {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mario Game");
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}