package com.trifluxsoftware.set.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.trifluxsoftware.set.enemies.BurgerBois;
import com.trifluxsoftware.set.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class World1 implements Screen{

    private TiledMap map;
    private OrthogonalTiledMapRenderer orthRenderer;
    private OrthographicCamera cam;
    public Player player;

    public BurgerBois boi;

    String[] boiModels = {"imgs/player/face_right.png", "imgs/player/face_right.png"};
    //int[] boiPos = {};
    ArrayList<BurgerBois> bois = new ArrayList<BurgerBois>();


    @Override
    public void show() {
        map = new TmxMapLoader().load("maps/world1a.tmx");

        orthRenderer = new OrthogonalTiledMapRenderer(map);

        cam = new OrthographicCamera();

        /*for(int i = 0; i < 3; i++){
            Random rnd = new Random();
            //int rndBoiPos = rnd.nextInt(boiPos.length);
            String rndBoiModel = boiModels[rnd.nextInt(boiModels.length)];
            BurgerBois boi = new BurgerBois(new Sprite(new Texture(rndBoiModel)), (TiledMapTileLayer)map.getLayers().get("collision"));
            boi.setPosition(3 * boi.getCollisionLayer().getTileWidth(), (boi.getCollisionLayer().getHeight()-7) * boi.getCollisionLayer().getTileHeight());
            bois.add(boi);
        }*/

        boi = new BurgerBois(new Sprite(new Texture("sprites/enemies/burger_boi_walk.png")), (TiledMapTileLayer)map.getLayers().get("collision"));
        boi.setPosition(3 * boi.getCollisionLayer().getTileWidth(), (boi.getCollisionLayer().getHeight() - 7) * boi.getCollisionLayer().getTileHeight());

        player = new Player(new Sprite(new Texture("sprites/player/face_right.png")), (TiledMapTileLayer)map.getLayers().get("collision"));
        player.setPosition(2 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 7) * player.getCollisionLayer().getTileHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        cam.update();

        orthRenderer.setView(cam);
        orthRenderer.render();

        orthRenderer.getBatch().begin();
        player.draw(orthRenderer.getBatch());
        boi.draw(orthRenderer.getBatch());

        for (int i = 0; i < bois.size(); i++){
            BurgerBois boi = bois.get(i);
            boi.draw(orthRenderer.getBatch());
        }
      
        orthRenderer.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportHeight = height / 5;
        cam.viewportWidth = width / 5;
    }

    @Override
    public void pause() {
        player.oldX = player.getX();
        player.setX(player.oldX);

        player.oldY = player.getY();
        player.setY(player.oldY);
    }

    @Override
    public void resume() {
        player.setY(player.oldY);
        player.setX(player.oldX);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        orthRenderer.dispose();
        player.getTexture().dispose();
    }

    public Player getPlayer(){
        return player;
    }
}
