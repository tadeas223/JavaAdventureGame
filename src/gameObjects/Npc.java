package gameObjects;

import collision.ColliderModule;
import gameObject.GameObject;
import tools.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Npc extends GameObject {
    private ColliderModule colliderModule = new ColliderModule(new Rectangle(27,139,64,23));
    public Npc() {
        try{
            setTexture(ImageIO.read(new File("data/animations/necoarc/idle/1.png")));
        }catch (IOException e){
            throw  new RuntimeException(e);
        }

        clampSizeToTexture();
        setScale(new Vector2(1,1));
        setPosition(200,200);

        addModule(colliderModule);
    }
}
