package ru.pankov;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Application extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion region;
	int x = 0, y = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		region = new TextureRegion(img, 0, 0, 100, 100);
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.BLUE);
		batch.begin();
		batch.draw(img, x++, y++);
		batch.draw(region, 100, 100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
