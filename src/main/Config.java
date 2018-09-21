package main;

public class Config {
	public static String[] menu = { "New game", "Load game", "Restart lvl", "Exit", "Game", "Help" }; 
	public static String[] info = { "Level: ", " || LvlScore: ", " || Score: ", " || Trial: "};
	public static String[] files = { "config/status.txt", "config/config.txt", "screens/", "sound/"};
	public static String maps = "screens/screen.";
	
	private Config() {
	}
	
	public static void setLang(String lang) {
		if(lang.equals("eng")) {
			menu[0] = "New game";
			menu[1] = "Load game";
			menu[2] ="Restart lvl" ;
			menu[3] = "Exit";
			menu[4] = "Game";
			menu[5] = "Help";
			
			info[0] = "Level: ";
			info[1] = " || LvlScore: ";
			info[2] = " || Score: ";
			info[3] = " || Trial: ";
		}
		else if(lang.equals("pl")) {
			menu[0] = "Nowa gra";
			menu[1] = "Wczytaj grê";
			menu[2] ="Restart" ;
			menu[3] = "Zamknij";
			menu[4] = "Gra";
			menu[5] = "Pomoc";
			
			info[0] = "Poziom: ";
			info[1] = " || Wynik poziomu: ";
			info[2] = " || Wynik: ";
			info[3] = " || Próby: ";
		}
	}
}
