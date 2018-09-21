package model;

import model.Pos.Dir;


public class Target extends Element implements ElementVisitor{

	public Target(MazeManager maze, Pos pos) {
		super(maze, pos);
	}

	public Target clone(MazeManager newMaze){
		return new Target(newMaze, pos);
	}
	
	@Override
	public boolean visitEnter(Element visitor, Dir dir) {
		if(visitor instanceof Player || visitor instanceof Box) return true;
		return false;
	}
	
}
