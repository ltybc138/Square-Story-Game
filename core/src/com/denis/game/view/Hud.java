package com.denis.game.view;

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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.denis.game.model.Resource.Assets;
import com.denis.game.model.Resource.PlayerStatements;
import com.denis.game.model.Resource.Textures;

public class Hud implements Disposable{

    public Stage stage;
    private Viewport viewport;

    public Integer worldTimer;
    public float timeCount;
    public static Integer score;
    public static Integer health;

    private Label countdownLabel;
    public static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label playerLabel;
    private Label healthLabel;
    public static Label healthCountLabel;

    public static String level = "1";

    public Texture buttonTexture;
    public Button.ButtonStyle buttonStyle;
    public Button button;

    public static boolean isButtonPressed = false;

    public Hud(SpriteBatch sb) {
        worldTimer = 200;
        timeCount = 0;
        score = 0;
        health = PlayerStatements.health;

        viewport = new FitViewport(Assets.V_WIDTH, Assets.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setFillParent(true);
        table1.top();
        table1.left();

        // initialize labels
        healthLabel = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthCountLabel = new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("l-" + level, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        // creating button style
        buttonTexture = new Texture(Gdx.files.internal(Textures.button));
        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        // creating level levelsMenu button and adding inputListener on it
        button = new Button(buttonStyle);
        button.setSize(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        button.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isButtonPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isButtonPressed = false;
            }
        });

        table.add(healthLabel).expandX().padTop(10);
        table.add(playerLabel).expandX().padTop(10);
        //table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        //table.add(button).expandX().padTop(10).size(Assets.SquareButton / Assets.CPPM, Assets.SquareButton / Assets.CPPM);
        table.row();
        table.add(healthCountLabel).expandX();
        table.add(scoreLabel).expandX();
        //table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);

    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }

        if(isButtonPressed)
            Gdx.app.log("Button", "Pressed");
    }

    public static boolean isBackButtonPressed() {
        return isButtonPressed;
    }

    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%04d", score));
    }

    public static void downHealth(int value) {
        health -= value;
        healthCountLabel.setText(String.format("%03d", health));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
