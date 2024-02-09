package gameObjects;

import gameObject.GameObject;
import generic.collision.CollisionModule;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends GameObject {
    private Rectangle collider;
    public Tile(BufferedImage texture, Rectangle collider) {
        setTexture(texture);
        this.collider = collider;
        if(collider != null) addModule(new CollisionModule(collider));
    }

    public Tile(Tile tile){
        this.setTexture(tile.getTexture());
        this.collider = tile.collider;
        if(tile.collider != null) addModule(new CollisionModule(tile.collider));
    }

    public Rectangle getCollider() {
        return collider;
    }
}
