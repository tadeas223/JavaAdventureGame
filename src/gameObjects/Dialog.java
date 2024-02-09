package gameObjects;

import dialogSystem.DialogHandler;
import engine.Engine;
import gameObject.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Dialog extends GameObject implements KeyListener {
    private ArrayList<String> hints = new ArrayList<>();
    private DialogHandler dialogHandler;
    public Dialog(String dialogPath){
        try{
            BufferedImage texture = new BufferedImage(400,100,BufferedImage.TYPE_INT_ARGB);
            setTexture(texture);

            dialogHandler = (DialogHandler) new ObjectInputStream(new FileInputStream(dialogPath)).readObject();
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        BufferedImage texture = getTexture();
        Graphics2D g2 = texture.createGraphics();
        g2.setColor(Color.gray);
        g2.fillRect(0,0,texture.getWidth(),texture.getHeight());
        g2.setColor(Color.black);
        g2.drawString(dialogHandler.getQuestion(),10,20);
        g2.drawString(dialogHandler.getHint(),10,40);

        g2.dispose();
        setTexture(texture);

        String[] split = dialogHandler.getHint().split("\n");
        hints.addAll(Arrays.asList(split));

        Engine.getInstance().addKeyListener(this);

        setLayer(Integer.MAX_VALUE);
    }

    public void trigger(String trigger){
        dialogHandler.trigger(trigger);

        BufferedImage texture = new BufferedImage(400,100,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = texture.createGraphics();
        g2.setColor(Color.gray);
        g2.fillRect(0,0,texture.getWidth(),texture.getHeight());
        g2.setColor(Color.black);
        g2.drawString(dialogHandler.getQuestion(),10,20);
        g2.drawString(dialogHandler.getHint(),10,40);

        g2.dispose();
        setTexture(texture);

        String[] split = dialogHandler.getHint().split("\n");
        hints = new ArrayList<>();
        hints.addAll(Arrays.asList(split));
    }

    public void reset(){
        dialogHandler.resetDialog();
        BufferedImage texture = getTexture();
        Graphics2D g2 = texture.createGraphics();
        g2.setColor(Color.gray);
        g2.fillRect(0,0,texture.getWidth(),texture.getHeight());
        g2.setColor(Color.black);
        g2.drawString(dialogHandler.getQuestion(),10,20);
        g2.drawString(dialogHandler.getHint(),10,40);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(isActive()){
            try{
                int index = Integer.parseInt(String.valueOf(e.getKeyChar()));
                this.trigger(hints.get(index-1));
            }catch (Exception ee){}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
