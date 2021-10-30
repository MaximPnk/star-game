package ru.pankov.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.Sprite;
import ru.pankov.math.Rect;

public class Spaceship extends Sprite {

    private final float SHIP_V = 0.01f;
    private final int LEFT = 29;
    private final int RIGHT = 32;

    private Vector2 v;
    private boolean leftPressed;
    private boolean rightPressed;
    private Rect worldBounds;
    private int leftPointer;
    private int rightPointer;

    public Spaceship(TextureAtlas atlas) {
        TextureRegion region = atlas.findRegion("main_ship");
        region.setRegion(region.getRegionX(), region.getRegionY(), region.getRegionWidth() / 2, region.getRegionHeight());
        v = new Vector2(0, 0);
        regions = new TextureRegion[1];
        regions[0] = region;
        setLeft(0 - getHalfWidth());
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setProportionalSize(0.15f);
        setBottom(worldBounds.getBottom());
        setLeft(getLeft());
    }

    @Override
    public void update(float delta) {
        checkBounds();
        pos.add(v);
    }

    private void checkBounds() {
        if (getLeft() < worldBounds.getLeft()) {
            keyUp(LEFT);
        }
        if (getRight() > worldBounds.getRight()) {
            keyUp(RIGHT);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < 0) {
            keyDown(LEFT);
            leftPointer = pointer;
        } else {
            keyDown(RIGHT);
            rightPointer = pointer;
        }
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            keyUp(LEFT);
        }
        if (pointer == rightPointer) {
            keyUp(RIGHT);
        }
        return true;
    }

    public boolean keyDown(int keycode) {
        if (keycode == LEFT && !leftPressed) {
            leftPressed = true;
            v.sub(SHIP_V, 0);
            return true;
        } else if (keycode == RIGHT && !rightPressed) {
            rightPressed = true;
            v.add(SHIP_V, 0);
            return true;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        if (keycode == LEFT && leftPressed) {
            leftPressed = false;
            v.add(SHIP_V, 0);
            return true;
        } else if (keycode == RIGHT && rightPressed) {
            rightPressed = false;
            v.sub(SHIP_V, 0);
            return true;
        }
        return false;
    }

}
