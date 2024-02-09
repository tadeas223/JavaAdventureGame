package gameObjects;

import generic.anim.AnimationLoader;
import generic.anim.AnimationModule;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CrazyNeco extends Npc{
    private AnimationModule animationModule = new AnimationModule(AnimationLoader.loadAnimationController("data/animations/necoarc"));
    //private CollisionModule collisionModule = new CollisionModule(new Rectangle(27,139,64,23));

    public CrazyNeco() {
        super("data/dialogs/crazyNeco.dg", new Rectangle(-20,-20,152,216));

        try{
            setTexture(ImageIO.read(new File("data/animations/necoarc/dance/1.png")));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        setScale(.5f,.5f);

        //addModule(collisionModule);
        addModule(animationModule);
        setLayer(1000);
    }

    @Override
    public void update(float time) {
        super.update(time);

        animationModule.startAnimation("dance");
    }
}
