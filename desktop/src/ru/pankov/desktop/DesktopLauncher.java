package ru.pankov.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.pankov.Application;

public class DesktopLauncher {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 700;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.resizable = false;
		System.out.println(Gdx.graphics);

		new LwjglApplication(new Application(), config);
	}
}
