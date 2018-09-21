package model;

import model.Pos.Dir;

public abstract class Element implements ElementVisitor, Cloneable {
	
	protected Pos pos;
	public final MazeManager maze;

	public Element(MazeManager maze, Pos pos) {
		this.pos = pos.clone();
		this.maze = maze;
	}

	public void delete() {
		maze.remove(this);
	}

	public Pos getPos() { return pos.clone(); }

	/*rusz obiektem w kierunku --> to wykorzystywane*/
	public boolean move(Dir dir) {
		boolean success = maze.move(this, pos.move(dir), dir);
		if(success) pos = pos.move(dir);
		return success;
	}
	
	public boolean moveTo(Pos newPos) {
		boolean success = maze.move(this, newPos, Pos.Dir.STILL);
		if(success) pos = newPos;
		return success;
	}
	
	public boolean visitLeave(Element visitor, Dir dir) {
		return true;
	}

	public boolean visitEnter(Element visitor, Dir dir) {
		return true;
	}
	
	public void visitNotify(Element visitor, Dir dir) {}
	
	public abstract Element clone(MazeManager newMaze);
	
	public boolean isWon() {
		return true;
	}

}
