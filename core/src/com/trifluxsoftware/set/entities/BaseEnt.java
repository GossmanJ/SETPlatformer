package com.trifluxsoftware.set.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.trifluxsoftware.set.util.Collision;
import com.trifluxsoftware.set.util.Gravity;

public abstract class BaseEnt extends Sprite {

    boolean wasColliding;
    float sideSpeed;
    float oldX, oldY;

    public BaseEnt(Sprite sprite){
        super(sprite);
        wasColliding = false;
    }

    public TiledMapTileLayer getCollisionLayer(TiledMapTileLayer collisionLayer){
        return collisionLayer;
    }

    public float setSideSpeed(float sideSpeed){
        this.sideSpeed = sideSpeed;
        return sideSpeed;
    }

    public void setGrav(Vector2 vel, float speed, float grav, float delta){
        new Gravity(vel, speed, grav, delta);
    }

    public void EntCol(Vector2  vel, float speed, float delta, TiledMapTileLayer collisionLayer){
        Collision collision = new Collision(this, collisionLayer);

        float oldX = getX(), oldY = getY();
        this.oldX = oldX;
        this.oldY = oldY;

        vel.x = sideSpeed;

        //jump when stuck
        if(vel.x == 0){
            vel.y = speed;
        }

        setX(getX() + vel.x * delta);

        if (vel.x < 0 || vel.x > 0) { //moving left
            collision.collideX = collision.collisionXDetect();
        }

        // If first frame of collision
        if (collision.collideX && !wasColliding) {
            setX(getX() + -vel.x * delta);
            if(wasColliding){
                sideSpeed = -sideSpeed;
                wasColliding = false;
            }
        }

        // If not colliding, but was previously
        if (!collision.collideX && wasColliding){
            wasColliding = true;
        }
        setY(getY() + vel.y * delta);

        if (vel.y < 0 || vel.y > 0) { //falling
            collision.collideY = collision.collisionYDetect();
        }

        if (collision.collideY){
            setY(oldY);
            vel.y = 0;
        }

    }
}
