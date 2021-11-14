package ru.pankov.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.pankov.base.BaseButton;
import ru.pankov.math.Rect;
import ru.pankov.screen.GameScreen;

public class PlayButton extends BaseButton {

    private final Game game;
    private final float HEIGHT = 0.2f;

    public PlayButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }

    @Override
    public void resize(Rect worldBounds) {
        setProportionalByHeight(HEIGHT);
        setLeft(worldBounds.getLeft() + worldBounds.getWidth() * 0.15f);
        setBottom(worldBounds.getBottom() + worldBounds.getHeight() * 0.1f);
    }
}
