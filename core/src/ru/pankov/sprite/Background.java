package ru.pankov.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.pankov.base.Sprite;
import ru.pankov.math.Rect;

public class Background extends Sprite {

    public Background(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        setProportionalSize(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}
