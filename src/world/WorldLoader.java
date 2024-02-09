package world;

import gameObjects.Map;
import gameObjects.World;
import tools.CustomFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WorldLoader {
    public static World loadWorld(String path){
        String mapsPath = CustomFileReader.readSimpleValue(path+"/data.world","MAPS_PATH");
        mapsPath = mapsPath.split("\"")[1];

        String[] mapsString = CustomFileReader.readComplexValue(path+"/data.world","MAPS");
        String[] linkersString = CustomFileReader.readComplexValue(path+"/data.world","LINKERS");

        HashMap<Integer, Map> maps = new HashMap<>();

        for(String s : mapsString){
            maps.put(Integer.parseInt(s),MapLoader.loadMap(path+"/"+mapsPath+"/"+s+".map"));
        }

        for(String s : linkersString){
            String[] split = s.split(",");
            Linker linker = new Linker(maps.get(Integer.parseInt(split[1])),
                    split[2].charAt(0));

            maps.get(Integer.parseInt(split[0])).addLinker(linker);}

        Map currentMap = maps.get(Integer.parseInt(CustomFileReader.readSimpleValue(path+"/data.world","INITIAL_MAP")));
        return new World(currentMap);
    }
}
