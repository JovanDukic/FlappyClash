package com.jga.flappy.game.entity;

import com.badlogic.gdx.Input;
import com.jga.flappy.game.config.GameConfig;
import com.jga.flappy.game.enums.ObjectState;
import com.jga.flappy.game.enums.UtilityType;
import com.jga.flappy.game.interfaces.JumpListener;

public class Player extends ObjectBase {

    private float speed = 0f;
    private float speedDown = 0f;
    private float velocity = GameConfig.PLAYER_JUMP_ACC;
    private float gravity = GameConfig.PLAYER_FALL_ACC;

    private float playerTopY = GameConfig.WORLD_HEIGHT - radius / 2;
    private float playerBottomY = radius / 2 + 3f;

    private ObjectState state = ObjectState.FALL;

    private UtilityType type;
    private float utilityTimer = 0;

    private JumpListener jumpListener;

    public Player() {
        setSize(GameConfig.PLAYER_HALF_SIZE);
        setPosition(GameConfig.WORLD_CENTER_X - GameConfig.WORLD_CENTER_X / 2f + GameConfig.PLAYER_HALF_SIZE, GameConfig.WORLD_CENTER_Y + GameConfig.PLAYER_HALF_SIZE);
    }

    public void update(float delta) {
        updateUtility(delta);

        if (state.isJumping()) {
            // when reached max speed switch to falling
            if (speed >= GameConfig.PLAYER_MAX_SPEED) {
                fall();
            } else {
                speed = speed + velocity * delta;
            }
        } else if (state.isFalling()) {
            if (speed > GameConfig.PLAYER_MIN_SPEED) {
                speed = speed - gravity * delta;
            }
        }

        float newY = y + speed;

        setPosition(x, newY);
    }

    public void updateAfter(float delta) {
        speedDown = speedDown - gravity * delta;

        float newY = y + speedDown;

        setPosition(x, newY);
    }

    public void updateUtility(float delta) {
        if (type != null) {
            utilityTimer = utilityTimer + delta;
            if (utilityTimer >= GameConfig.UTIL_DURATION) {
                if (type.isDecreasePlayer()) {
                    updateIncreasePlayer();
                }
                utilityTimer = 0;
                type = null;
            }
        }
    }

    public void restart() {
        speed = 0f;
        speedDown = 0f;
        state = ObjectState.FALL;
        updateIncreasePlayer();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Input.Keys.SPACE == keycode) {
            jump();
            jumpListener.jump();
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        jump();
        jumpListener.jump();
        return true;
    }

    private void fall() {
        state = ObjectState.FALL;
    }

    private void jump() {
        state = ObjectState.JUMP;
    }

    public void setJumpListener(JumpListener jumpListener) {
        this.jumpListener = jumpListener;
    }

    public void setType(UtilityType type) {
        if (type.isDecreasePlayer()) {
            updateDecreasePlayer();
        }
        this.type = type;
    }

    public UtilityType getUtility() {
        return type;
    }

    public boolean hasUtility() {
        return type != null;
    }

    public float getPlayerTopY() {
        return playerTopY;
    }

    private void updatePlayerTopY() {
        playerTopY = GameConfig.WORLD_HEIGHT - radius / 2;
    }

    public float getPlayerBottomY() {
        return playerBottomY;
    }

    private void updatePlayerBottomY() {
        playerBottomY = radius / 2 + 3f;
    }

    private void updateDecreasePlayer() {
        setSize(GameConfig.PLAYER_HALF_DECREASE_SIZE);
        updatePlayerTopY();
        updatePlayerBottomY();
    }

    private void updateIncreasePlayer(){
        setSize(GameConfig.PLAYER_HALF_SIZE);
        updatePlayerTopY();
        updatePlayerBottomY();
    }
}
