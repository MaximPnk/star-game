package ru.pankov.pool;

import com.badlogic.gdx.audio.Sound;

import ru.pankov.base.SpritePool;
import ru.pankov.math.Rect;
import ru.pankov.sprite.EnemySpaceship;

public class EnemyPool extends SpritePool<EnemySpaceship> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound bulletSound;

    private final ExplosionPool explosionPool;
    private final Sound explosionSound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound bulletSound, ExplosionPool explosionPool, Sound explotionSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
        this.explosionPool = explosionPool;
        this.explosionSound = explotionSound;
    }

    @Override
    protected EnemySpaceship create() {
        return new EnemySpaceship(bulletPool, worldBounds, bulletSound, explosionPool, explosionSound);
    }

    public void updateAllActive(float delta) {
        for (EnemySpaceship s : active) {
            if (!s.isDestroyed()) {
                s.update(delta);
            }
        }
    }
}
