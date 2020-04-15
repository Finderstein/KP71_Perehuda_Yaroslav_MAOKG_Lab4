package perehuda;

import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Thread.sleep;

public class Scene extends JFrame implements ActionListener, KeyListener {
	Clock clock;
	boolean rotate = false; // Rotate now?
	boolean left = false; // True if rotate left, false if rotate right

	public Scene() throws InterruptedException {
		super("Clock"); // Frame title

		clock = new Clock(); // Initiate clock
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

		add(canvas3D);
		canvas3D.addKeyListener(this); // To rotate clock

		Timer timer = new Timer(50, this);
		timer.start();

		// Build clock
		BranchGroup scene = clock.createSceneGraph(); // Main things happens here
		SimpleUniverse universe = new SimpleUniverse(canvas3D);
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(scene);

		// Configure our scene
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);

		// Update time every second
		while (true) {
			sleep(1000);
			clock.updateClock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Scene();
	}

	// Rotate now?
	@Override
	public void actionPerformed(ActionEvent e) {
		if(rotate) {
			clock.rotate(left);
		}
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) { }

	// Rotating left and right with left arrow and right arrow
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT || keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
			rotate = true; // Rotate now!

			// True if rotate left, false if rotate right
			if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) left = true;
			else left = false;
		}
	}

	// Rotating left and right with left arrow and right arrow
	@Override
	public void keyReleased(KeyEvent keyEvent) {
		if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT || keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
			rotate = false; // Do not rotate!
		}
	}
}
