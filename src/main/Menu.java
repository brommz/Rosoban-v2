package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import controller.MenuHandler;

import view.MazeView;

public class Menu extends JMenuBar {
	private JPanel view;
	
	public Menu(JPanel panel) {
		super();
		this.view = panel;
		
		JMenu gameMenu, helpMenu;
		JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
		
		gameMenu = new JMenu(Config.menu[4]);
		gameMenu.setMnemonic(KeyEvent.VK_G);
		
		helpMenu = new JMenu(Config.menu[5]);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		this.add(gameMenu);
		this.add(helpMenu);
		
		
		/*new*/
		menuItem1 = new JMenuItem(Config.menu[0], KeyEvent.VK_1);
		menuItem1.addActionListener(new MenuHandler(view, 1));
	
		/*load*/
		menuItem2 = new JMenuItem(Config.menu[1], KeyEvent.VK_2);
		menuItem2.addActionListener(new MenuHandler(view, 2));
		
		/*restart*/
		menuItem3 = new JMenuItem(Config.menu[2], KeyEvent.VK_3);
		menuItem3.addActionListener(new MenuHandler(view, 3));
		
		/*exit*/
		menuItem4 = new JMenuItem(Config.menu[3], KeyEvent.VK_4);
		menuItem4.addActionListener(new MenuHandler(view, 4));
	
		gameMenu.add(menuItem1);
		gameMenu.add(menuItem2);
		gameMenu.add(menuItem3);
		gameMenu.add(menuItem4);
	}
	

	
}
