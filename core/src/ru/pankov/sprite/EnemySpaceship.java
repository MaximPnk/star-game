package ru.pankov.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.Ship;
import ru.pankov.math.Rect;
import ru.pankov.pool.BulletPool;

public class EnemySpaceship extends Ship {

    public EnemySpaceship(BulletPool bulletPool, Rect worldBounds, Sound bulletSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        v = new Vector2(0, 0);
        this.bulletSound = bulletSound;
        bulletV = new Vector2();
        bulletVolume = 0.05f;
        bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {
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
            float bulletInterval
    ) {
        this.regions = regions;
        this.v.set(v);
        setProportionalSize(height);
        this.hp = hp;
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(bulletV);
        this.bulletDmg = bulletDamage;
        this.bulletInterval = bulletInterval;
    }

}
