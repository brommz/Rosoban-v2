package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Config;
import main.Sound;

import controller.GameStatus;

public class ScorePanel extends JPanel implements Runnable {
	private JLabel text;
	
	public ScorePanel() {
		/*JLabel text = new JLabel("User: mydew | Lvl: 50 | Score:");
		add(text);*/
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		String status =
				Config.info[0] + GameStatus.getLvl() + Config.info[1] + GameStatus.getLvlScore() + Config.info[2] + GameStatus.getLvlAllScore() + Config.info[3] + GameStatus.getPlayNumber();
		
		g.drawString(status, 10,10);
	}

	@Override
	public void run() {
		while(true) {
			if(GameStatus.isTrialAll()) {
				Sound.playSound(Config.files[3] + "gameover.wav");
				JOptionPane.showMessageDialog(this, GameStatus.status());
				System.exit(0);
			}
			
			repaint();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
