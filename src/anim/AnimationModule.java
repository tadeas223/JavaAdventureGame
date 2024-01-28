package anim;

import gameObject.Module;

import java.util.HashMap;

public class AnimationModule extends Module{
    private AnimationController animationController;
    private float time = 0;

    public AnimationModule(AnimationController animationController){
        this.animationController = animationController;
    }

    @Override
    public void update(float v) {
        if(animationController.getCurrentAnimation() != null){
            time += v;
            if(time >= animationController.getCurrentAnimation().getWait()){
                if (!changeTexture()) animationController.stopAnimation();
                time = 0;
            }
        }
    }

    public void startAnimation(String name){
        animationController.startAnimation(name);
    }

    private boolean changeTexture(){
        if(animationController.hasNext()){
            source.setTexture(animationController.next());
            return true;
        }
        return false;
    }
}
