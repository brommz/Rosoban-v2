package model;

import java.util.Iterator;
import java.util.LinkedList;

public class Maze {
	public LinkedList<Element>[][] array;//tablica 2d LinkedList
	private int xsize;
	private int ysize;

	public Maze() {
		array = null;
	}
	
	public Maze(int x, int y) {
		xsize = x;
		ysize = y;
		array = null;
		setMap();
	}

	@SuppressWarnings("unchecked")
	public void setMap() {
		array = new LinkedList[xsize][ysize];
		for (int i = 0; i < xsize; i++){
			for (int j = 0; j < ysize; j++){
				array[i][j]  = new LinkedList<Element>();
			}
		}	
	}
	
	/*zwraca element o pozycji z labiryntu*/
	public/*protected*/ LinkedList<Element> getTile(Pos pos) {
		return array[pos.getX()][pos.getY()];
	}

	/*iterator listy linkedlist*/
	public Iterator<Element> iterator(){
		return new MazeIterator(this);
	}

	/*czy pozycja jest poza labiryntem*/
	public boolean posOutsideMaze(Pos pos) {
		int x = pos.getX();
		int y = pos.getY();
		return (x >= xsize || x < 0 || y >= ysize || y < 0);
	}
}