package ru.pankov.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.pankov.base.Sprite;
import ru.pankov.math.Rect;

public class GameOver extends Sprite {

    private static final float SIZE = 0.05f;
    private static final float TEXT_MARGIN = 0.25f;

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setProportionalByHeight(worldBounds.getHeight() * SIZE);
        pos.set(0, worldBounds.getTop() - worldBounds.getHeight() * TEXT_MARGIN);
    }
}
