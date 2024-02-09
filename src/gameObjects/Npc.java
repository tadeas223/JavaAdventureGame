package gameObjects;

import dialogSystem.DialogNode;
import gameObject.GameObject;
import generic.collision.CollisionListener;
import generic.collision.CollisionModule;

import java.awt.*;

public class Npc extends GameObject implements CollisionListener {
    private Dialog dialog;
    private PopUp popUp = new PopUp('e',"INTERACT [E]");
    private CollisionModule collisionModule;

    private float time = 0;
    private Player player;

    public Npc(String dialogPath, Rectangle trigger){
        setName("NPC");

        dialog = new Dialog(dialogPath);
        collisionModule = new CollisionModule(trigger,true);

        dialog.setActive(false);
        popUp.setActive(false);

        collisionModule.addCollisionListener(this);

        addChild(dialog);
        addChild(popUp);
        addModule(collisionModule);
    }

    @Override
    public void update(float time) {
        super.update(time);

        if(this.time >= .5f){
            if(popUp.isPressed() && !dialog.isActive()){
                popUp.setText("EXIT [E]");
                popUp.setPosition(10,70);
                player.getMovementModule().setActive(false);
                player.getPopUp().setActive(false);
                dialog.setActive(true);
                this.time = 0;
            }
            else if(popUp.isPressed() && dialog.isActive()){
                popUp.setPosition(30,30);
                popUp.setText("INTERACT [E]");
                player.getMovementModule().setActive(true);
                player.getPopUp().setActive(true);
                dialog.setActive(false);
                dialog.reset();
                this.time = 0;
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
            player = null;
        }
    }
}
