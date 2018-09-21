package model;
import model.Pos.Dir;


public class Box extends Element implements ElementVisitor {
	public Box(MazeManager maze, Pos pos) {
		super(maze, pos);
	}

	public Box clone(MazeManager newMaze) {
		return new Box(newMaze, pos);
	}
	
	@Override
	public boolean visitEnter(Element visitor, Dir dir) {
		/*wizyta gracza - musi sprawdzic czy moze byc przesuniete*/
		if(visitor instanceof Player) {
			if(((Player) visitor).isHulk()) {
				((Player) visitor).setHulk(false);
				return maze.remove(this);
			}
			
			boolean canEnter = maze.canEnterCheck(this, this.getPos().move(dir), dir);
			boolean canLeave = maze.canLeaveCheck(this, this.getPos(), dir);
			boolean canBePushed = canEnter && canLeave;
			return canBePushed;
		}
		/*wizyta docelowego elementu*/
		else if(visitor instanceof Target) return true;
		return false;//pozostale przypadki*/
	}
	
	@Override
	public void visitNotify(Element visitor, Dir dir) {
		/*zawiadomienie od elementu gracza*/
		if(visitor instanceof Player) this.move(dir);
	}
	
	public boolean isWon() {
		return maze.isAt(Target.class, getPos());
	}
	
}
