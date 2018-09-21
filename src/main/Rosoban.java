package main;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

public class Rosoban {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FirstWindow window1;
	            RosobanFrame window2;
				try {
					/*window1 = new FirstWindow();
					window1.setVisible(true);
					window1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);*/
					
					window2 = new RosobanFrame(/*window1*/);
					window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window2.setVisible(true);
				} 
				catch(FileNotFoundException e) {
					System.out.println("File not found");
				} 
				catch(IOException e) {
					System.out.println("IO Exc");
					e.printStackTrace();
				}
				catch(Exception e) {
					System.out.println("NON-defined exc");
				}
	               
	            }
	         });
	   }	
}