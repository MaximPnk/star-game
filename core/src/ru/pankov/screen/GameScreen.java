package ru.pankov.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.base.BaseScreen;
import ru.pankov.math.Rect;
import ru.pankov.pool.BulletPool;
import ru.pankov.pool.EnemyPool;
import ru.pankov.sprite.Background;
import ru.pankov.sprite.Bullet;
import ru.pankov.sprite.EnemySpaceship;
import ru.pankov.sprite.MainSpaceship;
import ru.pankov.sprite.Star;
import ru.pankov.utils.EnemyGenerator;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private static final float BULLET_HEIGHT_COLLISION = 0.7f;
    private static final float BULLET_WIDTH_COLLISION = 1f;
    private static final float ENEMY_HEIGHT_COLLISION = 0.5f;
    private static final float ENEMY_WIDTH_COLLISION = 0.9f;

    private Texture bgImg;
    private Background bg;
    private TextureAtlas atlas;
    private Star[] stars;
    private MainSpaceship mainSpaceship;
    private BulletPool bulletPool;
    private Sound laserSound;
    private Sound bulletSound;
    private EnemyPool enemyPool;
    private EnemyGenerator enemyGenerator;

    @Override
    public void show() {
        super.show();
        bgImg = new Texture("textures/bg_v3.jpg");
        bg = new Background(bgImg);
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        mainSpaceship = new MainSpaceship(atlas, bulletPool, laserSound);
        enemyPool = new EnemyPool(bulletPool, worldBounds, bulletSound);
        enemyGenerator = new EnemyGenerator(enemyPool, worldBounds, atlas);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        bg.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainSpaceship.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        update(delta);
        draw();
        freeAllDestroyed();
        batch.end();
    }

    public void freeAllDestroyed() {
        bulletPool.freeDestroyed();
        enemyPool.freeDestroyed();
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        mainSpaceship.update(delta);
        bulletPool.updateAllActive(delta);
        enemyPool.updateAllActive(delta);
        checkCollisions();
        enemyGenerator.generate(delta);
    }

    private void checkCollisions() {
        for (EnemySpaceship e : enemyPool.getAllActive()) {
            if (e.isIntersect(mainSpaceship, ENEMY_WIDTH_COLLISION, ENEMY_HEIGHT_COLLISION)) {
                e.destroy();
                mainSpaceship.damage(e.getHp() * 2);
            }
        }

        for (Bullet b : bulletPool.getAllActive()) {
            if (b.getOwner() == mainSpaceship) {
                for (EnemySpaceship e : enemyPool.getAllActive()) {
                    if (e.isIntersect(b, BULLET_WIDTH_COLLISION, BULLET_HEIGHT_COLLISION)) {
                        e.damage(b.getDamage());
                        b.destroy();
                    }
                }
            } else {
                if (mainSpaceship.isIntersect(b, 1, 0.7f)) {
                    mainSpaceship.damage(b.getDamage());
                    b.destroy();
                }
            }
        }
    }

    private void draw() {
        bg.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainSpaceship.draw(batch);
        enemyPool.drawAllActive(batch);
        bulletPool.drawAllActive(batch);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        bgImg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        laserSound.dispose();
        enemyPool.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return mainSpaceship.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return mainSpaceship.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return mainSpaceship.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return mainSpaceship.touchUp(touch, pointer, button);
    }

}
