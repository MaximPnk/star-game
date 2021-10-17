package ru.pankov;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Application extends ApplicationAdapter {
	SpriteBatch batch;
	Texture backgroundImg;
	Texture coinImg;
	int x = 1, y = 1;
	boolean up = true, right = true;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImg = new Texture("background.jpg");
		coinImg = new Texture("coin.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.BLUE);
		batch.begin();
		batch.draw(backgroundImg, 0, 0, 512, 1024);

		batch.draw(coinImg, x, y);
		if (x + 64 >= 400 || x <= 0) {
			right = !right;
		}
		if (y + 64 >= 700 || y <= 0) {
			up = !up;
		}
		x = right ? x + 2 : x - 2;
		y = up ? y + 6 : y - 6;

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		coinImg.dispose();
	}
}
