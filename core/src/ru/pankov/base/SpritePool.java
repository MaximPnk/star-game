package ru.pankov.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritePool<T extends Sprite> {

    protected final List<T> active = new ArrayList<>();
    protected final List<T> free = new ArrayList<>();

    protected abstract T create();

    public T get() {
        T object;
        if (free.isEmpty()) {
            object = create();
        } else {
            object = free.remove(free.size() - 1);
        }
        active.add(object);
        return object;
    }

    public void updateAllActive(float delta) {
        for (T object : active) {
            if (!object.isDestroyed()) {
                object.update(delta);
            }
        }
    }

    public void drawAllActive(SpriteBatch batch) {
        for (T object : active) {
            if (!object.isDestroyed()) {
                object.draw(batch);
            }
        }
    }

    public void freeDestroyed() {
        for (int i = 0; i < active.size(); i++) {
            T object = active.get(i);
            if (object.isDestroyed()) {
                free(object);
                i--;
            }
        }
    }

    private void free(T object) {
        object.flushDestroy();
        if (active.remove(object)) {
            free.add(object);
        }
    }

    public List<T> getAllActive() {
        return active;
    }

    public void dispose() {
        active.clear();
        free.clear();
    }
}
