package ru.pankov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import ru.pankov.screen.MenuScreen;

public class Application extends Game {

	private Music music;

	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
		music.setVolume(0.1f);
		music.setLooping(true);
		music.play();

		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		music.dispose();
		super.dispose();
	}
}
