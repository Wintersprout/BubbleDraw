import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JButton;

public class BubblePanel extends JPanel {
	Random rand = new Random();
	ArrayList<Bubble> bubbleList;
	int size = 25;
	//Animation variables
	Timer timer;
	int delay = 20;
	
	public BubblePanel() {
		timer = new Timer(delay, new BubbleListener());
		bubbleList = new ArrayList<Bubble>();
		setBackground(Color.BLACK);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("Pause")) {
					timer.stop();
					btn.setText("Start");
				}
				else {
					timer.start();
					btn.setText("Pause");
				}
			}
		});
		panel.add(btnPause);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleList = new ArrayList<Bubble>();
				repaint();
			}
		});
		panel.add(btnClear);
		//testBubbles();
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		timer.start();
	}
	public void paintComponent(Graphics canvas) {
		super.paintComponent(canvas);
		for (Bubble b : bubbleList) {
			b.draw(canvas);
		}
	}
	public void testBubbles() {
		for (int i = 0; i < 100; i++) {
			int x = rand.nextInt(600);
			int y = rand.nextInt(400);
			int size = rand.nextInt(50);
			bubbleList.add(new Bubble(x, y, size));
		}
		repaint();
	}
	private class BubbleListener extends MouseAdapter implements ActionListener {
		public void mousePressed(MouseEvent e) {
			bubbleList.add(new Bubble(e.getX(), e.getY(), size));
			repaint();
		}
		public void mouseDragged(MouseEvent e) {
			bubbleList.add(new Bubble(e.getX(), e.getY(), size));
			repaint();
		}
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(System.getProperty("os.name").startsWith("Mac"))
				size += e.getUnitsToScroll();
			else
				size -= e.getUnitsToScroll();
			if (size < 3)
				size = 3;
		}
		public void actionPerformed(ActionEvent e) {
			for (Bubble b : bubbleList)
				b.update();
			repaint();
		}
	}
	private class Bubble {
		private int x, y, size;
		private int xSpeed, ySpeed;
		private final int MAX_SPEED = 5; 
		private Color color;
		
		public Bubble(int newX, int newY, int newSize) {
			x = newX;
			y = newY;
			size = newSize;
			color = new Color(rand.nextInt(256),
					rand.nextInt(256),
					rand.nextInt(256),
					rand.nextInt(256));
			xSpeed = rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
			ySpeed = rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
		}
		public void draw(Graphics canvas) {
			canvas.setColor(color);
			canvas.fillOval(x - size/2, y - size/2, size, size);
		}
		public void update() {
			x += xSpeed;
			y += ySpeed;
		}
	}
	
}
