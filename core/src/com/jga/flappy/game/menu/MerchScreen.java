package com.jga.flappy.game.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.jga.flappy.game.assets.AssetDescriptors;
import com.jga.flappy.game.assets.ButtonNames;
import com.jga.flappy.game.assets.RegionNames;
import com.jga.flappy.game.common.GameManager;
import com.jga.flappy.game.enums.PlayerSkin;
import com.jga.flappy.game.interfaces.PopUpConnector;
import com.jga.flappy.game.screen.FlappyGame;

public class MerchScreen extends MenuScreenBase {

    private int backgroundNumber;
    private Array<ImageButton> imageButtons;

    private static final String moneyMessage = "Need 500 coins!";
    private static final String possesMessage = "Skin acquired!";
    private static final String buyMessage = "Got a new skin!";
    private static final String nothingSelectedMessage = "Empty selection!";

    private Table backgroundTable;
    private Label coinLabel;

    private Skin own;

    public MerchScreen(FlappyGame game, int backgroundNumber) {
        super(game);
        this.backgroundNumber = backgroundNumber;
        imageButtons = new Array<ImageButton>();
    }

    @Override
    protected Actor createUi() {

        // get assets
        TextureAtlas atlas = assetManager.get(AssetDescriptors.TEXTURES);
        Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);
        own = assetManager.get(AssetDescriptors.MERCH_SKIN);
        Skin unOwn = assetManager.get(AssetDescriptors.MERCH_SKIN_1);

        // background-table
        backgroundTable = new Table();
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

        // coin image
        Image coinImage = new Image(new TextureRegion(atlas.findRegion(RegionNames.COIN)));

        // coin label
        coinLabel = new Label("", uiSkin);
        updateCoinLabel();

        // coin table
        Table coinTable = new Table(uiSkin);
        coinTable.defaults().padTop(10f);
        coinTable.add(coinImage);
        coinTable.add(coinLabel).padLeft(20f);

        // table of images
        final Table imageTable = new Table(uiSkin);
        imageTable.defaults().pad(10f);

        // image buttons
        ImageButton buttonA = new ImageButton(unOwn, ButtonNames.A);
        ImageButton buttonB = new ImageButton(unOwn, ButtonNames.B);
        ImageButton buttonC = new ImageButton(unOwn, ButtonNames.C);
        ImageButton buttonD = new ImageButton(unOwn, ButtonNames.D);
        ImageButton buttonE = new ImageButton(unOwn, ButtonNames.E);
        ImageButton buttonF = new ImageButton(unOwn, ButtonNames.F);
        ImageButton buttonG = new ImageButton(unOwn, ButtonNames.G);
        ImageButton buttonH = new ImageButton(unOwn, ButtonNames.H);
        ImageButton buttonI = new ImageButton(unOwn, ButtonNames.I);
        ImageButton buttonJ = new ImageButton(unOwn, ButtonNames.J);
        ImageButton buttonK = new ImageButton(unOwn, ButtonNames.K);
        ImageButton buttonL = new ImageButton(unOwn, ButtonNames.L);
        ImageButton buttonM = new ImageButton(unOwn, ButtonNames.M);
        ImageButton buttonN = new ImageButton(unOwn, ButtonNames.N);
        ImageButton buttonO = new ImageButton(unOwn, ButtonNames.O);
        ImageButton buttonP = new ImageButton(unOwn, ButtonNames.P);
        ImageButton buttonQ = new ImageButton(unOwn, ButtonNames.Q);
        ImageButton buttonR = new ImageButton(unOwn, ButtonNames.R);

        if (PlayerSkin.A.isPosses()) {
            buttonA.setStyle(own.get(ButtonNames.A, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.B.isPosses()) {
            buttonB.setStyle(own.get(ButtonNames.B, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.C.isPosses()) {
            buttonC.setStyle(own.get(ButtonNames.C, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.D.isPosses()) {
            buttonD.setStyle(own.get(ButtonNames.D, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.E.isPosses()) {
            buttonE.setStyle(own.get(ButtonNames.E, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.F.isPosses()) {
            buttonF.setStyle(own.get(ButtonNames.F, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.G.isPosses()) {
            buttonG.setStyle(own.get(ButtonNames.G, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.H.isPosses()) {
            buttonH.setStyle(own.get(ButtonNames.H, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.I.isPosses()) {
            buttonI.setStyle(own.get(ButtonNames.I, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.J.isPosses()) {
            buttonJ.setStyle(own.get(ButtonNames.J, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.K.isPosses()) {
            buttonK.setStyle(own.get(ButtonNames.K, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.L.isPosses()) {
            buttonL.setStyle(own.get(ButtonNames.L, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.M.isPosses()) {
            buttonM.setStyle(own.get(ButtonNames.M, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.N.isPosses()) {
            buttonN.setStyle(own.get(ButtonNames.N, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.O.isPosses()) {
            buttonO.setStyle(own.get(ButtonNames.O, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.P.isPosses()) {
            buttonP.setStyle(own.get(ButtonNames.P, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.Q.isPosses()) {
            buttonQ.setStyle(own.get(ButtonNames.Q, ImageButton.ImageButtonStyle.class));
        }

        if (PlayerSkin.R.isPosses()) {
            buttonR.setStyle(own.get(ButtonNames.R, ImageButton.ImageButtonStyle.class));
        }

        buttonA.setName("A");
        buttonB.setName("B");
        buttonC.setName("C");
        buttonD.setName("D");
        buttonE.setName("E");
        buttonF.setName("F");
        buttonG.setName("G");
        buttonH.setName("H");
        buttonI.setName("I");
        buttonJ.setName("J");
        buttonK.setName("K");
        buttonL.setName("L");
        buttonM.setName("M");
        buttonN.setName("N");
        buttonO.setName("O");
        buttonP.setName("P");
        buttonQ.setName("Q");
        buttonR.setName("R");

        imageButtons.add(buttonA, buttonB, buttonC);
        imageButtons.add(buttonD, buttonE, buttonF);
        imageButtons.add(buttonG, buttonH, buttonI);
        imageButtons.add(buttonJ, buttonK, buttonL);
        imageButtons.add(buttonM, buttonN, buttonO);
        imageButtons.add(buttonP, buttonQ, buttonR);

        for (PlayerSkin skin : PlayerSkin.values()) {
            if (skin.isPlay()) {
                for (ImageButton imageButton : imageButtons) {
                    if (imageButton.getName().equals(skin.toString())) {
                        imageButton.setChecked(true);
                    }
                }
            }
        }

        imageTable.add(buttonA);
        imageTable.add(buttonB).row();
        imageTable.add(buttonC);
        imageTable.add(buttonD).row();
        imageTable.add(buttonE);
        imageTable.add(buttonF).row();
        imageTable.add(buttonG);
        imageTable.add(buttonH).row();
        imageTable.add(buttonI);
        imageTable.add(buttonJ).row();
        imageTable.add(buttonK);
        imageTable.add(buttonL).row();
        imageTable.add(buttonM);
        imageTable.add(buttonN).row();
        imageTable.add(buttonO);
        imageTable.add(buttonP).row();
        imageTable.add(buttonQ);
        imageTable.add(buttonR);

        imageTable.center();
        imageTable.pack();

        imageTable.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (((ImageButton) actor).isChecked()) {
                    for (ImageButton imageButton : imageButtons) {
                        if ((imageButton.isChecked() && !actor.equals(imageButton))) {
                            (imageButton).setChecked(false);
                            break;
                        }
                    }
                    if (PlayerSkin.valueOf(actor.getName()).isPosses()) {
                        GameManager.INSTANCE.setPlayerSkin(PlayerSkin.valueOf(actor.getName()));
                    } else {
                        GameManager.INSTANCE.setPlayerSkin(PlayerSkin.DEFAULT);
                    }
                } else {
                    boolean check = false;

                    for (ImageButton imageButton : imageButtons) {
                        if (imageButton.isChecked()) {
                            check = true;
                            break;
                        }
                    }

                    if (!check) {
                        GameManager.INSTANCE.setPlayerSkin(PlayerSkin.DEFAULT);
                    }
                }
            }
        });

        // scroll-pane
        final ScrollPane scrollPane = new ScrollPane(imageTable);

        // buy button
        TextButton buyButton = new TextButton("BUY", uiSkin);
        buyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buy();
            }
        });

        // back button
        TextButton backButton = new TextButton("BACK", uiSkin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

        buttonTable.add(coinTable).align(Align.left).row();
        buttonTable.add(scrollPane).width(200f).height(250f).row();
        buttonTable.add(buyButton).expandY().row();
        buttonTable.add(backButton);

        backgroundTable.add(buttonTable);
        backgroundTable.setFillParent(true);
        backgroundTable.pack();

        return backgroundTable;
    }

    private void buy() {
        PopUpWindow popUpWindow = new PopUpWindow(assetManager);
        popUpWindow.setConnector(new PopUpConnector() {
            @Override
            public void reset() {
                backgroundTable.setTouchable(Touchable.enabled);
            }
        });

        backgroundTable.setTouchable(Touchable.disabled);

        for (Actor actor : imageButtons) {
            if (((ImageButton) actor).isChecked()) {
                for (PlayerSkin skin : PlayerSkin.values()) {
                    if (skin.toString().equals(actor.getName())) {
                        if (skin.isPosses()) {
                            popUpWindow.setMessage(possesMessage);
                            stage.addActor(popUpWindow);
                            return;
                        } else if (GameManager.INSTANCE.getCoinSumNumber() < 500) {
                            popUpWindow.setMessage(moneyMessage);
                            stage.addActor(popUpWindow);
                            return;
                        } else {
                            popUpWindow.setMessage(buyMessage);
                            stage.addActor(popUpWindow);
                            GameManager.INSTANCE.confirmPurchase(skin);
                            ((ImageButton) actor).setStyle(own.get(actor.getName().toLowerCase(), ImageButton.ImageButtonStyle.class));
                            updateCoinLabel();
                            return;
                        }
                    }
                }
            }
        }

        popUpWindow.setMessage(nothingSelectedMessage);
        stage.addActor(popUpWindow);
    }

    private void back() {
        game.setScreen(new MainMenu(game));
    }

    private void updateCoinLabel() {
        coinLabel.setText("" + GameManager.INSTANCE.getCoinSum());
    }

}
