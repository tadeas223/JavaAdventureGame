package world;

import gameObjects.Tile;
import tools.CustomFileReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileLoader {
    public static Tile loadTile(String path){
        Rectangle collider = null;
        BufferedImage texture;

        try{
            String[] colliderString = CustomFileReader.readComplexValue(
                    path.substring(0,path.length()-3)+"tile","COLLIDER");

            if(colliderString != null){
                collider = new Rectangle();
                for(String s : colliderString){
                    if(s.contains("X")){
                        String[] split = s.split(" ");
                        collider.x = Integer.parseInt(split[1]);
                    }
                    if(s.contains("Y")){
                        String[] split = s.split(" ");
                        collider.y = Integer.parseInt(split[1]);
                    }
                    if(s.contains("WIDTH")){
                        String[] split = s.split(" ");
                        collider.width = Integer.parseInt(split[1]);
                    }
                    if(s.contains("HEIGHT")){
                        String[] split = s.split(" ");
                        collider.height = Integer.parseInt(split[1]);
                    }
                }
            }
        }catch (RuntimeException e){}


        try{
            texture = ImageIO.read(new File(path));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return new Tile(texture,collider);

    }
}
