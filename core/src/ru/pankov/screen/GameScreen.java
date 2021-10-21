package ru.pankov.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.BaseScreen;

public class GameScreen extends BaseScreen {

    Texture backgroundImg;
    Texture coinImg;
    Vector2 v = new Vector2(1, 1);
    boolean up = true, right = true;

    @Override
    public void show() {
        super.show();
        backgroundImg = new Texture("background.jpg");
        coinImg = new Texture("coin.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(backgroundImg, 0, 0, 512, 1024);

        batch.draw(coinImg, v.x, v.y);
        if (v.x + coinImg.getWidth() >= Gdx.graphics.getWidth() || v.x <= 0) {
            right = !right;
        }
        if (v.y + coinImg.getHeight() >= Gdx.graphics.getHeight() || v.y <= 0) {
            up = !up;
        }
        v.x = right ? v.x + 2 : v.x - 2;
        v.y = up ? v.y + 6 : v.y - 6;

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        coinImg.dispose();
        backgroundImg.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        v.x = screenX;
        v.y = Gdx.graphics.getHeight() - screenY;
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
