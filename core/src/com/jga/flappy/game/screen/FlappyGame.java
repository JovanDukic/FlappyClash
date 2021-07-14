package com.jga.flappy.game.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.jga.flappy.game.ads.AdController;
import com.jga.flappy.game.common.GameManager;

public class FlappyGame extends Game {

    private final AdController adController;

    private AssetManager assetManager;
    private SpriteBatch batch;

    public FlappyGame(AdController adController) {
        this.adController = adController;
    }

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);

        batch = new SpriteBatch();

        getAdController().showBanner();
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        batch.dispose();
        GameManager.INSTANCE.updateHighScore();
        GameManager.INSTANCE.updateCoins();
        GameManager.INSTANCE.saveSkin();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public AdController getAdController() {
        return adController;
    }

}
