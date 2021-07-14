package com.jga.flappy.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import com.jga.flappy.game.config.GameConfig;
import com.jga.flappy.game.enums.UtilityType;

public class Utility extends ObjectBase implements Pool.Poolable {

    private float speedX = GameConfig.UTIL_HORIZONTAL_SPEED;

    private UtilityType type;

    public Utility() {
        super();
        setType();
    }

    private void setType() {
        // for now we have just shield and decrease player size
        int a = MathUtils.random(0, 1);

        switch (a) {
            case 0:
                setType(UtilityType.SHIELD);
                break;
            case 1:
                setType(UtilityType.DECREASE_PLAYER);
                break;
        }
    }

    private void setType(UtilityType type) {
        this.type = type;
    }

    public UtilityType getType() {
        return type;
    }

    public void update(float delta) {
        float newX = x - speedX * delta;
        setPosition(newX, y);
    }

    @Override
    public void reset() {
        speedX = GameConfig.UTIL_HORIZONTAL_SPEED;
        setType();
    }
}
