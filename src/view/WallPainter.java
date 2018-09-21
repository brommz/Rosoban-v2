package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import model.Element;

public class WallPainter extends ElementPainter {
	/*klasa Singleton*/
	public static final ElementPainter INSTANCE = new WallPainter();

	@Override
	public void paintComponent(Graphics2D g, Element e) {
		super.paint(g);
		// TODO Auto-generated method stub
		int gridwidth = MazeView.gridwidth;
		int gridheight = MazeView.gridheight;
		int xpos = e.getPos().getX();
		int ypos = e.getPos().getY();
		int xpaint = xpos * gridwidth;
		int ypaint = ypos * gridheight;
		
		/*g.setColor(Color.BLACK);
		g.fillRect(xpaint, ypaint, gridwidth, gridwidth);*/
		
		ImageIcon icon = new ImageIcon("img/wall.png");
		g.drawImage(icon.getImage(), xpaint, ypaint, gridwidth, gridheight, this);
	}
}
