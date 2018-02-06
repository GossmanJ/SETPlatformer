package com.trifluxsoftware.set.util;

import com.badlogic.gdx.math.Vector2;

public class Gravity {

    public Gravity(Vector2 vel, float speed, float gravity, float delta){
        //create gravity i am now god thanks
        vel.y -= gravity * delta;

        //Clamp the gravity
        if(vel.y > speed){
            vel.y = speed;
        }else if(vel.y < -speed){
            vel.y = -speed;
        }
    }
}
