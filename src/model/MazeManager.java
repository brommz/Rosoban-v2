package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controller.GameStatus;
import controller.MazeListener;

import model.Box;
import model.Player;
import model.Pos;
import model.Target;
import model.Wall;

import model.Maze;

import model.Pos.Dir;

public class MazeManager {
	private Maze maze;
	public int xsize;
	public int ysize;
	private List _listeners = new ArrayList<>();
	private boolean isMazeEmpty;
	
	public MazeManager() {
	    xsize = 0;
	    ysize = 0;
	    isMazeEmpty = true;
	}

	public int getX() {
		return xsize;
	}
	
	public int getY() {
		return ysize;
	}
	
	public boolean isEmpty() {
		return isMazeEmpty;
	}
	
	public Iterator<Element> iterator() {
		return this.maze.iterator();
	}
	
	public void loadMap(String fileName) {
		maze = null;
		File file = new File(fileName);
		int heightmax = 0, widthmax = 0;

		ArrayList<String> strings = new ArrayList();
		File fileTemp = file;
		Scanner in;
		try {
			in = new Scanner(fileTemp);
			while(in.hasNext()) {
				String line = in.nextLine();
				strings.add(line);
				if(line.length() > widthmax) widthmax = line.length();
				heightmax++;
			}
			
		} 
		catch(FileNotFoundException e1) {
			e1.printStackTrace();
		}
		xsize = widthmax;
		ysize = heightmax;
		maze = new Maze(xsize, ysize);
		
		int y = 0;
		FileReader fileRead = null;
		ArrayList<Pos> targets = new ArrayList<Pos>();
		try {
			fileRead = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fileRead);
			String str;
			while((str = bfr.readLine()) != null) {
				for(int x = 0; x < str.length(); x++) {
					char item = str.charAt(x);
					if(item == '#') {
						add(new Wall(this, new Pos(x,y)));
					}
					else if(item == '$') {
						add(new Box(this, new Pos(x,y)));
					}
					else if(item == '.') {
						add(new Target(this, new Pos(x,y)));
					}
					else if(item == '@') {
						add(new StartPosition(this, new Pos(x,y)));
						add(new Player(this, new Pos(x,y)));
						//System.out.println("wstaw: " + x + ", " + y);
					}
					else if(item == '*') {
						add(new Hulk(this, new Pos(x,y)));
					}
					else if(item == 'T') {
						add(new Teleport(this, new Pos(x,y)));
					}
				}
				y++;
			}
			bfr.close();
			if(maze != null) isMazeEmpty = false; else isMazeEmpty = true;
		}
		catch(FileNotFoundException e) {
		}
		catch(IOException e) {
		}
	}
	
	/*dodaje element*/
	public boolean add(Element e) {
		Pos pos = e.getPos();
		if(e instanceof Player) {
			if(getPlayer() != null) return false;
		}
		if(canEnterCheck(e, pos, Dir.STILL)) {
			maze.getTile(pos).add(e);
			notify(e, pos, Dir.STILL);
			fireMazeChanged();
			
			return true;
		}
		return false;
	}
	
	/*usuwa element*/
	public boolean remove(Element e) {
		Pos pos = e.getPos();
		boolean success = maze.getTile(pos).remove(e);
		if(success) {
			fireMazeChanged();
		}
		return success;
	}
	
	public Element getPlayer() {
		Iterator<Element> i = iterator();
		while(i.hasNext()){
			Element next = i.next();
			if(next instanceof Player) {
				return next;
			}
		}
		return null;
	}
	
	/*rusz element e na pozycje newPos, kierunek podany*/
	public boolean move(Element e, Pos newPos, Dir dir) {
		Pos pos = e.getPos();
		if(pos == newPos) return true;
		/*jezeli moze zejsc z pola oraz wejsc na nowe*/
		if((canLeaveCheck(e, pos, dir)) && canEnterCheck(e, newPos, dir)) {
			maze.getTile(pos).remove(e);//usuwa element ze starego pola
			maze.getTile(newPos).add(e);//dodaje element na nowe pole
			notify(e, newPos, dir);
			
			fireMazeChanged();//mazeChanged();
			
			boolean won = true;
			Iterator<model.Element> iterator = maze.iterator();
			while(iterator.hasNext()) {
				Element etemp = iterator.next();
				won &= etemp.isWon();
			}
			if(won) fireWon();
			
			return true;
		}
		return false;
	}

	/*czy element e moze isc na pozycje pos idac w kierunku dir*/
	public boolean canEnterCheck(Element e, Pos pos, Dir dir) {
		boolean canEnter = false;
		if(!posOutsideMaze(pos)) {
			canEnter = true;
			List<Element> ll = (List<Element>) maze.getTile(pos).clone();
			ll.remove(e);
			/*sprawdza wszystkie element na pozycji pos w tablicy maze
			 * bo np. moze byc target (true) oraz na nim box(false)*/
			for(Element v : ll) {
				canEnter &= v.visitEnter(e, dir);
			}
		}
		return canEnter;
	}
	
	/*czy element e moze opuscic pozycje pos idac w kierunku dir*/
	public boolean canLeaveCheck(Element e, Pos pos, Dir dir) {
		boolean canLeave = true;
		List<Element> ll = (List<Element>) maze.getTile(pos).clone();
		for (Element v : ll) {
			canLeave &= v.visitLeave(e, dir);
		}
		return canLeave;
	}
	
	/*zawiadom, ze element e na pozycje pos*/
	private void notify(Element e, Pos pos, Dir dir) {
		if(!posOutsideMaze(pos)) {
			List<Element> ll = (List<Element>) maze.getTile(pos).clone();
			for (Element v : ll) {
				/*kazdemu obiektowi, ktory jest na pozycji pos musimy wywolac*/
				v.visitNotify(e, dir);
			}
		}
	}
	
	/*zwraca element, ktory jest teraz na tej pozycji najwazniejszy - na gorze (top)*/
	public Element getTopObj(Pos pos) {
		if(posOutsideMaze(pos)) return null;
		List<Element> ll = maze.getTile(pos);
		if(ll.size() != 0) return ll.get(ll.size()-1);
		return null;
	}
	
	/*czy element jest na tej pozycji*/
	public boolean isAt(Class<? extends Element> elementClass, Pos pos){
		Iterator<Element> i = maze.getTile(pos).iterator();
		while (i.hasNext()){
			return (i.next().getClass() == elementClass);
		}
		return (false);
	}
	
	private boolean posOutsideMaze(Pos pos) {
		return maze.posOutsideMaze(pos);
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public synchronized void addEventListener(MazeListener listener)  {
		_listeners.add(listener);
	}
	
	public synchronized void removeEventListener(MazeListener listener) {
		_listeners.remove(listener);
	}
	
	/*rzuca zdarzeniem, ze cos sie na planszy zmienilo*/
	private synchronized void fireMazeChanged() {
		EventObject event = new EventObject(this);
	    Iterator i = _listeners.iterator();
	    while(i.hasNext()) {
	    	((MazeListener) i.next()).mazeChanged(event);
	    }
	}
	
	/*rzuca zdarzeniem, ze wygrano plansze*/
	private synchronized void fireWon() {
		EventObject event = new EventObject(this);
	    Iterator i = _listeners.iterator();
	    while(i.hasNext()) {
	    	((MazeListener) i.next()).mazeWon(event);
	    }
	}
	
}