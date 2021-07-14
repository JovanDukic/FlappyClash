package com.jga.flappy.game.entity;

import com.badlogic.gdx.math.Rectangle;

public class SpriteBase {

    protected float x;
    protected float y;

    protected float width = 1f;
    protected float height = 1f;

    protected Rectangle bounds;

    public SpriteBase() {
        bounds = new Rectangle(x, y, width, height);
    }


    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        updateBounds();
    }

    private void updateBounds() {
        bounds.setPosition(x, y);
        bounds.setSize(width, height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

}
