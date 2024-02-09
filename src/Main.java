import engine.Engine;
import gameObjects.Player;
import gameObjects.World;
import world.WorldLoader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Engine engine = Engine.getInstance();

        Player player = new Player();

        World world = WorldLoader.loadWorld("data/world/");
        world.setPlayer(player);

        engine.addGameObject(world);
        engine.addGameObject(player);


        //engine.addGameObject(new MapHolder(MapLoader.loadMap("data/world/maps/1.map","data/world")));

    }
}