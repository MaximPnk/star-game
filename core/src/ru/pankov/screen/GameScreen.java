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
import ru.pankov.pool.ExplosionPool;
import ru.pankov.sprite.Background;
import ru.pankov.sprite.Bullet;
import ru.pankov.sprite.EnemySpaceship;
import ru.pankov.sprite.GameOver;
import ru.pankov.sprite.MainSpaceship;
import ru.pankov.sprite.NewGameButton;
import ru.pankov.sprite.Star;
import ru.pankov.utils.EnemyGenerator;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private static final float BULLET_HEIGHT_COLLISION = 0.7f;
    private static final float BULLET_WIDTH_COLLISION = 1f;
    private static final float ENEMY_HEIGHT_COLLISION = 0.5f;
    private static final float ENEMY_WIDTH_COLLISION = 0.9f;

    private boolean gameOver;

    private Texture bgImg;
    private GameOver gameOverMsg;
    private NewGameButton newGameButton;
    private Background bg;
    private TextureAtlas atlas;
    private Star[] stars;
    private MainSpaceship mainSpaceship;
    private BulletPool bulletPool;
    private Sound laserSound;
    private Sound bulletSound;
    private EnemyPool enemyPool;
    private EnemyGenerator enemyGenerator;

    private ExplosionPool explosionPool;
    private Sound explosionSound;

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        bgImg = new Texture("textures/bg_v3.jpg");
        bg = new Background(bgImg);
        gameOverMsg = new GameOver(atlas);
        newGameButton = new NewGameButton(atlas, this);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas);

        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        mainSpaceship = new MainSpaceship(atlas, bulletPool, laserSound, explosionPool, explosionSound);
        enemyPool = new EnemyPool(bulletPool, worldBounds, bulletSound, explosionPool, explosionSound);
        enemyGenerator = new EnemyGenerator(enemyPool, worldBounds, atlas);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        bg.resize(worldBounds);
        gameOverMsg.resize(worldBounds);
        newGameButton.resize(worldBounds);
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
        explosionPool.freeDestroyed();
    }

    private void update(float delta) {
        if (mainSpaceship.isDestroyed()) {
            gameOver = true;
        }
        for (Star star : stars) {
            star.update(delta);
        }
        if (!gameOver) {
            mainSpaceship.update(delta);
            enemyPool.updateAllActive(delta);
            enemyGenerator.generate(delta);
            checkCollisions();
        }
        bulletPool.updateAllActive(delta);
        explosionPool.updateAllActive(delta);
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
        if (!gameOver) {
            mainSpaceship.draw(batch);
            enemyPool.drawAllActive(batch);
        } else {
            gameOverMsg.draw(batch);
            newGameButton.draw(batch);
        }
        bulletPool.drawAllActive(batch);
        explosionPool.drawAllActive(batch);
    }

    public void newGame() {
        gameOver = false;
        mainSpaceship.reset();
        enemyPool.destroyAll();
        bulletPool.destroyAll();
        explosionPool.destroyAll();
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
        explosionPool.dispose();
        explosionSound.dispose();
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
        newGameButton.touchDown(touch, pointer, button);
        return mainSpaceship.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        newGameButton.touchUp(touch, pointer, button);
        return mainSpaceship.touchUp(touch, pointer, button);
    }

}
