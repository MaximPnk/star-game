package ru.pankov.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pankov.math.Rect;
import ru.pankov.math.Rnd;
import ru.pankov.pool.EnemyPool;
import ru.pankov.sprite.EnemySpaceship;

public class EnemyGenerator {

    private static final float GENERATE_INTERVAL = 2;

    private static final float SMALL_HEIGHT = 0.1f;
    private static final float SMALL_BULLET_HEIGHT = 0.01f;
    private static final int SMALL_BULLET_DAMAGE = 1;
    private static final float SMALL_BULLET_INTERVAL = 2f;
    private static final int SMALL_HP = 1;
    private static final int SMALL_SCORE = 100;

    private static final float MEDIUM_HEIGHT = 0.15f;
    private static final float MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final int MEDIUM_BULLET_DAMAGE = 5;
    private static final float MEDIUM_BULLET_INTERVAL = 3f;
    private static final int MEDIUM_HP = 5;
    private static final int MEDIUM_SCORE = 250;

    private static final float BIG_HEIGHT = 0.2f;
    private static final float BIG_BULLET_HEIGHT = 0.04f;
    private static final int BIG_BULLET_DAMAGE = 10;
    private static final float BIG_BULLET_INTERVAL = 4f;
    private static final int BIG_HP = 10;
    private static final int BIG_SCORE = 500;

    private final Vector2 smallV = new Vector2(0f, -0.2f);
    private final Vector2 smallBulletV = new Vector2(0f, -0.3f);
    private final Vector2 mediumV = new Vector2(0f, -0.1f);
    private final Vector2 mediumBulletV = new Vector2(0f, -0.25f);
    private final Vector2 bigV = new Vector2(0f, -0.05f);
    private final Vector2 bigBulletV = new Vector2(0f, -0.2f);

    private final static float SCORE_INCREMENT = 0.25f;

    private float generateTimer = GENERATE_INTERVAL;

    private final EnemyPool enemyPool;
    private final Rect worldBounds;
    private final TextureRegion bulletRegion;

    private final TextureRegion[] smallRegions;
    private final TextureRegion[] mediumRegions;
    private final TextureRegion[] bigRegions;

    private int level;

    public EnemyGenerator(EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        bulletRegion = atlas.findRegion("bulletEnemy");
        smallRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2,2);
        mediumRegions = Regions.split(atlas.findRegion("enemy1"), 1, 2,2);
        bigRegions = Regions.split(atlas.findRegion("enemy2"), 1, 2,2);
        level = 1;
    }

    public void generate(float delta, int score) {
        level = Math.max(level, score / (int) (1 + 2000 * (level * SCORE_INCREMENT)) + 1);

        if ((generateTimer += delta) >= GENERATE_INTERVAL - level * SCORE_INCREMENT / 5) {
            generateTimer = 0;
            EnemySpaceship enemy = enemyPool.get();
            double type = Math.random();
            if (type < 0.6) {
                enemy.set(
                        smallRegions,
                        smallV,
                        SMALL_HEIGHT,
                        (int) (SMALL_HP * (1 + level * SCORE_INCREMENT)),
                        bulletRegion,
                        SMALL_BULLET_HEIGHT,
                        smallBulletV,
                        (int) (SMALL_BULLET_DAMAGE * (1 + level * SCORE_INCREMENT)),
                        SMALL_BULLET_INTERVAL,
                        (int) (SMALL_SCORE * (1 + level * SCORE_INCREMENT))
                );
            } else if (type < 0.9) {
                enemy.set(
                        mediumRegions,
                        mediumV,
                        MEDIUM_HEIGHT,
                        (int) (MEDIUM_HP * (1 + level * SCORE_INCREMENT)),
                        bulletRegion,
                        MEDIUM_BULLET_HEIGHT,
                        mediumBulletV,
                        (int) (MEDIUM_BULLET_DAMAGE * (1 + level * SCORE_INCREMENT)),
                        MEDIUM_BULLET_INTERVAL,
                        (int) (MEDIUM_SCORE * (1 + level * SCORE_INCREMENT))

                );
            } else {
                enemy.set(
                        bigRegions,
                        bigV,
                        BIG_HEIGHT,
                        (int) (BIG_HP * (1 + level * SCORE_INCREMENT)),
                        bulletRegion,
                        BIG_BULLET_HEIGHT,
                        bigBulletV,
                        (int) (BIG_BULLET_DAMAGE * (1 + level * SCORE_INCREMENT)),
                        BIG_BULLET_INTERVAL,
                        (int) (BIG_SCORE * (1 + level * SCORE_INCREMENT))

                );
            }
            enemy.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + enemy.getHalfWidth(),
                    worldBounds.getRight() - enemy.getHalfWidth()
            );
            enemy.setBottom(worldBounds.getTop());
        }
    }

    public int getLevel() {
        return level;
    }
}
