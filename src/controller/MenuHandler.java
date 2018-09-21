package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import controller.PlayListenerInterface;

public class MenuHandler implements ActionListener {
	private JPanel view;
	private int ID = -1;
	private List _listeners = new ArrayList<>();
	
	public MenuHandler(JPanel panel, int ID) {
		this.view = panel;
		this.ID = ID;
		this.addEventListener(new GameStatus(view));
	};
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(ID) {
		case 1:/*new*/
			fireNewGame();
		break;
		
		case 2:/*load*/
			fireLoadGame();
		break;
		
		case 3:/*restart*/
			fireRestart();
		break;
		
		case 4:/*exit*/
			System.exit(0);
		break;
		
		default:
		}
	}
	
	public synchronized void addEventListener(PlayListenerInterface listener)  {
		_listeners.add(listener);
	}
	
	public synchronized void removeEventListener(PlayListenerInterface listener) {
		_listeners.remove(listener);
	}
	
	private synchronized void fireNewGame() {
		EventObject event = new EventObject(this);
	    Iterator i = _listeners.iterator();
	    while(i.hasNext()) {
	    	((PlayListenerInterface) i.next()).NewGame(event);
	    }
	}
	
	private synchronized void fireLoadGame() {
		EventObject event = new EventObject(this);
	    Iterator i = _listeners.iterator();
	    while(i.hasNext()) {
	    	((PlayListenerInterface) i.next()).LoadGame(event);
	    }
	}
	
	private synchronized void fireRestart() {
		EventObject event = new EventObject(this);
	    Iterator i = _listeners.iterator();
	    while(i.hasNext()) {
	    	((PlayListenerInterface) i.next()).Restart(event);
	    }
	}
}
