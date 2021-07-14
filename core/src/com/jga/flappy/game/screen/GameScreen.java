package com.jga.flappy.game.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jga.flappy.game.interfaces.CollisionListener;
import com.jga.flappy.game.utils.GdxUtils;

public class GameScreen extends ScreenAdapter {

    private final FlappyGame game;
    private final SpriteBatch batch;

    private GameController controller;
    private GameRenderer renderer;

    private int backgroundNumber;

    /*private Sound coinSound;
    private Sound jumpSound;
    private Sound gameOverSound;*/

    public GameScreen(FlappyGame game, int backgroundNumber) {
        this.game = game;
        this.backgroundNumber = backgroundNumber;
        batch = game.getBatch();
    }

    @Override
    public void show() {
        AssetManager assetManager = game.getAssetManager();

        /*coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        jumpSound = assetManager.get(AssetDescriptors.JUMP_SOUND);
        gameOverSound = assetManager.get(AssetDescriptors.GAME_OVER_SOUND);*/

        controller = new GameController();
        renderer = new GameRenderer(game, controller, batch, backgroundNumber);

        controller.setCollisionListener(new CollisionListener() {
            @Override
            public void endGame() {
                /*if (GameManager.INSTANCE.getSoundState().isSoundOn()) {
                    gameOverSound.play();
                }*/
                game.getAdController().showInterstitial();
            }

            @Override
            public void coinSound() {
                /*if (GameManager.INSTANCE.getSoundState().isSoundOn()) {
                    coinSound.play();
                }*/
            }

            @Override
            public void jump() {
               /* if (GameManager.INSTANCE.getSoundState().isSoundOn()) {
                    jumpSound.play();
                }*/
            }
        });
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
