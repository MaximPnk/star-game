package ru.pankov.sprite;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import ru.pankov.base.Ship;
import ru.pankov.base.Sprite;
import ru.pankov.math.Rect;
import ru.pankov.pool.BulletPool;

public class MainSpaceship extends Ship {

    private static final float V_DELTA = 0.5f;
    private static final float BOTTOM_MARGIN = 0.04f;

    private boolean leftPressed;
    private boolean rightPressed;
    private int leftPointer = -1;
    private int rightPointer = -1;

    public MainSpaceship(TextureAtlas atlas, BulletPool bulletPool, Sound bulletSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        v = new Vector2(0, 0);
        setLeft(0 - getHalfWidth());
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV = new Vector2(0, 0.5f);
        this.bulletSound = bulletSound;
        bulletInterval = 0.15f;
        bulletVolume = 0.2f;
        bulletPos = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setProportionalSize(0.12f);
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
            leftPointer = -1;
            return true;
        }
        if (pointer == rightPointer) {
            keyUp(D);
            rightPointer = -1;
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
