package mra.game.states;

import mra.game.Game;
import mra.game.Handler;

import java.awt.*;

public abstract class State {

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);


}
