package gameObjects;

import collision.ColliderModule;
import gameObject.GameObject;
import gameObject.Module;
import org.w3c.dom.css.Rect;
import world.Map;
import world.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MapHolder extends GameObject {
    private final int TILE_SIZE = 32;
    private Map map;

    public MapHolder(Map map){
        this.map = map;
        setLayer(-1);
        generateTexture();
    }

    public void generateTexture(){
        setModules(new ArrayList<>());
        Tile[][] tiles = map.getTiles();

        BufferedImage texture = new BufferedImage(TILE_SIZE*tiles.length,
                TILE_SIZE*tiles[0].length,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = texture.createGraphics();


        for(int y = 0; y < tiles.length; y++){
            for(int x = 0; x < tiles[0].length; x++){
                if(tiles[x][y].isCollision()){
                    Rectangle rect = new Rectangle();
                    rect.x = x * TILE_SIZE;
                    rect.y = y * TILE_SIZE;
                    rect.width = tiles[x][y].getCollider().width;
                    rect.height = tiles[x][y].getCollider().height;

                    addModule(new ColliderModule(rect));
                }
                g2.drawImage(tiles[x][y].getTexture(),x*TILE_SIZE,y*TILE_SIZE,null);
            }
        }
        g2.dispose();
        setTexture(texture);
        clampSizeToTexture();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
        generateTexture();
    }
}
