package ru.pankov.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.math.Rect;
import ru.pankov.math.Rnd;
import ru.pankov.pool.EnemyPool;
import ru.pankov.sprite.EnemySpaceship;

public class EnemyGenerator {

    private static final float GENERATE_INTERVAL = 3f;

    private static final float SMALL_HEIGHT = 0.1f;
    private static final float SMALL_BULLET_HEIGHT = 0.01f;
    private static final int SMALL_BULLET_DAMAGE = 1;
    private static final float SMALL_BULLET_INTERVAL = 1f;
    private static final int SMALL_HP = 1;

    private static final float MEDIUM_HEIGHT = 0.15f;
    private static final float MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final int MEDIUM_BULLET_DAMAGE = 5;
    private static final float MEDIUM_BULLET_INTERVAL = 2.5f;
    private static final int MEDIUM_HP = 5;

    private static final float BIG_HEIGHT = 0.2f;
    private static final float BIG_BULLET_HEIGHT = 0.04f;
    private static final int BIG_BULLET_DAMAGE = 10;
    private static final float BIG_BULLET_INTERVAL = 5f;
    private static final int BIG_HP = 10;

    private final Vector2 smallV = new Vector2(0f, -0.2f);
    private final Vector2 smallBulletV = new Vector2(0f, -0.3f);
    private final Vector2 mediumV = new Vector2(0f, -0.1f);
    private final Vector2 mediumBulletV = new Vector2(0f, -0.25f);
    private final Vector2 bigV = new Vector2(0f, -0.05f);
    private final Vector2 bigBulletV = new Vector2(0f, -0.2f);

    private float generateTimer;

    private final EnemyPool enemyPool;
    private final Rect worldBounds;
    private final TextureRegion bulletRegion;

    private final TextureRegion[] smallRegions;
    private final TextureRegion[] mediumRegions;
    private final TextureRegion[] bigRegions;

    public EnemyGenerator(EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        bulletRegion = atlas.findRegion("bulletEnemy");
        smallRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2,2);
        mediumRegions = Regions.split(atlas.findRegion("enemy1"), 1, 2,2);
        bigRegions = Regions.split(atlas.findRegion("enemy2"), 1, 2,2);
    }

    public void generate(float delta) {
        if ((generateTimer += delta) >= GENERATE_INTERVAL) {
            generateTimer = 0;
            EnemySpaceship enemy = enemyPool.get();
            double type = Math.random();
            if (type < 0.6) {
                enemy.set(
                        smallRegions,
                        smallV,
                        SMALL_HEIGHT,
                        SMALL_HP,
                        bulletRegion,
                        SMALL_BULLET_HEIGHT,
                        smallBulletV,
                        SMALL_BULLET_DAMAGE,
                        SMALL_BULLET_INTERVAL
                );
            } else if (type < 0.9) {
                enemy.set(
                        mediumRegions,
                        mediumV,
                        MEDIUM_HEIGHT,
                        MEDIUM_HP,
                        bulletRegion,
                        MEDIUM_BULLET_HEIGHT,
                        mediumBulletV,
                        MEDIUM_BULLET_DAMAGE,
                        MEDIUM_BULLET_INTERVAL
                );
            } else {
                enemy.set(
                        bigRegions,
                        bigV,
                        BIG_HEIGHT,
                        BIG_HP,
                        bulletRegion,
                        BIG_BULLET_HEIGHT,
                        bigBulletV,
                        BIG_BULLET_DAMAGE,
                        BIG_BULLET_INTERVAL
                );
            }
            enemy.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + enemy.getHalfWidth(),
                    worldBounds.getRight() - enemy.getHalfWidth()
            );
            enemy.setBottom(worldBounds.getTop());
        }
    }

}
