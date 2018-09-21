package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import model.Element;
import model.Player;

public class PlayerPainter extends ElementPainter {
	public static final ElementPainter INSTANCE = new PlayerPainter();
	private ImageIcon current_icon;
	static int x = 1;
	
	private PlayerPainter() {
		current_icon = new ImageIcon("img/player1.png");
	}
	
	@Override
	public void paintComponent(Graphics2D g, Element e) {
		super.paint(g);
		
		Player player = (Player)e;
		int gridwidth = MazeView.gridwidth;
		int gridheight = MazeView.gridheight;
		int xpos = e.getPos().getX();
		int ypos = e.getPos().getY();
		int xpaint = xpos * gridwidth;
		int ypaint = ypos * gridheight;
		
		g.drawImage(current_icon.getImage(), xpaint, ypaint, gridwidth, gridheight, this);
		
		if(((Player)e).isHulk()) 
			current_icon = new ImageIcon("img/player_hulk.png");
		else
			current_icon = new ImageIcon("img/player" + x + ".png");
		x++;
		if(x == 5) x = 1;
		
	}
	
}
