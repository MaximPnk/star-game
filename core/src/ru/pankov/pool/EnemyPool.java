package ru.pankov.pool;

import com.badlogic.gdx.audio.Sound;

import ru.pankov.base.SpritePool;
import ru.pankov.math.Rect;
import ru.pankov.sprite.EnemySpaceship;

public class EnemyPool extends SpritePool<EnemySpaceship> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound bulletSound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound bulletSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
    }

    @Override
    protected EnemySpaceship create() {
        return new EnemySpaceship(bulletPool, worldBounds, bulletSound);
    }
}
