package world;

import gameObjects.Map;

public class Linker {
    private Map map;
    private char direction;

    public Linker(Map map, char direction) {
        this.map = map;
        this.direction = direction;
    }

    public Map getMap() {
        return map;
    }

    public char getDirection() {
        return direction;
    }
}
