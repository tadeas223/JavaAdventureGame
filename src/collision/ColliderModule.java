package collision;

import engine.Engine;
import gameObject.GameObject;
import gameObject.Module;
import tools.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class ColliderModule extends Module {
    private ArrayList<CollisionListener> listeners = new ArrayList<>();
    private Rectangle collisionArea;
    private Vector2 previousPosition;
    private boolean isTrigger = false;
    private boolean colliding = false;
    public ColliderModule(Rectangle collisionArea) {
        this.collisionArea = collisionArea;
    }

    public ColliderModule(Rectangle collisionArea, boolean isTrigger) {
        this.collisionArea = collisionArea;
        this.isTrigger = isTrigger;
        previousPosition = new Vector2(source.getRectangle().x,source.getRectangle().y);
    }

    @Override
    public void update(float v) {
        Graphics2D g2 = source.getTexture().createGraphics();
        g2.setColor(Color.red);
        g2.fillRect(collisionArea.x,collisionArea.y,collisionArea.width,collisionArea.height);
        g2.dispose();


        for(GameObject g : Engine.getInstance().getGameObjects()){
            if(g != source){
                ColliderModule colliderModule = (ColliderModule) g.findModule(this.getClass());
                if(colliderModule != null){
                    Rectangle collision1 = new Rectangle(collisionArea);
                    collision1.x += source.getRectangle().x;
                    collision1.y += source.getRectangle().y;
                    collision1.width *= (int) source.getScale().x;
                    collision1.height *= (int) source.getScale().y;

                    Rectangle collision2 = new Rectangle(colliderModule.collisionArea);
                    collision2.x += colliderModule.getSource().getRectangle().x;
                    collision2.y += colliderModule.getSource().getRectangle().y;
                    collision2.width = (int) colliderModule.getSource().getScale().x;
                    collision2.height = (int) colliderModule.getSource().getScale().y;

//                    System.out.println(collision2.width + " " + colliderModule.getSource().getScale().x);
                    if(collision1.intersects(collision2)){
                        System.out.println("a");
                        for(CollisionListener l : listeners){
                            l.onCollisionEnter(g);
                        }
                        if(!isTrigger){
                            colliding = true;
                            if(previousPosition != null){
                                source.setPosition((int) previousPosition.x, (int) previousPosition.y);
                            }

                        }
                    }
                }
            }
        }
        previousPosition = new Vector2(source.getRectangle().x,source.getRectangle().y);
    }

    public void removeCollisionListener(CollisionListener listener){
        listeners.remove(listener);
    }

    public void addCollisionListener(CollisionListener listener){
        listeners.add(listener);
    }

    public void setCollisionArea(Rectangle collisionArea) {
        this.collisionArea = collisionArea;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }
}
