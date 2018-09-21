package model;

import model.Pos.Dir;


public class Hulk extends Element implements ElementVisitor{

	public Hulk(MazeManager maze, Pos pos) {
		super(maze, pos);
	}

	public Hulk clone(MazeManager newMaze){
		return new Hulk(newMaze, pos);
	}
	
	@Override
	public boolean visitEnter(Element visitor, Dir dir) {
		if(visitor instanceof Player) { 
			((Player) visitor).setHulk(true);
			return maze.remove(this);
		}
		return false;
	}
	
}

