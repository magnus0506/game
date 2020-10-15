package mra.game.entities.statics;

import mra.game.Handler;
import mra.game.gfx.Assets;
import mra.game.tiles.Tile;

import java.awt.*;

public class Villain extends StaticEntity{

    public Villain(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.villain, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
