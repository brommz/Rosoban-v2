package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Player;
import model.Pos.Dir;

import view.MazeView;

public class PlayHandler implements KeyListener {
	private MazeView view;
	
	public PlayHandler(MazeView view) {
		this.view = view;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(view.getMaze().getPlayer() == null) return;
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT:
			view.getMaze().getPlayer().move(Dir.WEST);
			break;

		case KeyEvent.VK_RIGHT:
			view.getMaze().getPlayer().move(Dir.EAST);
			break;

		case KeyEvent.VK_UP:
			view.getMaze().getPlayer().move(Dir.NORTH);
			break;
			
		case KeyEvent.VK_DOWN:
			view.getMaze().getPlayer().move(Dir.SOUTH);
			break;
		default:
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
