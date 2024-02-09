package gameObjects;

import gameObject.GameObject;
import gameObject.Module;
import generic.MovementModule;
import generic.anim.AnimationLoader;
import generic.anim.AnimationModule;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
    private MovementModule movementModule = new MovementModule(300);
    private AnimationModule animationModule =
            new AnimationModule(AnimationLoader.loadAnimationController("data/animations/necoarc"));

    private CollisionModule collisionModule = new CollisionModule(new Rectangle(27,139,64,23));
    private Inventory inventory = new Inventory();
    private PopUp popUp = new PopUp('i',"INVENTORY [I]");
    private float time = 0;

    public Player(){
        setName("PLAYER");
        try{
            setTexture(ImageIO.read(new File("data/animations/necoarc/idle/1.png")));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        setScale(.5f,.5f);
        setPosition(211,160);

        setLayer(1000);

        inventory.setActive(false);
        popUp.setActive(true);
        popUp.setPosition(30,50);

        addChild(inventory);
        addChild(popUp);

        addModule(collisionModule);
        addModule(movementModule);
        addModule(animationModule);
    }

    @Override
    public void update(float time) {
        super.update(time);

        if(movementModule.isLeft() || movementModule.isUp()){
            animationModule.startAnimation("walkleft");
        } else if(movementModule.isRight() || movementModule.isDown()){
            animationModule.startAnimation("walkright");
        } else {
            animationModule.startAnimation("idle");
        }

        if(this.time >= 0.5f){
            if(popUp.isPressed() && !inventory.isActive()){
                popUp.setText("CLOSE [I]");
                popUp.setPosition(30,370);
                inventory.setActive(true);
                this.time = 0;
            } else if(popUp.isPressed() && inventory.isActive()){
                inventory.setActive(false);
                popUp.setText("INVENTORY [I]");
                popUp.setPosition(30,50);
                this.time = 0;
            }
        }

        this.time += time;
    }

    public MovementModule getMovementModule() {
        return movementModule;
    }

    public AnimationModule getAnimationModule() {
        return animationModule;
    }

    public CollisionModule getCollisionModule() {
        return collisionModule;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public PopUp getPopUp() {
        return popUp;
    }
}
