package mra.game.entities.creatures;

import mra.game.Handler;
import mra.game.gfx.Animation;
import mra.game.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    //Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private int direction = 0;

    public Player(Handler handler, float x, float y) {
        super(handler,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        speed = 6.0f;
        bounds.x = 17;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animations
        animationDown = new Animation(500,Assets.player_down);
        animationUp = new Animation(500,Assets.player_up);
        animationLeft = new Animation(500,Assets.player_left);
        animationRight = new Animation(500,Assets.player_right);
    }

    @Override
    public void tick() {
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

//        g.setColor(Color.RED);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()),bounds.width, bounds.height);
    }

    public BufferedImage getCurrentAnimation() {
        if (xMove != 0 || yMove != 0) {
            if (xMove < 0) {
                direction = 3;
                return animationLeft.getCurrentFrame();
            } else if (xMove > 0) {
                direction = 2;
                return animationRight.getCurrentFrame();
            } else if (yMove < 0) {
                direction = 1;
                return animationUp.getCurrentFrame();
            } else {
                direction = 0;
                return animationDown.getCurrentFrame();
            }
        } else return Assets.player_standing[direction];
    }
//    private BufferedImage getCurrentAnimationFrame(){
//        if(xMove < 0){
//            return animationLeft.getCurrentFrame();
//        } else if (xMove > 0){
//            return animationRight.getCurrentFrame();
//        } else if (yMove < 0){
//            return animationUp.getCurrentFrame();
//        } else {
//            return animationDown.getCurrentFrame();
//        }
//    }
}
