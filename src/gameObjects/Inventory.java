package gameObjects;

import gameObject.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Inventory extends GameObject {
    private ArrayList<GameObject> items = new ArrayList<>();

    public Inventory(){
        drawInventory();
    }

    public void drawInventory(){
        BufferedImage texture = new BufferedImage(100,400,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = texture.createGraphics();
        g2.setColor(Color.gray);
        g2.fillRect(0,0,100,400);
        g2.setColor(Color.black);
        for(int i = 0; i < items.size();i++){
            g2.drawString(items.get(i).getName(),10,(i != 0)?(i*20) : 20);
        }
        g2.dispose();
        setTexture(texture);
    }

    public void addItem(GameObject g){
        items.add(g);
        drawInventory();
    }

    public void removeItem(GameObject g){
        items.remove(g);
        drawInventory();
    }

    public ArrayList<GameObject> getItems() {
        return items;
    }
}
