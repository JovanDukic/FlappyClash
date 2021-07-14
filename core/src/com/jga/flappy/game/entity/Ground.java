package com.jga.flappy.game.entity;

import com.jga.flappy.game.config.GameConfig;

public class Ground extends SpriteBase {

    private float speedX = GameConfig.GROUND_SPEED;

    public Ground() {
        setSize(GameConfig.GROUND_WIDTH, GameConfig.GROUND_HEIGHT);
    }

    public void update(float delta) {
        float newX = x - speedX * delta;
        setPosition(newX, y);
    }

}
