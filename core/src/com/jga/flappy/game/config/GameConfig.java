package com.jga.flappy.game.config;

public class GameConfig {

    //screen settings
    public static final float WIDTH = 480f;
    public static final float HEIGHT = 800f;

    public static final float WORLD_WIDTH = 15f;
    public static final float WORLD_HEIGHT = 25f;

    public static final float HUD_WIDTH = 480f;
    public static final float HUD_HEIGHT = 800f;

    // spawn area
    public static final float SPAWN_WIDTH = 4f;
    public static final float SPAWN_WIDTH_HALF = SPAWN_WIDTH / 2f;

    public static final float SPAWN_AREA_WIDTH = WORLD_WIDTH + SPAWN_WIDTH_HALF;

    public static final float SPAWN_AREA_MIN_HEIGHT = 6f;
    public static final float SPAWN_AREA_MAX_HEIGHT = WORLD_HEIGHT - 4f;

    // delete area
    public static final float DELETE_WIDTH = 4f;
    public static final float DELETE_WIDTH_HALF = DELETE_WIDTH / 2f;

    public static final float DELETE_AREA_WIDTH = -DELETE_WIDTH;
    public static final float DELETE_LINE_X = -DELETE_WIDTH_HALF;

    // world center
    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f;

    // cell size
    public static final int CELL_SIZE = 1;

    // player config
    public static final float PLAYER_SIZE = 1.3f;
    public static final float PLAYER_HALF_SIZE = PLAYER_SIZE / 2f;

    public static final float PLAYER_DECREASE_SIZE = 0.8f;
    public static final float PLAYER_HALF_DECREASE_SIZE = PLAYER_DECREASE_SIZE / 2f;

    public static final float PLAYER_FALL_ACC = 0.6f;
    public static final float PLAYER_JUMP_ACC = 4.5f;

    public static final float PLAYER_MAX_SPEED = 0.2f;
    public static final float PLAYER_MIN_SPEED = -0.2f;

    public static final float PLAYER_START_X = 4f;
    public static final float PLAYER_BOTTOM_Y = PLAYER_HALF_SIZE + 3f;
    public static final float PLAYER_TOP_Y = WORLD_HEIGHT - PLAYER_HALF_SIZE;

    // enemy config
    public static final float ENEMY_SPAWN_TIME = 1f;
    public static final float MIN_ENEMY_DECREASE_TIME = 0.4f;

    public static final float ENEMY_SIZE = 1.3f;
    public static final float ENEMY_HALF_SIZE = ENEMY_SIZE / 2f;

    public static final float ENE_SIZE = 1.35f;
    public static final float ENE_HALF_SIZE = ENE_SIZE / 2f;

    public static final float ENEMY_JUMP_ACC = 0.3f;
    public static final float ENEMY_FALL_ACC = 0.3f;

    public static final float ENEMY_MAX_SPEED = 0.1f;
    public static final float ENEMY_MIN_SPEED = -0.1f;

    public static final float ENEMY_ACC_X = 3.5f;
    public static final float MAX_ENEMY_ACC_X = 6f;
    public static final float ENEMY_BONUS_ACC_X = 0.2f;

    // coin config
    public static final float COIN_SPAWN_TIME = 2f;
    public static final float MIN_COIN_DECREASE_TIME = 1f;

    public static final float COIN_SIZE = 1.3f;
    public static final float COIN_HALF_SIZE = COIN_SIZE / 2f;

    public static final float COIN_ACC_X = 3f;
    public static final int COIN_MULTIPLE = 25;

    // background config
    public static final float BACKGROUND_WIDTH = 16f;
    public static final float BACKGROUND_HEIGHT = 25f;
    public static final float BACKGROUND_SPEED = 1.5f;

    public static final float BACKGROUND_START_Y = 0f;
    public static final float BACKGROUND_START_X_1 = 0f;
    public static final float BACKGROUND_START_X_2 = WORLD_WIDTH - 0.2f;

    public static final float DELETE_BACKGROUND_LINE = -WORLD_WIDTH;

    // ground config
    public static final float GROUND_WIDTH = 16f;
    public static final float GROUND_HEIGHT = 3f;
    public static final float GROUND_SPEED = 1.5f;

    public static final float GROUND_START_Y = 0f;
    public static final float GROUND_START_X_1 = 0f;
    public static final float GROUND_START_X_2 = WORLD_WIDTH - 0.2f;

    // score config
    public static final int STARTING_MULTIPLE = 65;

    // enemy decrease timer
    public static final float ENEMY_DECREASE_TIMER = 0.05f;

    // coin decrease timer
    public static final float COIN_DECREASE_TIMER = 0.05f;

    // level-up
    public static final float LEVEL_UP_TIMER = 20f;

    // utility config
    public static final float UTIL_HORIZONTAL_SPEED = 2.5f;

    public static final float UTIL_SPAWN_TIME = 10f;
    public static final float UTIL_DURATION = 5f;

    public static final float UTIL_SIZE = 1f;
    public static final float UTIL_HALF_SIZE = UTIL_SIZE / 2f;

    public static final float UTIL_DRAW_SIZE = 2f;
    public static final float UTIL_HALF_DRAW_SIZE = UTIL_DRAW_SIZE / 2;


    private GameConfig() {

    }

}
