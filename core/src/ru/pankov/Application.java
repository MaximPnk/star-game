package ru.pankov;

import com.badlogic.gdx.Game;

import ru.pankov.screen.GameScreen;

public class Application extends Game {
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

}
