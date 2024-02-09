package gameObjects;

import engine.Engine;
import gameObject.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class PopUp extends GameObject {
    private boolean pressed = false;
    private char key;
    private String text;

    public PopUp(char key,String text){
        this.key = key;
        this.text = text;
        Engine.getInstance().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(isActive()){
                    if(e.getKeyChar() == key){
                        pressed = true;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                    if(e.getKeyChar() == key){
                        pressed = false;
                    }
            }
        });

        BufferedImage texture = new BufferedImage(250,50,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = texture.createGraphics();
        g2.setColor(Color.black);
        g2.drawString(text,0,20);
        g2.dispose();
        setTexture(texture);

        setPosition(30,30);
    }

    public boolean isPressed() {
        return pressed;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        BufferedImage texture = new BufferedImage(250,50,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = texture.createGraphics();
        g2.setColor(Color.black);
        g2.drawString(text,0,20);
        g2.dispose();
        setTexture(texture);
    }
}
