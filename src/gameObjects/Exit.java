package gameObjects;

import gameObject.GameObject;
import generic.collision.CollisionListener;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Exit extends GameObject implements CollisionListener {
    private CollisionModule collisionModule = new CollisionModule(new Rectangle(20,0,12,64));
    private CollisionModule trigger = new CollisionModule(new Rectangle(0,0,32,64),true);

    private PopUp popUp = new PopUp('e',"INSERT KEY [E]");
    private Player player = null;
    public Exit(){
        try {
            BufferedImage texture = new BufferedImage(32, 64, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = texture.createGraphics();
            g2.drawImage(ImageIO.read(new File("data/world/tiles/210.png")),0,0,null);
            g2.drawImage(ImageIO.read(new File("data/world/tiles/210.png")),0,32,null);
            setTexture(texture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setScale(1.5f,1.5f);

        popUp.setActive(false);
        trigger.addCollisionListener(this);

        addModule(collisionModule);
        addModule(trigger);
        addChild(popUp);

    }

    public void drawOpenDoor(){
        collisionModule.setActive(false);

        try {
            BufferedImage texture = new BufferedImage(32, 64, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = texture.createGraphics();
            g2.drawImage(ImageIO.read(new File("data/world/tiles/201.png")),0,0,null);
            g2.drawImage(ImageIO.read(new File("data/world/tiles/217.png")),0,32,null);
            setTexture(texture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(float time) {
        super.update(time);

        if(popUp.isPressed() && player != null){
            for(GameObject g : player.getInventory().getItems()){
                if(g.getName().equals("KEY")) {
                    drawOpenDoor();
                }
            }
        }
    }

    @Override
    public void onCollisionEnter(GameObject gameObject) {
        if(gameObject instanceof Player){
            popUp.setActive(true);
            player = (Player) gameObject;
        }
    }

    @Override
    public void onCollisionExit(GameObject gameObject) {
        if(gameObject instanceof Player){
            popUp.setActive(false);
            player = null;
        }
    }
}
