package gameObjects;

import gameObject.GameObject;
import world.Linker;

import java.awt.image.BufferedImage;


public class World extends GameObject {
    private Map currentMap;
    private GameObject player = null;
    public World(Map currentMap){
        this.currentMap = currentMap;
        addChild(currentMap);
    }

    public void changeMap(char direction) {
        Linker mapLinker = currentMap.getLiner(direction);

        if(mapLinker != null){
            removeChild(currentMap);
            currentMap = mapLinker.getMap();
            addChild(currentMap);
        }
    }

    @Override
    public void update(float time) {
        super.update(time);

        if(player != null){
            Tile[][] tilemap = currentMap.getTileMap();
            if(player.getX() > tilemap[tilemap[0].length-1][0].getX() + player.getWidth()/2){
                player.setX(tilemap[0][0].getX());
                changeMap('w');
            }
            if(player.getX() < tilemap[0][0].getX() - player.getWidth()/2){
                player.setX(tilemap[tilemap[0].length-1][0].getX());
                changeMap('e');
            }
            if(player.getY() > tilemap[0][tilemap[0].length-1].getY() + player.getHeight()/2){
                player.setY(tilemap[0][0].getY());
                changeMap('s');
            }
            if(player.getY() < tilemap[0][0].getY() - player.getHeight()/2){
                player.setY(tilemap[0][tilemap[0].length-1].getY());
                changeMap('n');
            }
        }
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    public GameObject getPlayer() {
        return player;
    }

    public void setPlayer(GameObject player) {
        this.player = player;
    }
}
