package com.trifluxsoftware.set.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.trifluxsoftware.set.entities.BaseEnt;
import com.trifluxsoftware.set.player.Player;

public class Collision extends Sprite{

    private TiledMapTileLayer collisionLayer;
    private BaseEnt ent;
    public boolean collideX = false, collideY = false;

    public Collision(BaseEnt ent, TiledMapTileLayer collisionLayer) {
        this.ent = ent;
        this.collisionLayer = collisionLayer;
    }

    private boolean isCellBlocked(float x, float y){
        TiledMapTileLayer.Cell cell = ent.getCollisionLayer(collisionLayer).getCell((int) (x / ent.getCollisionLayer(collisionLayer).getTileWidth()), (int) (y / ent.getCollisionLayer(collisionLayer).getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collisionXDetect() {
        for (float step = 0; step < ent.getHeight(); step += ent.getCollisionLayer(collisionLayer).getTileHeight() / 4) {
            if (isCellBlocked(ent.getX(), ent.getY() + step)) { //left then right
                collideX = true;
                return true;
            }else if(isCellBlocked(ent.getX() + ent.getWidth(), ent.getY() + step)){
                collideX = true;
                return true;
            }
        }
        return false;
    }

    public boolean collisionYDetect() {
        for(float step = 0; step < ent.getWidth(); step += ent.getCollisionLayer(collisionLayer).getTileWidth() / 6) {
            if (isCellBlocked(ent.getX() + step, ent.getY() + ent.getHeight()) || isCellBlocked(ent.getX() + step, ent.getY())) { //top then bottom
                collideY = true;
                return true;
            }
        }
        return false;
    }
}
