package gameObjects;

import gameObject.GameObject;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class House extends GameObject {
    private CollisionModule collisionModule = new CollisionModule(new Rectangle(0,0,150,200));
    public House(){
        try {
            setTexture(ImageIO.read(new File("data/world/house.png")));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        addModule(collisionModule);
    }
}
