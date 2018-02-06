package com.trifluxsoftware.set.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.trifluxsoftware.set.MainGame;
import com.trifluxsoftware.set.worlds.World1;

public class MainMenu implements Screen {

    private MainGame game;

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton btnPlay, btnQuit, btnLoad;
    public static BitmapFont largeB, smallB, largeW, smallW;
    private Label header;
    private float elapsed;

    public MainMenu(MainGame game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/buttons/button.pack");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        smallW = new BitmapFont(Gdx.files.internal("fonts/12pxW.fnt"), false);
        largeW = new BitmapFont(Gdx.files.internal("fonts/24pxW.fnt"), false);

        smallB = new BitmapFont(Gdx.files.internal("fonts/12pxB.fnt"), false);
        largeB = new BitmapFont(Gdx.files.internal("fonts/24pxB.fnt"), false);

        TextButton.TextButtonStyle  txtBtnStyle = new TextButton.TextButtonStyle();
        txtBtnStyle.up = skin.getDrawable("button.up");
        txtBtnStyle.down = skin.getDrawable("button.down");
        txtBtnStyle.pressedOffsetX = 1;
        txtBtnStyle.pressedOffsetY = -1;
        txtBtnStyle.font = largeB;

        btnPlay = new TextButton("Play", txtBtnStyle);
        btnPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new World1());
                dispose();
            }
        });
        btnPlay.pad(5);

        btnLoad = new TextButton("Load Game", txtBtnStyle);
        btnLoad.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadScreen(game));
                dispose();
            }
        });

        btnQuit = new TextButton("Quit", txtBtnStyle);
        btnQuit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });
        btnQuit.pad(5);

        Label.LabelStyle headStyle = new Label.LabelStyle(largeW, Color.WHITE);
        header = new Label(MainGame.TITLE, headStyle);

        table.add(header);
        table.padBottom(10);
        table.row();
        table.add(btnPlay);
        table.row();
        table.add(btnLoad);
        table.row();
        table.add(btnQuit);
        table.debug(); //TODO remove later
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        elapsed += delta;

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        if(elapsed > 60){
            /*
                Add the classic video game functionality of
                replaying the start up screen if no options are selected within 60 seconds.
            */
            System.out.println(elapsed);
            game.setScreen(new StartUp(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
