package ru.pankov.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.Ship;
import ru.pankov.math.Rect;
import ru.pankov.pool.BulletPool;
import ru.pankov.pool.ExplosionPool;

public class EnemySpaceship extends Ship {

    private static final float BULLET_VOLUME = 0.05f;
    private static final float START_V_Y = -0.25f;
    private static final float EXPLOSION_VOLUME = 0.05f;

    private final Vector2 startV;
    private final Vector2 gameV;

    private int score;

    public EnemySpaceship(BulletPool bulletPool, Rect worldBounds, Sound bulletSound, ExplosionPool explosionPool, Sound explosionSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        v = new Vector2();
        this.bulletSound = bulletSound;
        bulletV = new Vector2();
        bulletVolume = BULLET_VOLUME;
        bulletPos = new Vector2();
        startV = new Vector2(0, START_V_Y);
        gameV = new Vector2();
        this.explosionPool = explosionPool;
        this.explosionSound = explosionSound;
        explosionVolume = EXPLOSION_VOLUME;
    }

    @Override
    public void update(float delta) {
        if (!ready && getTop() <= worldBounds.getTop()) {
            v.set(gameV);
            currentBulletDelta = bulletInterval;
            ready = true;
        }
        bulletPos.set(pos.x, getBottom());
        super.update(delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v,
            float height,
            int hp,
            TextureRegion bulletRegion,
            float bulletHeight,
            Vector2 bulletV,
            int bulletDamage,
            float bulletInterval,
            int score
    ) {
        ready = false;
        this.regions = regions;
        this.gameV.set(v);
        this.v.set(startV);
        setProportionalByHeight(height);
        this.hp = hp;
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(bulletV);
        this.bulletDmg = bulletDamage;
        this.bulletInterval = bulletInterval;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
