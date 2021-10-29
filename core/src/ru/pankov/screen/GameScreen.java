package ru.pankov.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.BaseScreen;
import ru.pankov.math.Rect;
import ru.pankov.sprite.Background;
import ru.pankov.sprite.Coin;

public class GameScreen extends BaseScreen {

    Texture bgImg;
    Background bg;

    Texture coinImg;
    Coin coin;

    @Override
    public void show() {
        super.show();
        bgImg = new Texture("background.jpg");
        bg = new Background(bgImg);
        coinImg = new Texture("coin.png");
        coin = new Coin(coinImg);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        bg.resize(worldBounds);
        coin.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        bg.draw(batch);
        coin.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bgImg.dispose();
        coinImg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        super.touchDown(touch, pointer, button);
        coin.touchDown(touch, pointer, button);
        return false;
    }
}
