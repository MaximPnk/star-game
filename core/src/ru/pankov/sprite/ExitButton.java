package ru.pankov.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.pankov.base.BaseButton;
import ru.pankov.math.Rect;

public class ExitButton extends BaseButton {

    private final float HEIGHT = 0.15f;

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }

    @Override
    public void resize(Rect worldBounds) {
        setProportionalSize(HEIGHT);
        setRight(worldBounds.getRight() - worldBounds.getWidth() * 0.15f);
        setBottom(worldBounds.getBottom() + worldBounds.getHeight() * 0.1f);
    }
}
