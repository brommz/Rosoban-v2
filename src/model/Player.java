package model;

import controller.GameStatus;
import model.Pos.Dir;

public class Player extends Element implements ElementVisitor {
	private Dir dir;
	private final Pos startPos;
	private boolean Hulkmode;
	private int moveNumber = 0;
	
	public Player(MazeManager maze, Pos pos) {
		super(maze,pos);
		dir = Dir.STILL;
		Hulkmode = false;
		startPos = pos.clone();
	}

	public Player clone(MazeManager newMaze) {
		return new Player(newMaze, pos);
	}

	public boolean move(Dir dir) {
		this.dir = dir;
		boolean moving = super.move(dir);
		if(moving) {
			moveNumber++; 
		}
		GameStatus.setLvlScore(moveNumber);
		return moving;
	}
	
	public boolean moveStart() {
		boolean moving = super.moveTo(startPos);
		if(moving) {
			moveNumber += 5;
		}
		GameStatus.setLvlScore(moveNumber);
		return moving;
	}
	
	@Override
	public boolean visitEnter(Element visitor, Dir dir) {
		if(visitor instanceof Target) return true;
		return false;
	}
	
	public Dir getDir() {
		return dir;
	}
	
	public boolean isHulk() {
		return Hulkmode;
	}
	
	public void setHulk(boolean what) {
		Hulkmode = what;
	}
	
}
