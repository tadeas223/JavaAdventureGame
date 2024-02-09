package gameObjects;

import engine.Engine;
import gameObject.GameObject;
import generic.collision.CollisionListener;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TrapDoor extends GameObject implements CollisionListener {
    private CollisionModule collisionModule = new CollisionModule(new Rectangle(0,0,32,32),true);
    private PopUp popUp = new PopUp('e',"ENTER [E]");
    private Player player = null;
    private float time = 0;
    public TrapDoor(){
        try{
            setTexture(ImageIO.read(new File("data/world/tiles/159.png")));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        setScale(3,3);

        collisionModule.addCollisionListener(this);
        popUp.setActive(false);

        addModule(collisionModule);
        addChild(popUp);
    }

    @Override
    public void update(float time) {
        super.update(time);

        if(this.time >= 0.5f) {
            if(popUp.isPressed() && popUp.isActive()){
                popUp.setActive(false);
                this.time = 0;
                for(GameObject g : Engine.getInstance().getGameObjects()){
                    if(g instanceof World){
                        ((World) g).changeMap('n');
                        player.setPosition(192,192);
                    }
                }
            }
        }
        this.time += time;
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
        }
    }
}
