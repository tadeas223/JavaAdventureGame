package anim;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;

public class AnimationController implements Iterator<BufferedImage> {
    private HashMap<String,Animation> animations = new HashMap<>();private int iteration = 0;

    private Animation currentAnimation = null;

    public void addAnimation(String name,Animation animation){
        animations.put(name,animation);
    }

    public void removeAnimation(String name){
        animations.remove(name);
    }

    public HashMap<String, Animation> getAnimations() {
        return animations;
    }

    public void startAnimation(String name){
        if(currentAnimation != animations.get(name)){
            currentAnimation = animations.get(name);
            currentAnimation.restart();
        }
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public void stopAnimation(){
        currentAnimation = null;
    }

    @Override
    public boolean hasNext() {
        return currentAnimation.hasNext();
    }

    @Override
    public BufferedImage next() {
        return currentAnimation.next();
    }
}
