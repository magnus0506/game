package mra.game;

import mra.game.gfx.Assets;
import mra.game.gfx.Display;
import mra.game.gfx.GameCamera;
import mra.game.input.KeyManager;
import mra.game.input.MouseManager;
import mra.game.states.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    //JFrame variables
    private Display display;
    private int width, height;
    public String title;

    //Runnable variables
    private boolean running = false;
    private Thread thread;

    //Gfx variables
    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState, menuState, settingsState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);

        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);

        menuState = new MenuState(handler);
        gameState = new GameState(handler);
        settingsState = new SettingsState(handler);
        StateManager.setState(menuState);
    }

    private void tick() {
        keyManager.tick();
        if (StateManager.getState() != null)
            StateManager.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (StateManager.getState() != null)
            StateManager.getState().render(g);

        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer>= 1_000_000_000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
