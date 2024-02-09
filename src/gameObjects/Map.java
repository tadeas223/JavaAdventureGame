package gameObjects;

import gameObject.GameObject;
import tools.Vector2Int;
import world.Linker;

import java.util.ArrayList;

public class Map extends GameObject {
    private World world;
    private Tile[][] tileMap;
    private ArrayList<Linker> linkers = new ArrayList<>();

    public Map(Tile[][] tileMap){
        setScale(1.5f,1.5f);
        this.tileMap = tileMap;
        createMap();
    }

    public void createMap() {
        for (int y = 0; y < tileMap[0].length;y++){
            for (int x = 0; x < tileMap.length;x++){
                GameObject g = tileMap[x][y];
                g.setScale(getScaleX(),getScaleY());
                int tileSizeX = g.getWidth();
                int tileSizeY = g.getHeight();
                g.setPosition((int) (x * tileSizeX * getScaleX()), (int) (y * tileSizeY * getScaleY()));
                g.setLayer(-2);
                addChild(g);
            }
        }
    }

    public Linker getLiner(char direction){
        for(Linker l : linkers){
            if(l.getDirection() == direction){
                return l;
            }
        }
        return null;
    }
    public void addLinker(Linker l){
        linkers.add(l);
    }

    public void removeLinker(Linker l){
        linkers.remove(l);
    }

    public void addGameObject(GameObject gameObject,int x, int y){
        gameObject.setPosition(x,y);
        addChild(gameObject);
    }
    public void addGameObject(GameObject gameObject, Vector2Int position){
        gameObject.setPosition(position);
        addChild(gameObject);
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }
}
