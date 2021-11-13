package ru.pankov.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.pankov.base.BaseButton;
import ru.pankov.math.Rect;
import ru.pankov.screen.GameScreen;

public class NewGameButton extends BaseButton {

    private static final float SIZE = 0.035f;
    private static final float BUTTON_MARGIN = 0.3f;

    private final GameScreen gameScreen;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setProportionalSize(worldBounds.getHeight() * SIZE);
        pos.set(0, worldBounds.getBottom() + worldBounds.getHeight() * BUTTON_MARGIN);
    }

    @Override
    public void action() {
        gameScreen.newGame();
    }
}
