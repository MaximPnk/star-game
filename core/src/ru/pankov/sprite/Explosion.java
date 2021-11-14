package ru.pankov.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.Sprite;

public class Explosion extends Sprite {

    private static final float ANIMATION_INTERVAL = 0.01f;

    private float animationTimer;

    public Explosion(TextureAtlas atlas) {
        super(atlas.findRegion("explosion"), 9, 9, 74);
    }

    @Override
    public void update(float delta) {
        animationTimer += delta;
        if (animationTimer >= ANIMATION_INTERVAL) {
            animationTimer = 0;
            if (++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        frame = 0;
        super.destroy();
    }

    public void set(Vector2 pos, float height) {
        this.pos.set(pos);
        setProportionalByHeight(height);
    }

}
