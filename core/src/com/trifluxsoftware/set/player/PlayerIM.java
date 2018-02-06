package com.trifluxsoftware.set.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class PlayerIM implements InputProcessor{
    public Vector2 velocity = new Vector2();

    float speed = 75, gravity = 60 * 1.6f;

    boolean canJump = true;

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.W:
                if(canJump) {
                    velocity.y = speed;
                    canJump = false;
                }
                break;
            case Input.Keys.A:
                velocity.x = -speed;
                break;
            case Input.Keys.D:
                velocity.x = speed;
                break;
            case Input.Keys.SPACE:
                if(canJump){
                    velocity.y = speed;
                    canJump = false;
                }
                break;
            case Input.Keys.UP:
                if(canJump){
                    velocity.y = speed;
                    canJump = false;
                }
                break;
            case Input.Keys.LEFT:
                velocity.x = -speed;
                break;
            case Input.Keys.RIGHT:
                velocity.x = speed;
                break;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.W:
                break;
            case Input.Keys.A:
                velocity.x = 0;
                break;
            case Input.Keys.D:
                velocity.x = 0;
                break;
            case Input.Keys.SPACE:
                break;
            case Input.Keys.ESCAPE:
                //TODO make this open the pause menu/make pause menu
                break;
            case Input.Keys.UP:
                break;
            case Input.Keys.LEFT:
                velocity.x = 0;
                break;
            case Input.Keys.RIGHT:
                velocity.x = 0;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
