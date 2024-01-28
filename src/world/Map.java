package world;

public class Map {
    private String name;
    private Tile[][] tiles;

    public Map(Tile[][] tiles){
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        return tiles.length + "," + tiles[0].length;
    }
}
