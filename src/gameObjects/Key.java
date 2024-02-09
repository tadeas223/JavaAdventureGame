package gameObjects;

import gameObject.GameObject;
import generic.collision.CollisionListener;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Key extends GameObject implements CollisionListener {
    private CollisionModule collisionModule = new CollisionModule(new Rectangle(0,0,32,32),true);
    private PopUp popUp = new PopUp('e',"PICK UP [E]");
    private Player player = null;
    public Key(){
        try{
            setTexture(ImageIO.read(new File("data/world/key.png")));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        setName("KEY");
        setScale(2,2);
        popUp.setActive(false);


        collisionModule.addCollisionListener(this);

        addChild(popUp);
        addModule(collisionModule);
    }

    @Override
    public void update(float time) {
        super.update(time);
        if(popUp.isPressed() && popUp.isActive()){
            player.getInventory().addItem(this);
            setActive(false);
            popUp.setActive(false);
        }
    }

    @Override
    public void onCollisionEnter(GameObject gameObject) {
        if(gameObject instanceof Player){
            player = (Player) gameObject;
            popUp.setActive(true);
        }
    }

    @Override
    public void onCollisionExit(GameObject gameObject) {
        if(gameObject instanceof Player){
            player = null;
            popUp.setActive(false);
        }
    }
}
