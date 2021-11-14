package ru.pankov.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.pankov.base.Sprite;
import ru.pankov.math.Rect;

public class HealthBackground extends Sprite {

    public HealthBackground(TextureRegion... regions) {
        super(regions);
    }

    @Override
    public void resize(Rect worldBounds) {
        setProportionalByWidth(worldBounds.getWidth());
        setLeft(worldBounds.getLeft());
        setBottom(worldBounds.getBottom());
    }

}
