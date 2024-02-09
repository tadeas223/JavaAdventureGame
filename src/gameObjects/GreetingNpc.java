package gameObjects;


import generic.anim.AnimationLoader;
import generic.anim.AnimationModule;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GreetingNpc extends Npc {
    CollisionModule collisionModule = new CollisionModule(new Rectangle(27,139,64,23));
    AnimationModule animationModule = new AnimationModule(AnimationLoader.loadAnimationController("data/animations/necoarc"));

    public GreetingNpc() {
        super("data/dialogs/greeting.dg", new Rectangle(-20,-20,152,216));

        try{
            setTexture(ImageIO.read(new File("data/animations/necoarc/idle/1.png")));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        setScale(.5f,.5f);

        addModule(collisionModule);
        addModule(animationModule);

    }

    @Override
    public void update(float time) {
        super.update(time);
        animationModule.startAnimation("idle");
    }
}