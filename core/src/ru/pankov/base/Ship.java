package ru.pankov.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.math.Rect;
import ru.pankov.pool.BulletPool;
import ru.pankov.sprite.Bullet;

public class Ship extends Sprite {

    protected static final float DAMAGE_INTERVAL = 0.1f;

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected float bulletHeight = 0.012f;
    protected int bulletDmg = 1;
    protected Sound bulletSound;
    protected float bulletVolume;
    protected float currentBulletDelta;
    protected float bulletInterval;
    protected Vector2 bulletPos;
    protected boolean ready;

    protected float currentDamageDelta = DAMAGE_INTERVAL;

    protected int hp;
    protected Vector2 v;
    protected Rect worldBounds;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        if (ready) {
            if ((currentBulletDelta += delta) >= bulletInterval) {
                shoot(bulletVolume);
                currentBulletDelta = 0;
            }
        }
        pos.mulAdd(v, delta);

        currentDamageDelta += delta;
        if (currentDamageDelta >= DAMAGE_INTERVAL) {
            frame = 0;
        }
    }

    protected void shoot(float volume) {
        Bullet bullet = bulletPool.get();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, bulletHeight, bulletDmg);
        bulletSound.play(volume);
    }

    public void damage(float dmg) {
        this.hp -= dmg;
        if (this.hp <= 0) {
            destroy();
        }

        currentDamageDelta = 0;
        frame = 1;
    }

    public int getHp() {
        return hp;
    }
}
