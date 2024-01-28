package gameObjects;

import anim.Animation;
import anim.AnimationController;
import anim.AnimationLoader;
import anim.AnimationModule;
import collision.ColliderModule;
import gameObject.GameObject;
import test.MovementModule;
import tools.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
    private MovementModule movementModule = new MovementModule(150);
    private AnimationModule animationModule =
            new AnimationModule(AnimationLoader.loadAnimationController("data/animations/necoarc"));

    private ColliderModule colliderModule = new ColliderModule(new Rectangle(27,139,64,23));
    private boolean wasGoingLeft = false;
    public Player() {
        try{
            setTexture(ImageIO.read(new File("data/animations/necoarc/idle/1.png")));
        }catch (IOException e){
            throw  new RuntimeException(e);
        }

        clampSizeToTexture();
        setScale(new Vector2(.5f,.5f));
        setPosition(400,400);

        addModule(movementModule);
        addModule(colliderModule);

        addModule(animationModule);
    }

    @Override
    public void update(float time) {
        super.update(time);
        if (movementModule.isLeft() && !movementModule.isRight()){
            animationModule.startAnimation("walkright");
            wasGoingLeft = true;
        } else if(!movementModule.isLeft() && movementModule.isRight()){
            animationModule.startAnimation("walkleft");
            wasGoingLeft = false;
        } else if(movementModule.isUp() || movementModule.isDown()){
            if(wasGoingLeft){
                animationModule.startAnimation("walkleft");
            } else {
                animationModule.startAnimation("walkright");
            }
        } else{
            animationModule.startAnimation("idle");
        }
    }
}
