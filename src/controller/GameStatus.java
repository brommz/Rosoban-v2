package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.EventObject;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import main.Config;
import main.Sound;

import view.MazeView;

 	public class GameStatus implements PlayListenerInterface {
 		private static final int maxPlayNumber = 3; /*liczba razy, ze moze przejsc poziomy w sumie na cala gre*/
 		private static int playNumber = 0; /*ile razy juz gra*/ 
 		private static int lvl = 0; /*ktory to level*/
 		private static int lvlScore = 0;
 		private static int lvlAllScore = 0; /*wynik wszystkich lvl*/
 		private static int lvlMax = -1;
 		private JPanel view;
 		
 	public GameStatus(JPanel panel) {
 		this.view = panel;
 	}
 		
	public static void clearAll() {
		playNumber = 0;
		lvl = 0;
		lvlScore = 0;
		lvlAllScore = 0;
		playNumber = 0;
	}
	
	private void newGame() throws FileNotFoundException {
		GameStatus.clearAll();
		GameStatus.loadMaxLvl();
		make(Config.maps + 0);
	}
	
	private void load() throws FileNotFoundException {
		GameStatus.clearAll();
		if(GameStatus.lvl != 0)
			GameStatus.saveStatus();
		GameStatus.loadStatus();
		GameStatus.loadMaxLvl();
		make(Config.maps + GameStatus.lvl);
	}
	
	private void restart() {
		if(GameStatus.lvlScore != 0) {
			make(Config.maps + GameStatus.lvl);
			GameStatus.playNumber++;
		}
	}
	
	private void make(String str) {
		MazeView x = (MazeView)view;
	    x.loadMap(str);
	}
	
	public static void loadStatus() throws FileNotFoundException {
		File file = new File(Config.files[0]);
	    Scanner in = new Scanner(file);
	    lvl = in.nextInt();
	    lvlAllScore = in.nextInt();
	    in.close();
	}
	
	public static void saveStatus() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(Config.files[0]);
		writer.println(lvl + "\n" + lvlAllScore + "\n" + lvlMax);
		writer.close();
	}
	
	public static void loadMaxLvl() throws FileNotFoundException {
		File file = new File(Config.files[2] + "lvl.txt");
		Scanner in = new Scanner(file);
		lvlMax = in.nextInt();
		in.close();
	}
	
	public static void setLvlScore(int i) {
		lvlScore = i;
	}
	
	public static void nextLvl() {
		lvl++;
	}
	
	public static void sumScore() {
		lvlAllScore += lvlScore;
		lvlScore = 0;
	}
	
	public static int getLvl() {
		return GameStatus.lvl;
	}
	
	public static int getLvlScore() {
		return GameStatus.lvlScore;
	}	
	public static int getLvlAllScore() {
		return GameStatus.lvlAllScore;
	}
	
	public static int getPlayNumber() {
		return GameStatus.playNumber;
	}	
	
	public static int getMaxPlayNumber() {
		return GameStatus.maxPlayNumber;
	}

	@Override
	public void NewGame(EventObject e) {
		try {
			Sound.playSound(Config.files[3] + "newgame.wav");
			newGame();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void LoadGame(EventObject e) {
		try {
			load();
			GameStatus.lvlScore = 1000;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void Restart(EventObject e) {
		restart();
		GameStatus.lvlScore = 0;
	}
	
	public static boolean isAllWon() {
		return (lvl == lvlMax) && !isTrialAll();
	}
	
	public static boolean isTrialAll() {
		return (playNumber == maxPlayNumber);
	}

	public static String status() {
		if(isAllWon()) return "GAME WON!!!";
		else if(isTrialAll()) return "Game Over";
		return "Thank you for playing";
	}
}
