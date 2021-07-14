package com.jga.flappy.game.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jga.flappy.game.assets.AssetDescriptors;
import com.jga.flappy.game.assets.RegionNames;
import com.jga.flappy.game.common.GameManager;
import com.jga.flappy.game.screen.FlappyGame;

public class HighScoreScreen extends MenuScreenBase {

    private int backgroundNumber;

    public HighScoreScreen(FlappyGame game, int backgroundNumber) {
        super(game);
        this.backgroundNumber = backgroundNumber;
    }

    @Override
    protected Actor createUi() {

        // get assets
        TextureAtlas atlas = assetManager.get(AssetDescriptors.TEXTURES);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);

        // background-table
        Table backgroundTable = new Table();
        TextureRegion background;
        switch (backgroundNumber) {
            case 0:
                background = atlas.findRegion(RegionNames.BACKGROUND);
                break;
            case 1:
                background = atlas.findRegion(RegionNames.BACKGROUND_NIGHT);
                break;
            default:
                background = atlas.findRegion(RegionNames.BACKGROUND);
                break;
        }
        backgroundTable.setBackground(new TextureRegionDrawable(background));

        // button-table
        Table buttonTable = new Table(uiSkin);
        buttonTable.defaults().pad(20f);
        buttonTable.setBackground(RegionNames.PANEL);

        // high-score-label-1
        String highScoreText = "HIGH-SCORE";
        Label highScoreLabel_1 = new Label(highScoreText, uiSkin);

        // high-score-label-2
        String highScoreNumber = GameManager.INSTANCE.getDisplayHighScoreText();
        Label highScoreLabel_2 = new Label(highScoreNumber, uiSkin);

        // back-button
        TextButton backButton = new TextButton("BACK", uiSkin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

       /* final ImageButton soundButton = new ImageButton(uiSkin, ButtonNames.SOUND_BUTTON);

        if (GameManager.INSTANCE.getSoundState().isSoundOn()) {
            soundButton.setChecked(false);
        } else {
            soundButton.setChecked(true);
        }

        soundButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (soundButton.isChecked()) {
                    GameManager.INSTANCE.setSoundState(SoundState.SOUND_OFF);
                } else {
                    GameManager.INSTANCE.setSoundState(SoundState.SOUND_ON);
                }
            }
        });*/

        // add stuff to button-table
        buttonTable.add(highScoreLabel_1).row();
        buttonTable.add(highScoreLabel_2).row();
        buttonTable.add(backButton);

        backgroundTable.add(buttonTable).expandY().padTop(40f).row();
//        backgroundTable.add(soundButton).pad(0f, 350f, 30f, 0f);
        backgroundTable.setFillParent(true);
        backgroundTable.pack();

        return backgroundTable;
    }

    private void back() {
        game.getAdController().setVisibleBanner();
        game.setScreen(new MainMenu(game));
    }
}
