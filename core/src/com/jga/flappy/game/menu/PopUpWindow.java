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
import com.jga.flappy.game.interfaces.PopUpConnector;

public class PopUpWindow extends Table {

    private Label messageLabel;

    private final AssetManager assetManager;

    private PopUpConnector connector;

    public PopUpWindow(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    private void init() {

        // get assets
        Skin skin = assetManager.get(AssetDescriptors.WINDOW_SKIN);

        // label
        messageLabel = new Label("", skin);
        messageLabel.setName("message-label");

        // table image
        Table imageTable = new Table(skin);
        imageTable.defaults();
        imageTable.setBackground(RegionNames.LABEL_IMAGE);

        // background table
        Table backgroundTable = new Table(skin);
        backgroundTable.setBackground(RegionNames.MESSAGE_WINDOW);

        // ok button
        TextButton ok = new TextButton("OK", skin);
        ok.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

        // add to image table
        imageTable.add(messageLabel);

        // add to background table
        backgroundTable.add(imageTable).padTop(30f).row();
        backgroundTable.add(ok).padBottom(30f);

        // add to main table
        add(backgroundTable);

        // main table
        center();
        setFillParent(true);
        pack();
    }

    private void back() {
        connector.reset();
        remove();
    }

    public void setConnector(PopUpConnector connector) {
        this.connector = connector;
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
