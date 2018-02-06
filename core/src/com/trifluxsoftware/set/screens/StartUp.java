package com.trifluxsoftware.set.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trifluxsoftware.set.MainGame;

public class StartUp implements Screen {
    private final MainGame game;
    private SpriteBatch startupBatch;
    private Sprite logo;

    private float elapsed;

    public StartUp(final MainGame game){
        this.game = game;
    }

    @Override
    public void show() {
        startupBatch = new SpriteBatch();

        Texture logoTexture = new Texture("imgs/logo.png");
        logo = new Sprite(logoTexture);
        logo.setX(Gdx.graphics.getWidth() / 2 - (logo.getWidth() / 2));
        logo.setY(Gdx.graphics.getHeight() / 2);
    }

    @Override
    public void render(float delta) {
        elapsed += delta;

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        startupBatch.begin();
        logo.draw(startupBatch);
        startupBatch.end();

        if(elapsed > 5 || Gdx.input.isTouched()){ //Shortly display the startup screen with our logo
            System.out.println(elapsed);
            game.setScreen(new MainMenu(game));
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
        startupBatch.dispose();
        logo.getTexture().dispose();
    }
}
