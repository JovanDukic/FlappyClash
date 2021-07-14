package com.jga.flappy.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jga.flappy.game.assets.AssetDescriptors;
import com.jga.flappy.game.assets.RegionNames;
import com.jga.flappy.game.screen.FlappyGame;
import com.jga.flappy.game.screen.GameScreen;

public class MainMenu extends MenuScreenBase {

    private int backgroundNumber;

    public MainMenu(FlappyGame game) {
        super(game);
    }

    @Override
    protected Actor createUi() {

        // get assets
        TextureAtlas atlas = assetManager.get(AssetDescriptors.TEXTURES);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);

        // label-game-name
        String gameText = "Flappy Clash";
        Label gameName;

        // background-table
        Table backgroundTable = new Table();
        TextureRegion background;
        backgroundNumber = MathUtils.random(0, 1);
        switch (backgroundNumber) {
            case 0:
                background = atlas.findRegion(RegionNames.BACKGROUND);
                gameName = new Label(gameText, uiSkin, "menu-font-2");
                break;
            case 1:
                background = atlas.findRegion(RegionNames.BACKGROUND_NIGHT);
                gameName = new Label(gameText, uiSkin, "menu-font-1");
                break;
            default:
                background = atlas.findRegion(RegionNames.BACKGROUND);
                gameName = new Label(gameText, uiSkin, "menu-font-2");
                break;
        }
        backgroundTable.setBackground(new TextureRegionDrawable(background));

        // button-table
        Table buttonTable = new Table(uiSkin);
        buttonTable.setBackground(RegionNames.PANEL);
        buttonTable.defaults().pad(20f);

        // play-button
        TextButton playButton = new TextButton("PLAY", uiSkin);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });

        // high-score-button
        TextButton highScoreButton = new TextButton("HIGH-SCORE", uiSkin);
        highScoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                highScore();
            }
        });

        // merchant-button
        TextButton merchantButton = new TextButton("MERCHANT", uiSkin);
        merchantButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                merch();
            }
        });

        // quit-button
        TextButton quitButton = new TextButton("QUIT", uiSkin);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        // add buttons to button-table
        buttonTable.add(playButton).row();
        buttonTable.add(highScoreButton).row();
        buttonTable.add(merchantButton).row();
        buttonTable.add(quitButton);

        // add button-table to background-table
        backgroundTable.add(gameName).pad(100f, 0f, 0f, 0f).row();
        backgroundTable.add(buttonTable).expandY();

        backgroundTable.center();
        backgroundTable.setFillParent(true);
        backgroundTable.pack();

        return backgroundTable;
    }

    private void play() {
        game.getAdController().setInVisibleBanner();
        game.setScreen(new GameScreen(game, backgroundNumber));
    }

    private void quit() {
        Gdx.app.exit();
    }

    private void highScore() {
        game.setScreen(new HighScoreScreen(game, backgroundNumber));
    }

    private void merch() {
        game.setScreen(new MerchScreen(game, backgroundNumber));
    }
}
