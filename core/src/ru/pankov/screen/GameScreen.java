package ru.pankov.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.BaseScreen;

public class GameScreen extends BaseScreen {

    Texture backgroundImg;
    Texture coinImg;
    Vector2 coinPos;
    Vector2 coinCenter;
    Vector2 coinV;
    Vector2 finishPos;

    @Override
    public void show() {
        super.show();
        backgroundImg = new Texture("background.jpg");
        coinImg = new Texture("coin.png");
        coinPos = new Vector2(1, 1);
        coinCenter = new Vector2();
        coinV = new Vector2(0, 0);
        finishPos = new Vector2(1, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(backgroundImg, 0, 0, 512, 1024);

        coinCenter.set(coinPos).add(coinImg.getWidth() / 2, coinImg.getHeight() / 2);
        if (finishPos.dst(coinCenter) <= coinV.len()) {
            coinV.set(0,0);
        }
        coinPos.add(coinV);
        batch.draw(coinImg, coinPos.x, coinPos.y);

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
        finishPos.set(screenX, Gdx.graphics.getHeight() - screenY);
        coinV.set(finishPos.cpy().sub(coinPos.cpy().add(coinImg.getWidth()/2, coinImg.getHeight()/2))).nor();
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
