package tileEditor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TileChopper {
    public static void main(String[] args) throws IOException {
        BufferedImage tileSet = ImageIO.read(new File("src/tileEditor/dungeon.png"));
        int tileSize = 32;

        int tilenum = 0;
        for(int y = 0; y < tileSet.getHeight()/tileSize;y++){
            for(int x = 0; x < tileSet.getWidth()/tileSize;x++){
                BufferedImage tile = new BufferedImage(tileSize,tileSize,BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = tile.createGraphics();
                g2.drawImage(tileSet.getSubimage(x*tileSize,y*tileSize,tileSize,tileSize),0,0,null);
                g2.dispose();
                File f = new File("src/tileEditor/tiles/" + tilenum + ".png");
                f.createNewFile();
                ImageIO.write(tile,"png",new FileOutputStream("src/tileEditor/tiles/" + (tilenum+200) + ".png"));
                tilenum++;
            }
        }

    }
}
