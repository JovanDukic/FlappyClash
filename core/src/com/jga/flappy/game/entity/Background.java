package com.jga.flappy.game.entity;

import com.jga.flappy.game.config.GameConfig;

public class Background extends SpriteBase {

    private float speedX = GameConfig.BACKGROUND_SPEED;

    public Background() {
        setSize(GameConfig.BACKGROUND_WIDTH, GameConfig.BACKGROUND_HEIGHT);
    }

    public void update(float delta) {
        float newX = x - speedX * delta;
        setPosition(newX, y);
    }

}
