package gameObjects;

import gameObject.GameObject;
import world.Map;
import world.MapLoader;

public class WorldHolder extends GameObject {
    private Map map = MapLoader.loadMap("data/world/maps/1.map","data/world");
    public WorldHolder(){
    }

    @Override
    public void update(float time) {
        super.update(time);
    }
}
