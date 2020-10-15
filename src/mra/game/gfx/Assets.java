package mra.game.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int width = 32, height = 32;
    public static BufferedImage player, dirt, grass, tree, villain;
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;
    public static BufferedImage[] player_standing;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite_sheet32.png"));

        player_standing = new BufferedImage[4];
        player_standing[0] = sheet.crop(width*3, 0,width,height);
        player_standing[1] = sheet.crop(width*3, height,width,height);
        player_standing[2] = sheet.crop(width*3, height*2,width,height);
        player_standing[3] = sheet.crop(width*3, height*3,width,height);

        player_down = new BufferedImage[2];
        player_down[0] = sheet.crop(width*4,0,width,height);
        player_down[1] = sheet.crop(width*5,0,width,height);

        player_up = new BufferedImage[2];
        player_up[0] = sheet.crop(width*4,height,width,height);
        player_up[1] = sheet.crop(width*5,height,width,height);

        player_left = new BufferedImage[2];
        player_left[0] = sheet.crop(width*4,height*3,width,height);
        player_left[1] = sheet.crop(width*5,height*3,width,height);

        player_right = new BufferedImage[2];
        player_right[0] = sheet.crop(width*4,height*2,width,height);
        player_right[1] = sheet.crop(width*5,height*2,width,height);

        player = sheet.crop(width*3,0,width,height);
        dirt = sheet.crop(0,0,width,height);
        grass = sheet.crop(width,0,width,height);
        tree = sheet.crop(width*2,0,width,height);
        villain = sheet.crop(width*6, 0, width,height);

    }
}
