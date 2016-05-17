package com.denis.game.controller.ScreenControlls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.Main;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.Levels;
import com.denis.game.model.Resource.Textures;

public class LevelsControls {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera cam;

    private Label label;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton.TextButtonStyle closedTextButtonStyle;

    private Texture openedButtonTexture;
    private Texture closedButtonTexture;

    public static boolean yesButtonPressed;
    public static boolean cancelButtonPressed;

    private TextButton backButton;
    private TextButton level1;
    private TextButton level2;
    private TextButton level3;
    private TextButton level4;
    private TextButton level5;
    private TextButton level6;
    private TextButton level7;
    private TextButton level8;
    private TextButton level9;
    private TextButton level10;
    private TextButton level11;
    private TextButton level12;
    private TextButton level13;
    private TextButton level14;
    private TextButton level15;

    public boolean backButtonPressed;
    public boolean level1Pressed;
    public boolean level2Pressed;
    public boolean level3Pressed;
    public boolean level4Pressed;
    public boolean level5Pressed;
    public boolean level6Pressed;
    public boolean level7Pressed;
    public boolean level8Pressed;
    public boolean level9Pressed;
    public boolean level10Pressed;
    public boolean level11Pressed;
    public boolean level12Pressed;
    public boolean level13Pressed;
    public boolean level14Pressed;
    public boolean level15Pressed;

    public LevelsControls(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        font = Main.font;
        labelStyle = new Label.LabelStyle(font, Color.WHITE);

        // creating textButton style
        openedButtonTexture = new Texture(Gdx.files.internal(Textures.openedButton));
        closedButtonTexture = new Texture(Gdx.files.internal(Textures.closedButton));

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(openedButtonTexture));

        closedTextButtonStyle = new TextButton.TextButtonStyle();
        closedTextButtonStyle.font = font;
        closedTextButtonStyle.up = new TextureRegionDrawable(new TextureRegion(closedButtonTexture));

        Table table = new Table();
        table.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 3);

        Table table1 = new Table();
        table1.setPosition(Assets.V_WIDTH / 2, Assets.V_HEIGHT / 8 * 7);

        label = new Label("Levels", labelStyle);
        label.setSize(300 / Assets.CPPM, 100 / Assets.CPPM);

        // creating backButton and adding inputListener on it
        backButton = new TextButton("Back", textButtonStyle);
        backButton.setSize(150 / Assets.CPPM, 70 / Assets.CPPM);
        backButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backButtonPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backButtonPressed = false;
            }
        });

        // creating level1 button and adding inputListener on it
        if(Levels.is1LevelOpen)
            level1 = new TextButton("1", textButtonStyle);
        else
            level1 = new TextButton("1", closedTextButtonStyle);
        level1.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level1.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level1Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level1Pressed = false;
            }
        });

        // creating level2 button and adding inputListener on it
        if(Levels.is2LevelOpen)
            level2 = new TextButton("2", textButtonStyle);
        else
            level2 = new TextButton("2", closedTextButtonStyle);
        level2.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level2.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level2Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level2Pressed = false;
            }
        });

        // creating level3 button and adding inputListener on it
        if(Levels.is3LevelOpen)
            level3 = new TextButton("3", textButtonStyle);
        else
            level3 = new TextButton("3", closedTextButtonStyle);
        level3.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level3.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level3Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level3Pressed = false;
            }
        });

        // creating level4 button and adding inputListener on it
        if(Levels.is4LevelOpen)
            level4 = new TextButton("4", textButtonStyle);
        else
            level4 = new TextButton("4", closedTextButtonStyle);
        level4.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level4.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level4Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level4Pressed = false;
            }
        });

        // creating level5 button and adding inputListener on it
        if(Levels.is5LevelOpen)
            level5 = new TextButton("5", textButtonStyle);
        else
            level5 = new TextButton("5", closedTextButtonStyle);
        level5.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level5.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level5Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level5Pressed = false;
            }
        });

        // creating level6 button and adding inputListener on it
        if(Levels.is6LevelOpen)
            level6 = new TextButton("6", textButtonStyle);
        else
            level6 = new TextButton("6", closedTextButtonStyle);
        level6.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level6.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level6Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level6Pressed = false;
            }
        });

        // creating level7 button and adding inputListener on it
        if(Levels.is7LevelOpen)
            level7 = new TextButton("7", textButtonStyle);
        else
            level7 = new TextButton("7", closedTextButtonStyle);
        level7.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level7.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level7Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level7Pressed = false;
            }
        });

        // creating level8 button and adding inputListener on it
        if(Levels.is8LevelOpen)
            level8 = new TextButton("8", textButtonStyle);
        else
            level8 = new TextButton("8", closedTextButtonStyle);
        level8.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level8.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level8Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level8Pressed = false;
            }
        });

        // creating level9 button and adding inputListener on it
        if(Levels.is9LevelOpen)
            level9 = new TextButton("9", textButtonStyle);
        else
            level9 = new TextButton("9", closedTextButtonStyle);
        level9.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level9.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level9Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level9Pressed = false;
            }
        });

        // creating level10 button and adding inputListener on it
        if(Levels.is10LevelOpen)
            level10 = new TextButton("10", textButtonStyle);
        else
            level10 = new TextButton("10", closedTextButtonStyle);
        level10.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level10.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level10Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level10Pressed = false;
            }
        });

        // creating level11 button and adding inputListener on it
        if(Levels.is11LevelOpen)
            level11 = new TextButton("11", textButtonStyle);
        else
            level11 = new TextButton("11", closedTextButtonStyle);
        level11.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level11.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level11Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level11Pressed = false;
            }
        });

        // creating level12 button and adding inputListener on it
        if(Levels.is12LevelOpen)
            level12 = new TextButton("12", textButtonStyle);
        else
            level12 = new TextButton("12", closedTextButtonStyle);
        level12.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level12.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level12Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level12Pressed = false;
            }
        });

        // creating level13 button and adding inputListener on it
        if(Levels.is13LevelOpen)
            level13 = new TextButton("13", textButtonStyle);
        else
            level13 = new TextButton("13", closedTextButtonStyle);
        level13.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level13.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level13Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level13Pressed = false;
            }
        });

        // creating level14 button and adding inputListener on it
        if(Levels.is14LevelOpen)
            level14 = new TextButton("14", textButtonStyle);
        else
            level14 = new TextButton("14", closedTextButtonStyle);
        level14.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level14.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level14Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level14Pressed = false;
            }
        });

        // creating level15 button and adding inputListener on it
        if(Levels.is15LevelOpen)
            level15 = new TextButton("15", textButtonStyle);
        else
            level15 = new TextButton("15", closedTextButtonStyle);
        level15.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        level15.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                level15Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level15Pressed = false;
            }
        });

        // adding elements on the screen
        table1.add(backButton).size(backButton.getWidth(), backButton.getHeight()).padRight(30);
        table1.add(label);
        table1.add();
        table1.row().padBottom(10);
        table1.debug(); // TODO delete later

        stage.addActor(table1);

        table.row().padBottom(10);
        table.add(level1).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level2).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level3).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level4).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level5).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.row().padBottom(10);
        table.add(level6).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level7).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level8).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level9).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level10).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.row().padBottom(10);
        table.add(level11).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level12).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level13).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level14).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.add(level15).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM).padRight(5);
        table.debug(); // TODO delete later

        stage.addActor(table);
    }

    public boolean isYesButtonPressed() {
        return yesButtonPressed;
    }

    public boolean isCancelButtonPressed() {
        return cancelButtonPressed;
    }

    public boolean isBackButtonPressed() {
        return backButtonPressed;
    }

    public boolean isLevel1Pressed() {
        return level1Pressed;
    }

    public boolean isLevel2Pressed() {
        return level2Pressed;
    }

    public boolean isLevel3Pressed() {
        return level3Pressed;
    }

    public boolean isLevel4Pressed() {
        return level4Pressed;
    }

    public boolean isLevel5Pressed() {
        return level5Pressed;
    }

    public boolean isLevel6Pressed() {
        return level6Pressed;
    }

    public boolean isLevel7Pressed() {
        return level7Pressed;
    }

    public boolean isLevel8Pressed() {
        return level8Pressed;
    }

    public boolean isLevel9Pressed() {
        return level9Pressed;
    }

    public boolean isLevel10Pressed() {
        return level10Pressed;
    }

    public boolean isLevel11Pressed() {
        return level11Pressed;
    }

    public boolean isLevel12Pressed() {
        return level12Pressed;
    }

    public boolean isLevel13Pressed() {
        return level13Pressed;
    }

    public boolean isLevel14Pressed() {
        return level14Pressed;
    }

    public boolean isLevel15Pressed() {
        return level15Pressed;
    }

    public void draw() {
        stage.draw();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}
