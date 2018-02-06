package com.trifluxsoftware.set.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.trifluxsoftware.set.entities.BaseEnt;
import com.trifluxsoftware.set.util.Collision;
import com.trifluxsoftware.set.util.Gravity;

public class BurgerBois extends BaseEnt {
    private TiledMapTileLayer collisionLayer;
    Vector2 vel = new Vector2();
    float sideSpeed = -20;
    int _SPEED = 60;

    public BurgerBois(Sprite sprite, TiledMapTileLayer collisionLayer){
        super(sprite);
        this.collisionLayer = collisionLayer;
    }

    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta){
        vel.x = sideSpeed;
        setSideSpeed(sideSpeed);
        setGrav(vel, _SPEED, 60*2f, delta);
        EntCol(vel, _SPEED, delta, collisionLayer);
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }
}
