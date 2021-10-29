package ru.pankov.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.BaseScreen;
import ru.pankov.math.Rect;
import ru.pankov.sprite.Background;
import ru.pankov.sprite.Spaceship;
import ru.pankov.sprite.Star;

public class GameScreen extends BaseScreen {

    private final int STAR_COUNT = 64;

    private Texture bgImg;
    private Background bg;
    private TextureAtlas atlas;
    private Star[] stars;
    private Spaceship spaceship;

    @Override
    public void show() {
        super.show();
        bgImg = new Texture("bg_v3.jpg");
        bg = new Background(bgImg);
        atlas = new TextureAtlas("mainAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        spaceship = new Spaceship(atlas);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        bg.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        spaceship.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        update(delta);
        draw();
        batch.end();
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        spaceship.update(delta);
    }

    private void draw() {
        bg.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        spaceship.draw(batch);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        bgImg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return spaceship.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return spaceship.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return spaceship.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return spaceship.touchUp(touch, pointer, button);
    }

}
