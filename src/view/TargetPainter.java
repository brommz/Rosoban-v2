package view;

import java.awt.Color;
import java.awt.Graphics2D;

import model.Element;

public class TargetPainter extends ElementPainter {
	public static final ElementPainter INSTANCE = new TargetPainter();

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
		g.setColor(Color.PINK);
		g.fillRect(xpaint, ypaint, gridwidth, gridheight);
	}
}
