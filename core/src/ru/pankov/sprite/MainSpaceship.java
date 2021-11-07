package ru.pankov.sprite;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.Ship;
import ru.pankov.math.Rect;
import ru.pankov.pool.BulletPool;
import ru.pankov.pool.ExplosionPool;

public class MainSpaceship extends Ship {

    private static final float HEIGHT = 0.12f;
    private static final float V_DELTA = 0.5f;
    private static final float BOTTOM_MARGIN = 0.04f;
    private static final float BULLET_V_Y = 0.5f;
    private static final float BULLET_INTERVAL = 0.15f;
    private static final float BULLET_VOLUME = 0.2f;
    private static final int INVALID_POINTER = -1;
    private static final float EXPLOSION_VOLUME = 0.3f;

    private boolean leftPressed;
    private boolean rightPressed;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    public MainSpaceship(TextureAtlas atlas, BulletPool bulletPool, Sound bulletSound, ExplosionPool explosionPool, Sound explosionSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        v = new Vector2();
        setLeft(0 - getHalfWidth());
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV = new Vector2(0, BULLET_V_Y);
        this.bulletSound = bulletSound;
        bulletInterval = BULLET_INTERVAL;
        bulletVolume = BULLET_VOLUME;
        bulletPos = new Vector2();
        ready = true;
        this.explosionPool = explosionPool;
        this.explosionSound = explosionSound;
        explosionVolume = EXPLOSION_VOLUME;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setProportionalSize(HEIGHT);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getTop());
        checkBounds();
        super.update(delta);
    }

    private void checkBounds() {
        if (getLeft() < worldBounds.getLeft()) {
            keyUp(A);
        }
        if (getRight() > worldBounds.getRight()) {
            keyUp(D);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < 0) {
            keyDown(A);
            leftPointer = pointer;
        } else {
            keyDown(D);
            rightPointer = pointer;
        }
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            keyUp(A);
            leftPointer = INVALID_POINTER;
            return true;
        }
        if (pointer == rightPointer) {
            keyUp(D);
            rightPointer = INVALID_POINTER;
            return true;
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        if ((keycode == A || keycode == LEFT) && !leftPressed) {
            leftPressed = true;
            v.sub(V_DELTA, 0);
            return true;
        } else if ((keycode == D || keycode == RIGHT) && !rightPressed) {
            rightPressed = true;
            v.add(V_DELTA, 0);
            return true;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        if ((keycode == A || keycode == LEFT) && leftPressed) {
            leftPressed = false;
            v.add(V_DELTA, 0);
            return true;
        } else if ((keycode == D || keycode == RIGHT) && rightPressed) {
            rightPressed = false;
            v.sub(V_DELTA, 0);
            return true;
        }
        return false;
    }

}
