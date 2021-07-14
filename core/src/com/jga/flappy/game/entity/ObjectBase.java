package com.jga.flappy.game.entity;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Circle;

public class ObjectBase extends InputAdapter {

    protected float x;
    protected float y;

    protected float radius = 1f;

    protected Circle bounds;

    public ObjectBase() {
        bounds = new Circle(x, y, radius);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setSize(float radius) {
        this.radius = radius;
        updateBounds();
    }

    private void updateBounds() {
        bounds.set(x, y, radius);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public Circle getBounds() {
        return bounds;
    }
}
