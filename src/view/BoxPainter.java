package view;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import model.Element;

public class BoxPainter extends ElementPainter {
	/*klasa Singleton*/
	public static final ElementPainter INSTANCE = new BoxPainter();

	@Override
	public void paintComponent(Graphics2D g, Element e) {
		super.paint(g);
		
		int gridwidth = MazeView.gridwidth;
		int gridheight = MazeView.gridheight;
		int xpos = e.getPos().getX();
		int ypos = e.getPos().getY();
		int xpaint = xpos * gridwidth;
		int ypaint = ypos * gridheight;
		
		ImageIcon icon = new ImageIcon("img/box.png");
		g.drawImage(icon.getImage(), xpaint, ypaint, gridwidth, gridheight, this);	
	}
}
