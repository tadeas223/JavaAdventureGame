package world;

import gameObject.GameObject;
import gameObjects.Map;
import gameObjects.Tile;
import tools.CustomFileReader;
import tools.FlipImage;
import tools.Vector2Int;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MapLoader {
    public static Map loadMap(String path){
        String sizeString = CustomFileReader.readSimpleValue(path,"SIZE");
        String[] split = sizeString.split("x");
        Vector2Int size = new Vector2Int(Integer.parseInt(split[0]),Integer.parseInt(split[1]));

        String[] map = CustomFileReader.readComplexValue(path,"MAP");

        Tile[][] tileMap = new Tile[size.x][size.y];

        String tilePath = CustomFileReader.readSimpleValue(path,"TILE_PATH");

        if(tilePath.contains("WORLD_PATH")){

            String[] pathSplit = tilePath.split("[+]");
            tilePath = "";
            for (String s : pathSplit){
                if(s.equals("WORLD_PATH")){
                    s = CustomFileReader.readSimpleValue(path,"WORLD_PATH").split("\"")[1];
                }
                String[] pathSplit2 = s.split("\"");
                for(String s2 : pathSplit2){
                    tilePath += s2;
                }
            }
        }
        HashMap<String,Tile> tiles = new HashMap<>();

        HashSet<String> unhandledTiles = new HashSet<>();

        for(int y = 0; y < size.y;y++){
            String[] splitX = map[y].split(",");
            for (int x = 0; x < size.x;x++) {
                try{
                    tiles.put(splitX[x],TileLoader.loadTile(tilePath + "/" + Integer.parseInt(splitX[x]) + ".png"));
                } catch (NumberFormatException e){
                    unhandledTiles.add(splitX[x]);
                }
            }
        }

        int complexTilesLine = CustomFileReader.getValueLine(path,"COMPLEX_TILES");
        for(String s : unhandledTiles){
            int tileLine = complexTilesLine;
            String[] lines = CustomFileReader.readLines(path);
            while(!lines[tileLine].equals(s)){
                tileLine++;
                if(tileLine >= lines.length) {
                    break;
                }
            }

            int endLine = CustomFileReader.getValueLine(path,"END",tileLine);
            String[] tileBuild = CustomFileReader.readComplexValue(path,"TILE_BUILD",tileLine,endLine);

            ArrayList<BufferedImage> textures = new ArrayList<>();

            for(String s2 : tileBuild){
                textures.add(TileLoader.loadTile(tilePath + "/" + s2 + ".png").getTexture());
            }

            Vector2Int textureSize = new Vector2Int(0,0);

            for(BufferedImage tex : textures){
                if(textureSize.x < tex.getWidth()){
                    textureSize.x = tex.getWidth();
                }
                if(textureSize.y < tex.getHeight()){
                    textureSize.y = tex.getHeight();
                }
            }

            BufferedImage texture = new BufferedImage(textureSize.x,textureSize.y,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = texture.createGraphics();
            for(BufferedImage tex : textures){
                g2.drawImage(tex,0,0,null);
            }
            g2.dispose();

            String colliderTile = CustomFileReader.readSimpleValue(path,"COLLIDER_FROM");
            Rectangle collider = null;
            if(colliderTile != null){
                collider = TileLoader.loadTile(tilePath + "/" + colliderTile + ".png").getCollider();
            }

            Tile tile = new Tile(texture,collider);

            String flip = CustomFileReader.readSimpleValue(path,"FLIP",tileLine);
            if(flip != null){
                if(flip.equals("X")){
                    tile.setTexture(FlipImage.flipX(tile.getTexture()));
                }
                else if(flip.equals("Y")){
                    tile.setTexture(FlipImage.flipY(tile.getTexture()));
                }
            }


            tiles.put(s,tile);
        }

        for(int y = 0; y < size.y;y++){
            String[] splitX = map[y].split(",");
            for (int x = 0; x < size.x;x++) {
                tileMap[x][y] = new Tile(tiles.get(splitX[x]));
            }
        }

        Map finalMap = new Map(tileMap);


        int objectsLine = CustomFileReader.getValueLine(path,"OBJECTS");
        objectsLine+= 1;
        String[] lines = CustomFileReader.readLines(path);

        for(int i = objectsLine; i < lines.length;i++){
            if(lines[i].contains("OBJECT")){

                String className = lines[i].split(" ")[1];
                try{
                    Class objClass = Class.forName(className);
                    GameObject obj = (GameObject) objClass.getDeclaredConstructor().newInstance();

                    int positonLine = CustomFileReader.getValueLine(path,"POSITION",i,true);

                    int j = positonLine;

                    ArrayList<String> position = new ArrayList<>();

                    if(j!=-1){
                        while (!lines[j].equals("END")){
                            position.add(lines[j]);
                            j++;
                        }
                    }

                    Vector2Int pos = new Vector2Int(0,0);

                    for(String s : position){
                        if(s.contains("X")){
                            pos.x = Integer.parseInt(s.split(" ")[1]);
                        }
                        if(s.contains("Y")){
                            pos.y = Integer.parseInt(s.split(" ")[1]);
                        }
                    }

                    finalMap.addGameObject(obj,pos);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         NoSuchMethodException | InvocationTargetException e){
                    throw new RuntimeException(e);
                }
            }
        }

        return finalMap;
    }
}
