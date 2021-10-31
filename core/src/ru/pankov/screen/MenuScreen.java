package ru.pankov.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.BaseButton;
import ru.pankov.base.BaseScreen;
import ru.pankov.math.Rect;
import ru.pankov.sprite.Background;
import ru.pankov.sprite.ExitButton;
import ru.pankov.sprite.PlayButton;
import ru.pankov.sprite.Star;

public class MenuScreen extends BaseScreen {

    private final Game game;
    private final int STAR_COUNT = 128;

    private TextureAtlas atlas;
    private Texture bgImg;
    private Background bg;
    private Star[] stars;
    private BaseButton exitButton;
    private BaseButton playButton;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bgImg = new Texture("textures/bg_v3.jpg");
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        bg = new Background(bgImg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        exitButton = new ExitButton(atlas);
        playButton = new PlayButton(atlas, game);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        bg.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);
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
    }

    private void draw() {
        bg.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        exitButton.draw(batch);
        playButton.draw(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
        bgImg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        exitButton.touchDown(touch, pointer, button);
        playButton.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        exitButton.touchUp(touch, pointer, button);
        playButton.touchUp(touch, pointer, button);
        return false;
    }

}
