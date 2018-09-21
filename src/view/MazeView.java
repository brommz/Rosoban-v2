package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.text.html.StyleSheet.BoxPainter;

import model.MazeManager;
import model.Hulk;
import model.StartPosition;
import model.Teleport;
import model.Wall;
import model.Box;
import model.Player;
import model.Target;
import model.Wall;

import controller.MazeViewListener;
import controller.PlayHandler;

public class MazeView extends JPanel implements Runnable {
	private MazeManager maze;
	public MazeViewListener mazeViewListener;
	private final PlayHandler play;
	public static int gridwidth = 20;
	public static int gridheight = 20;
	private double mazeheight, mazewidth;
	
	private static final Map<Class<? extends Element>,ElementPainter> painters = new HashMap<>();
	
	static {
		painters.put((Class<? extends Element>) Wall.class, WallPainter.INSTANCE);
		painters.put((Class<? extends Element>) Player.class, PlayerPainter.INSTANCE);
		painters.put((Class<? extends Element>) Box.class, view.BoxPainter.INSTANCE);
		painters.put((Class<? extends Element>) Target.class, TargetPainter.INSTANCE);
		painters.put((Class<? extends Element>) Hulk.class, HulkPainter.INSTANCE);
		painters.put((Class<? extends Element>) Teleport.class, TeleportPainter.INSTANCE);
		painters.put((Class<? extends Element>) StartPosition.class, StartPositionPainter.INSTANCE);
	}

	public MazeView() {
		setFocusable(true);
		setBackground(Color.red);
		
		maze = new MazeManager();
		
		mazeViewListener = new MazeViewListener(this);
		play = new PlayHandler(this);
		maze.addEventListener(mazeViewListener);
		
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	gridwidth = (int)(getSize().getWidth() / maze.xsize);
            	gridheight = (int)(getSize().getHeight() / maze.ysize);
            }
        });
		
		this.addKeyListener(play);
		
	}
	
	public void loadMap(String fileName) {
		maze.loadMap(fileName);
			mazeheight = maze.xsize * gridwidth;
			mazewidth = maze.ysize * gridwidth;
		
			gridwidth = (int)(getSize().getWidth() / maze.xsize);
        	gridheight = (int)(getSize().getHeight() / maze.ysize);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(maze.isEmpty() == false) {
			Graphics2D g2d = (Graphics2D) g;
			
			Iterator<model.Element> iterator = maze.iterator();
			while(iterator.hasNext()) {
				model.Element e = iterator.next();
				
				if(e.getClass() == Player.class) {
					//playerPainter = (PlayerPainter)getMazeElementPainter(e.getClass());
					getMazeElementPainter(e.getClass()).paintComponent(g2d,e);
				}
				else {
					getMazeElementPainter(e.getClass()).paintComponent(g2d,e);
				}
			}
		}
	}
	
	private ElementPainter getMazeElementPainter(Class<? extends model.Element> class1) {
		ElementPainter painter;
		painter = null;
		if(painters.containsKey(class1)) painter = painters.get(class1); 
		return painter;
	}
	
	public MazeManager getMaze() {
		return maze;
	}

	@Override
	public void run() {
		while(true) {
			sleep(350);
			repaint();
		}
	}

	public void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


