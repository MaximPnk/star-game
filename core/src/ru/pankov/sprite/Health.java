package ru.pankov.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.pankov.base.Sprite;
import ru.pankov.math.Rect;

public class Health extends Sprite {

    public Health(TextureRegion... regions) {
        super(regions);
    }

    public void update(float delta, Rect worldBounds, int hp) {
        setWidth(worldBounds.getWidth() * hp / 100);
        setLeft(worldBounds.getLeft());
    }

    @Override
    public void resize(Rect worldBounds) {
        setProportionalByWidth(worldBounds.getWidth());
        setLeft(worldBounds.getLeft());
        setBottom(worldBounds.getBottom());
    }

}
