package model;

import model.Pos.Dir;


public class Teleport extends Element implements ElementVisitor{

	public Teleport(MazeManager maze, Pos pos) {
		super(maze, pos);
	}

	public Teleport clone(MazeManager newMaze){
		return new Teleport(newMaze, pos);
	}
	
	@Override
	public boolean visitEnter(Element visitor, Dir dir) {
		if(visitor instanceof Player) {
			((Player) visitor).moveStart();
		}
		return false;
	}
	
}

