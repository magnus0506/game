package mra.game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static final int width = 32, height = 32;
    public static BufferedImage player, dirt, grass, tree;
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite_sheet32.png"));

        player = sheet.crop(width*5,0,width,height);
        dirt = sheet.crop(width*2,0,width,height);
        grass = sheet.crop(width*3,0,width,height);
        tree = sheet.crop(width*4,0,width,height);

    }
}
