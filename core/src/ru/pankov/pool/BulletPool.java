package ru.pankov.pool;

import ru.pankov.base.SpritePool;
import ru.pankov.sprite.Bullet;

public class BulletPool extends SpritePool<Bullet> {

    @Override
    protected Bullet create() {
        return new Bullet();
    }

}
