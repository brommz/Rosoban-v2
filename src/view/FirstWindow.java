package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstWindow extends JPanel {
	public FirstWindow(final main.FirstWindow ff) {
		JTextField address = new JTextField("localhost");
		JTextField port = new JTextField("1314");
		JButton fir = new JButton("Po≥πcz");
		JButton sec = new JButton("Wyjdü");
		
		
		setLayout(new GridLayout(4,1));
		add(address);
		add(port);
		add(fir);
		add(sec);
		
		fir.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ff.setVisible(false);
					}
				}
		);
		
		sec.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				}
		);
		
	}
}
