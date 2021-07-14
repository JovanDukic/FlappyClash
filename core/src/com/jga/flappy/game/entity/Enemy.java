package com.jga.flappy.game.entity;

import com.badlogic.gdx.utils.Pool;
import com.jga.flappy.game.config.GameConfig;
import com.jga.flappy.game.enums.EnemyType;
import com.jga.flappy.game.enums.ObjectState;

public class Enemy extends ObjectBase implements Pool.Poolable {

    private float speedY = 0f;

    private float velocityY = GameConfig.ENEMY_JUMP_ACC;
    private float velocityX = GameConfig.ENEMY_ACC_X;
    private float gravity = GameConfig.ENEMY_FALL_ACC;

    private ObjectState state = ObjectState.FALL;
    private EnemyType enemyType = null;

    public void update(float delta) {
        if (state.isJumping()) {
            if (speedY >= GameConfig.ENEMY_MAX_SPEED) {
                fall();
            } else {
                speedY = speedY + velocityY * delta;
            }
        } else if (state.isFalling()) {
            if (speedY <= GameConfig.ENEMY_MIN_SPEED) {
                jump();
            } else {
                speedY = speedY - gravity * delta;
            }
        }

        float newX = x - velocityX * delta;
        float newY = y + speedY;

        setPosition(newX, newY);
    }

    private void fall() {
        state = ObjectState.FALL;
    }

    private void jump() {
        state = ObjectState.JUMP;
    }

    public void setState(ObjectState state) {
        this.state = state;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityX() {
        return velocityX;
    }

    @Override
    public void reset() {
        speedY = 0f;
        velocityX = GameConfig.ENEMY_ACC_X;
    }
}
