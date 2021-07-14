package com.jga.flappy.game.menu;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jga.flappy.game.assets.AssetDescriptors;
import com.jga.flappy.game.assets.RegionNames;
import com.jga.flappy.game.common.GameManager;
import com.jga.flappy.game.interfaces.Restart;
import com.jga.flappy.game.screen.FlappyGame;

public class GameOverScreen extends Table {

    private final FlappyGame game;
    private final AssetManager assetManager;

    private Label scoreLabel;
    private Label coinLabel;
    private Label coinSumLabel;

    private Restart restart;

    public GameOverScreen(FlappyGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
        init();
    }

    private void init() {
        // main table settings
        defaults().pad(20f);

        // get assets
        Skin skin = assetManager.get(AssetDescriptors.UI_SKIN);
        setSkin(skin);

        // button table
        Table buttonTable = new Table(getSkin());
        buttonTable.defaults().pad(10f);
        buttonTable.setBackground(RegionNames.PANEL);

        // game-over label
        String gameOverText = "Final Results";
        Label gameOverLabel = new Label(gameOverText, getSkin());

        // score label
        scoreLabel = new Label("", getSkin());
        updateScoreLabel();

        // coins label
        coinLabel = new Label("", getSkin());
        updateCoinLabel();

        // coin sum label
        coinSumLabel = new Label("", getSkin());
        updateCoinSumLabel();

        // restart button
        TextButton restartButton = new TextButton("RESTART", getSkin());
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                restart();
            }
        });

        // back button
        TextButton backButton = new TextButton("MENU", getSkin());
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

        buttonTable.add(gameOverLabel).row();
        buttonTable.add(scoreLabel).row();
        buttonTable.add(coinLabel).row();
        buttonTable.add(coinSumLabel).row();
        buttonTable.add(restartButton).row();
        buttonTable.add(backButton);

        add(buttonTable);
        setFillParent(true);
        center();
        pack();
    }

    private void back() {
        GameManager.INSTANCE.restart();
        setVisible(false);
        game.getAdController().setVisibleBanner();
        game.setScreen(new MainMenu(game));
    }

    private void restart() {
        GameManager.INSTANCE.restart();
        setVisible(false);
        restart.restartGame();
    }

    public void setRestart(Restart restart) {
        this.restart = restart;
    }

    public void updateScoreLabel() {
        scoreLabel.setText("Score: " + GameManager.INSTANCE.getDisplayScore());
    }

    public void updateCoinLabel() {
        coinLabel.setText("Coins: " + GameManager.INSTANCE.getCoins());
    }

    public void updateCoinSumLabel() {
        coinSumLabel.setText("Total Score: " + GameManager.INSTANCE.getTotalScore());
    }

}
