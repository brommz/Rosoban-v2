package model;

import model.Pos.Dir;


public class StartPosition extends Element implements ElementVisitor{

	public StartPosition(MazeManager maze, Pos pos) {
		super(maze, pos);
	}

	public StartPosition clone(MazeManager newMaze){
		return new StartPosition(newMaze, pos);
	}
	
}

