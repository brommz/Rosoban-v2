package model;

import model.Pos.Dir;


public class Wall extends Element implements ElementVisitor {

	public Wall(MazeManager maze, Pos pos) {
		super(maze, pos);
	}

	public Wall clone(MazeManager newMaze) {
		return new Wall(newMaze, pos);
	}
	
	@Override
	public boolean visitEnter(Element visitor, Dir dir) {
		return false;
	}
	
}
