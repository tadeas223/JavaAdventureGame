package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WorldLoader {
    public World loadWorld(String path) {
        String tilePath = "";
        Tile[][] tiles;
        String[][] tileMap = new String[0][0];
        HashMap<String,Tile> tileHashMap = new HashMap<>();

        ArrayList<String> lines = new ArrayList<>();

        try{
            Scanner sc = new Scanner(new File(path + "/data.world"));

            while(sc.hasNextLine()){
                lines.add(sc.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(String s : lines){
            if(s.contains("TILE_PATH")){
                String[] split1 = s.split(" ");
                String[] split2 = split1[1].split("[+]");

                for(int i = 0; i < split2.length;i++){
                    if(split2[i].equals("WORLD_PATH")){
                        tilePath += path;
                    }
                    else{
                        tilePath += split2[i];
                    }
                }
                break;
            }
        }

        for(int i = 0; i < lines.size();i++){
            if(lines.get(i).contains("MAP")){
                String[] split1 = lines.get(i).split(" ");
                String[] split2 = split1[1].split("x");

                tileMap = new String[Integer.parseInt(split2[0])][Integer.parseInt(split2[1])];

                for(int y = i; y < tileMap.length+i;y++){
                    String[] splitX = lines.get(y).split(",");
                    for(int x = 0; x < tileMap[0].length;x++){
                        tileMap[x][y] = splitX[x];
                    }
                }
            }
        }

        for(int y = 0; y < tileMap.length;y++){
            for(int x = 0; x < tileMap[0].length;x++){
                for(String s : lines){
                    if(s.equals(tileMap[x][y])){
                        try{
                            int tileNum = Integer.parseInt(s);
                            if(tileHashMap.get(String.valueOf(tileNum)) == null){

                            }

                        }catch (NumberFormatException e){

                        }
                    }

                }
            }
        }
        return null;
    }
}
