package ru.pankov.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.pankov.base.SpritePool;
import ru.pankov.sprite.Explosion;

public class ExplosionPool extends SpritePool<Explosion> {

    TextureAtlas atlas;

    public ExplosionPool(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    @Override
    protected Explosion create() {
        return new Explosion(atlas);
    }
}
