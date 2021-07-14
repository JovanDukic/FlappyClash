package com.jga.flappy.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jga.flappy.game.assets.AssetDescriptors;
import com.jga.flappy.game.assets.RegionNames;
import com.jga.flappy.game.common.GameManager;
import com.jga.flappy.game.config.GameConfig;
import com.jga.flappy.game.entity.Background;
import com.jga.flappy.game.entity.Coin;
import com.jga.flappy.game.entity.Enemy;
import com.jga.flappy.game.entity.Ground;
import com.jga.flappy.game.entity.Player;
import com.jga.flappy.game.entity.Utility;
import com.jga.flappy.game.interfaces.Restart;
import com.jga.flappy.game.menu.GameOverScreen;
import com.jga.flappy.game.utils.ViewportUtils;
import com.jga.flappy.game.utils.debug.DebugCameraController;

public class GameRenderer implements Disposable {

    private static final float FRAME_DURATION = 0.3f;

    private final FlappyGame game;

    private final GameController controller;
    private final SpriteBatch batch;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private Stage hudStage;
    private Viewport hudViewport;
    private GameOverScreen overScreen;
    private BitmapFont font;

    private GlyphLayout layout;

    private DebugCameraController debugCameraController;

    private Player player;
    private Array<Enemy> enemies;
    private Array<Coin> coins;
    private Array<Utility> utilities;
    private Array<Background> backgrounds;
    private Array<Ground> grounds;

    private TextureRegion backgroundRegion;
    private TextureRegion groundRegion;
    private TextureRegion coinRegion;

    private Animation playerAnimation;
    private Animation enemyAnimation;
    private Animation eneAnimation;
    private Animation shieldAnimation;

    private Animation shieldUtilityAnimation;
    private Animation decreasePlayerSizeAnimation;

    private int backgroundNumber;

    public GameRenderer(FlappyGame game, GameController controller, SpriteBatch batch, int backgroundNumber) {
        this.game = game;
        this.controller = controller;
        this.batch = batch;
        this.assetManager = game.getAssetManager();
        this.backgroundNumber = backgroundNumber;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        player = controller.getPlayer();
        enemies = controller.getEnemies();
        utilities = controller.getUtilities();
        coins = controller.getCoins();
        backgrounds = controller.getBackgrounds();
        grounds = controller.getGrounds();

        overScreen = new GameOverScreen(game);
        overScreen.setRestart(new Restart() {
            @Override
            public void restartGame() {
                controller.restart();
            }
        });

        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);

        hudStage = new Stage(hudViewport, batch);
        hudStage.addActor(overScreen);

        // very important
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hudStage);
        inputMultiplexer.addProcessor(controller.getPlayer());

        Gdx.input.setInputProcessor(inputMultiplexer);

        layout = new GlyphLayout();
        font = assetManager.get(AssetDescriptors.RENDERER_FONT);

        TextureAtlas textureAtlas = assetManager.get(AssetDescriptors.TEXTURES);
        TextureAtlas utilityAtlas = assetManager.get(AssetDescriptors.UTILITY_ATLAS);

        switch (backgroundNumber) {
            case 0:
                backgroundRegion = textureAtlas.findRegion(RegionNames.BACKGROUND);
                break;
            case 1:
                backgroundRegion = textureAtlas.findRegion(RegionNames.BACKGROUND_NIGHT);
                break;
            default:
                backgroundRegion = textureAtlas.findRegion(RegionNames.BACKGROUND);
                break;
        }

        // texture regions
        groundRegion = textureAtlas.findRegion(RegionNames.GROUND);
        coinRegion = textureAtlas.findRegion(RegionNames.COIN);

        // player animation selection
        switch (GameManager.INSTANCE.getPlayerSkin()) {
            case A:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_1), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case B:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_2), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case C:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_3), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case D:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_4), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case E:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_5), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case F:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_6), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case G:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_7), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case H:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_8), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case I:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_9), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case J:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_10), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case K:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_11), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case L:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_12), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case M:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_13), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case N:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_14), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case O:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_15), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case P:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_16), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case Q:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_17), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case R:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.SKIN_18), Animation.PlayMode.LOOP_PINGPONG);
                break;
            case DEFAULT:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.PLAYER), Animation.PlayMode.LOOP_PINGPONG);
                break;
            default:
                playerAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.PLAYER), Animation.PlayMode.LOOP_PINGPONG);
                break;
        }

        // enemy animation
        enemyAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.ENEMY), Animation.PlayMode.LOOP_PINGPONG);

        // ene animation
        eneAnimation = new Animation(FRAME_DURATION, textureAtlas.findRegions(RegionNames.ENE), Animation.PlayMode.LOOP_PINGPONG);

        // shield animation
        shieldAnimation = new Animation(FRAME_DURATION, utilityAtlas.findRegions(RegionNames.SHIELD), Animation.PlayMode.LOOP_PINGPONG);

        // shield util animation
        shieldUtilityAnimation = new Animation(FRAME_DURATION, utilityAtlas.findRegions(RegionNames.SHIELD_UTIL), Animation.PlayMode.LOOP_PINGPONG);

        // decrease player size animation
        decreasePlayerSizeAnimation = new Animation(FRAME_DURATION, utilityAtlas.findRegions(RegionNames.DECREASE_PLAYER_UTIL), Animation.PlayMode.LOOP_PINGPONG);
    }

    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        renderGamePlay(delta);
//        renderDebug();
        renderHud();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelsPerUnit(viewport);
    }

    private void renderGamePlay(float delta) {
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        drawGamePlay(delta);

        batch.end();
    }

    private void drawGamePlay(float delta) {
        // background
        for (Background background : backgrounds) {
            batch.draw(backgroundRegion, background.getX(), background.getY(), background.getWidth(), background.getHeight());
        }

        // ground
        for (Ground ground : grounds) {
            batch.draw(groundRegion, ground.getX(), ground.getY(), ground.getWidth(), ground.getHeight());
        }

        // coin
        for (Coin coin : coins) {
            batch.draw(coinRegion, coin.getX() - GameConfig.COIN_HALF_SIZE, coin.getY() - GameConfig.COIN_HALF_SIZE, GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);
        }

        // player
        TextureRegion playerRegion = (TextureRegion) playerAnimation.getKeyFrame(controller.getAnimationTime());
        batch.draw(playerRegion, player.getX() - player.getRadius(), player.getY() - player.getRadius(), player.getRadius() * 2, player.getRadius() * 2);

        // player utility
        if (player.hasUtility() && player.getUtility().isShield()) {
            TextureRegion shieldRegion = (TextureRegion) shieldAnimation.getKeyFrame(controller.getAnimationTime());
            batch.draw(shieldRegion, player.getX() - GameConfig.UTIL_HALF_DRAW_SIZE, player.getY() - GameConfig.UTIL_HALF_DRAW_SIZE, GameConfig.UTIL_DRAW_SIZE, GameConfig.UTIL_DRAW_SIZE);
        }

        // utilities
        for (Utility utility : utilities) {
            if (utility.getType().isShield()) {
                TextureRegion shieldUtilRegion = (TextureRegion) shieldUtilityAnimation.getKeyFrame(controller.getAnimationTime());
                batch.draw(shieldUtilRegion, utility.getX() - GameConfig.UTIL_HALF_SIZE, utility.getY() - GameConfig.UTIL_HALF_SIZE, GameConfig.UTIL_SIZE, GameConfig.UTIL_SIZE);
            } else if (utility.getType().isDecreasePlayer()) {
                TextureRegion decreasePlayerSizeRegion = (TextureRegion) decreasePlayerSizeAnimation.getKeyFrame(controller.getAnimationTime());
                batch.draw(decreasePlayerSizeRegion, utility.getX() - GameConfig.UTIL_HALF_SIZE, utility.getY() - GameConfig.UTIL_HALF_SIZE, GameConfig.UTIL_SIZE, GameConfig.UTIL_SIZE);
            }
        }

        // enemy
        TextureRegion enemyRegion;
        for (Enemy enemy : enemies) {
            if (enemy.getEnemyType() != null) {
                if (enemy.getEnemyType().isEnemy()) {
                    enemyRegion = (TextureRegion) enemyAnimation.getKeyFrame(controller.getAnimationTime());
                    batch.draw(enemyRegion, enemy.getX() - GameConfig.ENEMY_HALF_SIZE, enemy.getY() - GameConfig.ENEMY_HALF_SIZE, GameConfig.ENEMY_SIZE, GameConfig.ENEMY_SIZE);
                } else if (enemy.getEnemyType().isEne()) {
                    enemyRegion = (TextureRegion) eneAnimation.getKeyFrame(controller.getAnimationTime());
                    batch.draw(enemyRegion, enemy.getX() - GameConfig.ENE_HALF_SIZE, enemy.getY() - GameConfig.ENE_HALF_SIZE, GameConfig.ENE_SIZE, GameConfig.ENE_SIZE);
                }
            }
        }
    }

    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, renderer, GameConfig.CELL_SIZE);

        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        drawDebug();

        renderer.end();
    }

    private void drawDebug() {
        // player
        renderer.setColor(Color.BLUE);
        renderer.circle(player.getX(), player.getY(),
                player.getRadius(), 30);

        // player utility
        if (player.hasUtility()) {
            if (player.getUtility().isShield()) {
                renderer.circle(player.getX(), player.getY(),
                        GameConfig.UTIL_SIZE, 30);
            }

        }

        // enemies
        renderer.setColor(Color.YELLOW);
        for (Enemy enemy : enemies) {
            renderer.circle(enemy.getX(), enemy.getY(),
                    enemy.getRadius(), 30);
        }

        // coins
        renderer.setColor(Color.GREEN);
        for (Coin coin : coins) {
            renderer.circle(coin.getX(), coin.getY(),
                    coin.getRadius(), 30);
        }

        renderer.setColor(Color.RED);
        for (Utility utility : utilities) {
            renderer.circle(utility.getX(), utility.getY(),
                    utility.getRadius(), 30);
        }
    }

    private void renderHud() {
        hudViewport.apply();

        overScreen.setVisible(false);

        if (controller.getGameState().isPlay()) {
            batch.setProjectionMatrix(hudViewport.getCamera().combined);
            batch.begin();

            drawHud();

            batch.end();
        } else if (controller.getGameState().isGameOver() && !overScreen.isVisible()) {
            drawGameOver();
        }

        hudStage.act();
        hudStage.draw();
    }

    private void drawGameOver() {
        overScreen.updateScoreLabel();
        overScreen.updateCoinLabel();
        overScreen.updateCoinSumLabel();

        overScreen.setVisible(true);
    }

    private void drawHud() {
        float padding = 10f;

        // score
        String score = "Score: " + GameManager.INSTANCE.getDisplayScore();
        layout.setText(font, score);
        font.draw(batch, layout, padding, GameConfig.HUD_HEIGHT - layout.height);

        // high-score
        String highScore = "High-Score: " + GameManager.INSTANCE.getDisplayHighScore();
        layout.setText(font, highScore);
        font.draw(batch, layout, padding, GameConfig.HUD_HEIGHT - 3 * layout.height);

        // coins
        String coins = "Coins: " + GameManager.INSTANCE.getCoins();
        layout.setText(font, coins);
        font.draw(batch, layout, padding, GameConfig.HUD_HEIGHT - 5 * layout.height);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        hudStage.dispose();
    }
}
