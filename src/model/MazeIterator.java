package model;

import java.util.Iterator;
import java.util.LinkedList;

final class MazeIterator implements Iterator<Element> {

	LinkedList<Element> list;/*lista wszystkich elementow 1wymiarowa*/
	private final Iterator<Element> iterator;

	public MazeIterator (Maze maze) {
		list = new LinkedList<Element>();
		for(LinkedList<Element>[] table : maze.array) {
			for(LinkedList<Element> elements : table) {
				for(Element e : elements) {
					/*all elementy(nawet te co na jednej pozycji)w 1 liscie*/
					list.add(e);
				}
			}
		}
		iterator = list.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Element next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}
	
}