package view;

import java.awt.Color;
import java.awt.Graphics2D;

import model.Element;

public class HulkPainter extends ElementPainter {
	public static final ElementPainter INSTANCE = new HulkPainter();

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
		g.setColor(Color.GREEN);
		g.fillRect(xpaint, ypaint, gridwidth, gridheight);
	}
}
