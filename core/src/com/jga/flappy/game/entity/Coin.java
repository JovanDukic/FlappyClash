package com.jga.flappy.game.entity;

import com.badlogic.gdx.utils.Pool;
import com.jga.flappy.game.config.GameConfig;

public class Coin extends ObjectBase implements Pool.Poolable {

    private float speedX = GameConfig.COIN_ACC_X;

    public Coin() {
        setSize(GameConfig.COIN_HALF_SIZE);
    }

    public void update(float delta) {
        float newX = x - speedX * delta;
        setPosition(newX, y);
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    @Override
    public void reset() {
        speedX = GameConfig.COIN_ACC_X;
    }
}
