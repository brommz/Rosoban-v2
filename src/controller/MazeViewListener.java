package controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import view.MazeView;


import main.Config;
import main.Sound;

public class MazeViewListener implements MazeListener {
	private final MazeView view;
	private List _listeners = new ArrayList<>();
	
	public MazeViewListener(MazeView view) {
		this.view = view;
		this.addEventListener(new GameStatus(view));
	}
	
	public void mazeChanged(EventObject e) {
		view.repaint();
		/*tutaj watek animujacy postac*/
	}

	/*gdy nadeszla wygrana*/
	@Override
	public void mazeWon(EventObject e) {
		GameStatus.sumScore();
		if(!GameStatus.isAllWon()) {
			GameStatus.nextLvl();
			try {
				GameStatus.saveStatus();
			} catch(FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Sound.playSound(Config.files[3] + "levelup.wav");
			fireLoadGame();
			GameStatus.setLvlScore(0);
		}
		else {
			Sound.playSound(Config.files[3] + "gamewon.wav");
			JOptionPane.showMessageDialog(view, GameStatus.status());
			System.exit(0);
		}
	}
	
	public synchronized void addEventListener(PlayListenerInterface listener)  {
		_listeners.add(listener);
	}
	
	public synchronized void removeEventListener(PlayListenerInterface listener) {
		_listeners.remove(listener);
	}
	
	private synchronized void fireLoadGame() {
		EventObject event = new EventObject(this);
	    Iterator i = _listeners.iterator();
	    while(i.hasNext()) {
	    	((PlayListenerInterface) i.next()).LoadGame(event);
	    }
	}

}
