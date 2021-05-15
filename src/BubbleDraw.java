import javax.swing.JFrame;

public class BubbleDraw extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Reno's BubbleDraw App");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().add(new BubblePanel());
		frame.setSize(new java.awt.Dimension(400, 400));
		frame.setVisible(true);
	}

}
