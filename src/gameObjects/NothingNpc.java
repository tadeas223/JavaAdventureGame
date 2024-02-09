package gameObjects;

import generic.anim.AnimationLoader;
import generic.anim.AnimationModule;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NothingNpc extends Npc{
    CollisionModule collisionModule = new CollisionModule(new Rectangle(28,99,571,100));
    AnimationModule animationModule = new AnimationModule(AnimationLoader.loadAnimationController("data/animations/nothing"));
    public NothingNpc() {
        super("data/dialogs/nothing.dg", new Rectangle(-50,-50,700,400));

        try{
            setTexture(ImageIO.read(new File("data/animations/nothing/rotate/1.png")));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        addModule(collisionModule);
        addModule(animationModule);

        setScale(.9f,.9f);
    }

    @Override
    public void update(float time) {
        super.update(time);

        animationModule.startAnimation("rotate");
    }
}
