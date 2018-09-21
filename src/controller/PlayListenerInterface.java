package controller;

import java.util.EventObject;

public interface PlayListenerInterface {
	 public void NewGame(EventObject e);
	 public void LoadGame(EventObject e);
	 public void Restart(EventObject e);
}