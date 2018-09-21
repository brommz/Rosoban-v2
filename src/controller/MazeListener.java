package controller;

import java.util.EventObject;

public interface MazeListener {
	 public void mazeChanged(EventObject e); /*kiedy Maze sie zmienia - jakis ruch etc.*/
	 public void mazeWon(EventObject e); /*ze przeszedl plansze*/
}