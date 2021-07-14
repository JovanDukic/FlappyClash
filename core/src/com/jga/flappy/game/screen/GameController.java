package com.jga.flappy.game.screen;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.jga.flappy.game.common.GameManager;
import com.jga.flappy.game.config.GameConfig;
import com.jga.flappy.game.entity.Background;
import com.jga.flappy.game.entity.Coin;
import com.jga.flappy.game.entity.Enemy;
import com.jga.flappy.game.entity.Ground;
import com.jga.flappy.game.entity.ObjectBase;
import com.jga.flappy.game.entity.Player;
import com.jga.flappy.game.entity.SpriteBase;
import com.jga.flappy.game.entity.Utility;
import com.jga.flappy.game.enums.EnemyType;
import com.jga.flappy.game.enums.GameState;
import com.jga.flappy.game.enums.ObjectState;
import com.jga.flappy.game.interfaces.CollisionListener;
import com.jga.flappy.game.interfaces.JumpListener;

public class GameController {

    private Player player;

    private Array<Enemy> enemies = new Array<Enemy>();
    private Pool<Enemy> enemyPool = Pools.get(Enemy.class, 20);
    private float enemyTimer;

    private Array<Coin> coins = new Array<Coin>();
    private Pool<Coin> coinPool = Pools.get(Coin.class, 10);
    private float coinTimer;

    private Array<Utility> utilities = new Array<>();
    private Pool<Utility> utilityPool = Pools.get(Utility.class, 10);
    private float utilityTimer;

    private Array<Background> backgrounds = new Array<Background>();
    private Pool<Background> backgroundPool = Pools.get(Background.class, 2);

    private Array<Ground> grounds = new Array<Ground>();
    private Pool<Ground> groundPool = Pools.get(Ground.class, 2);

    private final float startPlayerX = GameConfig.PLAYER_START_X;
    private final float startPlayerY = GameConfig.WORLD_CENTER_Y;

    private float animationTime;
    private float levelUpTimer = GameConfig.LEVEL_UP_TIMER;
    private float enemyDecreaseTimer = 0f;
    private float coinDecreaseTimer = 0f;

    private GameState gameState = GameState.PLAY;

    private CollisionListener collisionListener;

    private boolean show;

    public GameController() {
        init();
    }

    private void init() {
        player = new Player();
        player.setPosition(startPlayerX, startPlayerY);

        player.setJumpListener(new JumpListener() {
            @Override
            public void jump() {
                if (gameState.isPlay()) {
                    collisionListener.jump();
                }
            }
        });
    }

    public void update(float delta) {
        if (gameState.isPlay()) {
            animationTime = animationTime + delta;
            levelUpTimer = levelUpTimer - delta;

            updateScore(delta);
            updateBackground(delta);
            updateGround(delta);
            updatePlayer(delta);
            updateEnemy(delta);
            updateCoin(delta);
            updateUtilities(delta);

            if (levelUpTimer <= 0f) {
                levelUpTimer = GameConfig.LEVEL_UP_TIMER;

                if (enemyDecreaseTimer < GameConfig.MIN_ENEMY_DECREASE_TIME) {
                    enemyDecreaseTimer = enemyDecreaseTimer + GameConfig.ENEMY_DECREASE_TIMER;
                }

                if (coinDecreaseTimer < GameConfig.MIN_COIN_DECREASE_TIME) {
                    coinDecreaseTimer = coinDecreaseTimer + GameConfig.COIN_DECREASE_TIMER;
                }
            }
        } else if (gameState.isGameOver()) {
            if (show) {
                collisionListener.endGame();
                show = false;
            }

            if (!checkBoundsAfter()) {
                updatePlayerAfter(delta);
            }
        }
    }

    private void updateUtilities(float delta) {
        spawnUtility(delta);

        for (Utility utility : utilities) {
            utility.update(delta);
            checkDelete(utility);
        }
    }

    private void spawnUtility(float delta) {
        utilityTimer = utilityTimer + delta;

        if (utilityTimer < GameConfig.UTIL_SPAWN_TIME) {
            return;
        }

        utilityTimer = 0f;

        Utility utility = utilityPool.obtain();

        float x = GameConfig.SPAWN_AREA_WIDTH;
        utility.setSize(GameConfig.UTIL_HALF_SIZE);

        do {
            float y = MathUtils.random(GameConfig.SPAWN_AREA_MIN_HEIGHT, GameConfig.SPAWN_AREA_MAX_HEIGHT);
            utility.setPosition(x + GameConfig.UTIL_HALF_SIZE, y + GameConfig.UTIL_HALF_SIZE);
        } while (checkCoinCollision(utility) || checkEnemyCollision(utility));

        utilities.add(utility);
    }

    private void updateBackground(float delta) {
        spawnBackgrounds();

        for (Background background : backgrounds) {
            background.update(delta);
            checkSprite(background);
        }
    }

    private void updateGround(float delta) {
        spawnGrounds();

        for (Ground ground : grounds) {
            ground.update(delta);
            checkSprite(ground);
        }
    }

    private void updatePlayer(float delta) {
        player.update(delta);

        checkUtilityCollision(player);

        if (player.hasUtility() && player.getUtility().isShield()) {
            checkPlayerEnemyCollision(player);
        } else if (checkEnemyCollision(player)) {
            prepareRestart();
        }

        if (checkBounds()) {
            prepareRestart();
        } else if (checkCoinCollision(player)) {
            collisionListener.coinSound();
            GameManager.INSTANCE.addCoins();
        }

    }

    private void prepareRestart() {
        gameState = GameState.GAME_OVER;
        show = true;
        GameManager.INSTANCE.updateGameManager();
    }

    private void updatePlayerAfter(float delta) {
        player.updateAfter(delta);
    }

    private void updateCoin(float delta) {
        spawnCoins(delta);

        for (Coin coin : coins) {
            checkDelete(coin);
            coin.update(delta);
        }
    }

    private void updateEnemy(float delta) {
        spawnEnemies(delta);

        for (Enemy enemy : enemies) {
            checkDelete(enemy);
            if (levelUpTimer <= 0f && enemy.getVelocityX() != GameConfig.MAX_ENEMY_ACC_X) {
                enemy.setVelocityX(enemy.getVelocityX() + GameConfig.ENEMY_BONUS_ACC_X);
            }
            enemy.update(delta);
        }
    }

    private void checkDelete(ObjectBase object) {
        if (object.getX() <= GameConfig.DELETE_LINE_X) {
            if (object instanceof Enemy) {
                Enemy enemy = (Enemy) object;
                enemyPool.free(enemy);
                enemies.removeValue(enemy, true);
            } else if (object instanceof Coin) {
                Coin coin = (Coin) object;
                coinPool.free(coin);
                coins.removeValue(coin, true);
            } else if (object instanceof Utility) {
                Utility utility = (Utility) object;
                utilityPool.free(utility);
                utilities.removeValue(utility, true);
            }
        }
    }

    private void checkSprite(SpriteBase object) {
        if (object.getX() <= GameConfig.DELETE_BACKGROUND_LINE) {
            if (object instanceof Background) {
                Background background = (Background) object;
                background.setPosition(GameConfig.BACKGROUND_START_X_2, GameConfig.BACKGROUND_START_Y);
                backgrounds.reverse();
            } else if (object instanceof Ground) {
                Ground ground = (Ground) object;
                ground.setPosition(GameConfig.GROUND_START_X_2, GameConfig.GROUND_START_Y);
                grounds.reverse();
            }
        }
    }

    private void spawnCoins(float delta) {
        coinTimer = coinTimer + delta;

        if (coinTimer < GameConfig.COIN_SPAWN_TIME - coinDecreaseTimer) {
            return;
        }

        coinTimer = 0f;

        Coin coin = coinPool.obtain();
        do {
            float x = GameConfig.SPAWN_AREA_WIDTH;
            float y = MathUtils.random(GameConfig.SPAWN_AREA_MIN_HEIGHT, GameConfig.SPAWN_AREA_MAX_HEIGHT);
            coin.setPosition(x + GameConfig.COIN_HALF_SIZE, y + GameConfig.COIN_HALF_SIZE);
        } while (checkEnemyCollision(coin));

        coins.add(coin);
    }

    private void spawnEnemies(float delta) {
        enemyTimer = enemyTimer + delta;

        if (enemyTimer < GameConfig.ENEMY_SPAWN_TIME - enemyDecreaseTimer) {
            return;
        }

        enemyTimer = 0f;

        Enemy enemy = enemyPool.obtain();
        do {
            float x = GameConfig.SPAWN_AREA_WIDTH;
            float y = MathUtils.random(GameConfig.SPAWN_AREA_MIN_HEIGHT, GameConfig.SPAWN_AREA_MAX_HEIGHT);
            enemy.setPosition(x + GameConfig.ENEMY_HALF_SIZE, y + GameConfig.ENEMY_HALF_SIZE);

        } while (checkEnemyCollision(enemy));

        int a = MathUtils.random(0, 1);

        switch (a) {
            case 0:
                enemy.setState(ObjectState.JUMP);
                enemy.setEnemyType(EnemyType.ENE);
                enemy.setSize(GameConfig.ENE_HALF_SIZE);
                break;
            case 1:
                enemy.setState(ObjectState.FALL);
                enemy.setEnemyType(EnemyType.ENEMY);
                enemy.setSize(GameConfig.ENEMY_HALF_SIZE);
                break;
            default:
                enemy.setState(ObjectState.FALL);
                enemy.setEnemyType(EnemyType.ENEMY);
                enemy.setSize(GameConfig.ENEMY_HALF_SIZE);
                break;
        }

        enemies.add(enemy);
    }

    private void spawnBackgrounds() {
        if (backgrounds.size < 2) {
            Background background_1 = backgroundPool.obtain();
            background_1.setPosition(GameConfig.BACKGROUND_START_X_1, GameConfig.BACKGROUND_START_Y);

            Background background_2 = backgroundPool.obtain();
            background_2.setPosition(GameConfig.BACKGROUND_START_X_2, GameConfig.BACKGROUND_START_Y);

            backgrounds.add(background_1, background_2);
        }
    }

    private void spawnGrounds() {
        if (grounds.size < 2) {
            Ground ground_1 = groundPool.obtain();
            ground_1.setPosition(GameConfig.GROUND_START_X_1, GameConfig.GROUND_START_Y);

            Ground ground_2 = groundPool.obtain();
            ground_2.setPosition(GameConfig.GROUND_START_X_2, GameConfig.GROUND_START_Y);

            grounds.add(ground_1, ground_2);
        }
    }

    private boolean checkCoinCollision(ObjectBase object) {
        for (Coin coin : coins) {
            if (Intersector.overlaps(object.getBounds(), coin.getBounds())) {
                removeCoin(coin);
                return true;
            }
        }
        return false;
    }

    private boolean checkEnemyCollision(ObjectBase object) {
        for (Enemy enemy : enemies) {
            if (Intersector.overlaps(object.getBounds(), enemy.getBounds())) {
                return true;
            }
        }
        return false;
    }

    private void checkPlayerEnemyCollision(ObjectBase object) {
        for (Enemy enemy : enemies) {
            if (Intersector.overlaps(object.getBounds(), enemy.getBounds())) {
                enemyPool.free(enemy);
                enemies.removeValue(enemy, true);
                return;
            }
        }
    }

    private void checkUtilityCollision(ObjectBase object) {
        for (Utility utility : utilities) {
            if (Intersector.overlaps(object.getBounds(), utility.getBounds())) {
                player.setType(utility.getType());
                utilityPool.free(utility);
                utilities.removeValue(utility, true);
                return;
            }
        }
    }

    public void restart() {
        gameState = GameState.PLAY;

        enemyDecreaseTimer = 0f;
        coinDecreaseTimer = 0f;

        player.setPosition(startPlayerX, startPlayerY);
        player.restart();

        coinPool.freeAll(coins);
        coins.clear();

        enemyPool.freeAll(enemies);
        enemies.clear();

        utilityPool.freeAll(utilities);
        utilities.clear();

        enemyTimer = 0f;
        coinTimer = 0f;
        utilityTimer = 0f;
        animationTime = 0f;
    }

    private void updateScore(float delta) {
        GameManager.INSTANCE.updateScore(delta);
    }

    private void removeCoin(Coin coin) {
        coinPool.free(coin);
        coins.removeValue(coin, true);
    }

    private boolean checkBounds() {
        if (player.getY() >= player.getPlayerTopY()) {
            player.setPosition(player.getX(), player.getPlayerTopY());
            return true;
        } else if (player.getY() <= player.getPlayerBottomY()) {
            player.setPosition(player.getX(), player.getPlayerBottomY());
            return true;
        } else {
            return false;
        }
    }

    private boolean checkBoundsAfter() {
        if (player.getY() <= player.getPlayerBottomY()) {
            player.setPosition(player.getX(), player.getPlayerBottomY());
            return true;
        } else {
            return false;
        }
    }

    public void setCollisionListener(CollisionListener collisionListener) {
        this.collisionListener = collisionListener;
    }

    public Player getPlayer() {
        return player;
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public Array<Utility> getUtilities() {
        return utilities;
    }

    public Array<Coin> getCoins() {
        return coins;
    }

    public Array<Background> getBackgrounds() {
        return backgrounds;
    }

    public Array<Ground> getGrounds() {
        return grounds;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public GameState getGameState() {
        return gameState;
    }

}
