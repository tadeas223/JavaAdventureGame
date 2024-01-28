import engine.Engine;
import gameObjects.MapHolder;
import gameObjects.Npc;
import gameObjects.Player;
import world.MapLoader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Engine engine = Engine.getInstance();

        engine.addGameObject(new Player());
        engine.addGameObject(new Npc());
        engine.addGameObject(new MapHolder(MapLoader.loadMap("data/world/maps/1.map","data/world")));

    }
}