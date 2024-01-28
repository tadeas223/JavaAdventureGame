package world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage texture;

    private boolean collision;
    private Rectangle collider;

    public Tile(){}
    public Tile(BufferedImage texture, Rectangle collider) {
        this.texture = texture;
        this.collision = true;
        this.collider = collider;
    }

    public Tile(BufferedImage texture) {
        this.texture = texture;
        this.collision = false;
    }

    public Tile(Tile tile){
        this.texture = tile.getTexture();
        this.collider = tile.getCollider();
        this.collision = tile.isCollision();
    }



    public BufferedImage getTexture() {
        return texture;
    }

    public boolean isCollision() {
        return collision;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
        if(collider != null) collision = true;
        else collision = false;
    }
}
