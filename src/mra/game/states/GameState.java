package mra.game.states;

import mra.game.Game;
import mra.game.Handler;
import mra.game.entities.creatures.Player;
import mra.game.tiles.Tile;
import mra.game.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "resources/worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 100, 100);
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
