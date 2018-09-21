package main;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.MazeView;
import view.ScorePanel;
import controller.GameStatus;
import controller.PlayListenerInterface;

public class RosobanFrame extends JFrame implements WindowListener {
	private int x, y;
	public String title;
	public String lang;
	private Menu menu;
	private JPanel centerPanel;
	private JPanel scorePanel;
	
	public RosobanFrame(/*JFrame ff*/) throws IOException {
		/*ff.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				RosobanFrame.this.setVisible(true);
			}
		}	
		);*/
		
		loadConfig();
		Config.setLang(lang);
		
		setTitle(title);
	    setSize(300, 300);

	    setLayout(new BorderLayout());
	    
	    centerPanel = new MazeView();
	    add(centerPanel, BorderLayout.CENTER);
	    Thread thr = new Thread((Runnable)centerPanel);
	    thr.start();
	    
	    scorePanel = new ScorePanel();
	    add(scorePanel, BorderLayout.PAGE_END);
	    Thread thr2 = new Thread((Runnable)scorePanel);
	    thr2.start();
	    
	    menu = new Menu(centerPanel);
	    setJMenuBar(menu);
	    
	    centerPanel.addComponentListener(new ComponentAdapter() {
	    	public void componentResized(ComponentEvent e) {
	    	}
	    });
	    
	    this.addWindowListener(this);
	}
	
	private void loadConfig() throws FileNotFoundException {
		File file = new File(Config.files[1]);
	    Scanner in = new Scanner(file);
	    
	    title = in.nextLine();
	    x = in.nextInt();
	    y = in.nextInt();
	    lang = in.next();
	    in.close();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		JOptionPane.showMessageDialog(this, GameStatus.status());
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
