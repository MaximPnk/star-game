package ru.pankov;

import com.badlogic.gdx.Game;

import ru.pankov.screen.MenuScreen;

public class Application extends Game {
	
	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}

}
