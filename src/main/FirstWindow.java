package main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstWindow extends JFrame {
	public FirstWindow() {
		JPanel panel = new view.FirstWindow(this);
		setSize(new Dimension(50, 250));
		add(panel);
	}
}
