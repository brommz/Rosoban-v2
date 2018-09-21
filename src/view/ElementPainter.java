package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import javax.swing.ImageIcon;

import model.Element;

public abstract class ElementPainter extends Component {
	public abstract void paintComponent(Graphics2D g, Element e);
}
