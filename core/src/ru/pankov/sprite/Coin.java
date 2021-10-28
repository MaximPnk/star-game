package ru.pankov.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.Sprite;
import ru.pankov.math.Rect;

public class Coin extends Sprite {

    Vector2 finishPos = new Vector2(pos);
    Vector2 v = new Vector2(0, 0);

    public Coin(Texture texture) {
        super(new TextureRegion(texture));
    }



    @Override
    public void resize(Rect worldBounds) {
        setProportionalSize(0.1f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        pos.add(v);
        if (pos.dst(finishPos) <= v.len()) {
            v.set(0, 0);
            pos.set(finishPos);
        }
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        finishPos.set(touch);
        v.set(finishPos.cpy().sub(pos).scl(0.03f));
        return super.touchDown(touch, pointer, button);
    }

}
