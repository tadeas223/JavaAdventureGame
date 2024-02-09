
package gameObjects;


import generic.anim.AnimationLoader;
import generic.anim.AnimationModule;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DanceNpc extends Npc {
    CollisionModule collisionModule = new CollisionModule(new Rectangle(25,84,73,23));
    AnimationModule animationModule = new AnimationModule(AnimationLoader.loadAnimationController("data/animations/necoarc"));

    public DanceNpc() {
        super("data/dialogs/dance.dg", new Rectangle(-20,-20,152,216));

        try{
            setTexture(ImageIO.read(new File("data/animations/necoarc/dance2/1.png")));
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
        animationModule.startAnimation("dance2");
    }

}