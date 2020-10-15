package mra.game.entities.creatures;

import mra.game.Handler;
import mra.game.entities.Entity;
import mra.game.tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;


    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        moveX();
        moveY();

    }

    public void moveX(){
        int tempX;
        if (xMove > 0){
            tempX = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH);

            if (collisionWithTile(tempX, (int) Math.floor (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile(tempX, (int) Math.floor(y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            } else {
                x = tempX * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }

        } else if (xMove < 0){
            tempX = (int) Math.floor ((x + xMove + bounds.x) / Tile.TILE_WIDTH);

            if (collisionWithTile(tempX, (int) Math.floor((y + bounds.y) / Tile.TILE_HEIGHT)) &&
                    collisionWithTile(tempX, (int)Math.floor ((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))){
                x += xMove;
            } else {
                x = tempX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY(){
        int tempY;
        if (yMove < 0 ){
            tempY = (int) Math.floor((y + yMove + bounds.y) / Tile.TILE_HEIGHT);

            if (collisionWithTile((int) Math.floor(x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
                    collisionWithTile((int) Math.floor (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY)){
                y += yMove;
            } else{
                y = tempY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (yMove > 0){
            tempY = (int) Math.floor ((y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT);

            if (collisionWithTile((int) Math.floor (x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
                    collisionWithTile((int) Math.floor (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY)){
                y += yMove;
            } else {
                y = tempY * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }


    protected boolean collisionWithTile(int x, int y){
        return !handler.getWorld().getTile(x, y).isSolid();
    }

    public static int getDefaultHealth() {
        return DEFAULT_HEALTH;
    }

    public static float getDefaultSpeed() {
        return DEFAULT_SPEED;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
