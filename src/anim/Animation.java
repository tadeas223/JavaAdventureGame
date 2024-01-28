package anim;

import tools.FlipImage;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Iterator;

public class Animation implements Iterator<BufferedImage> {
    private BufferedImage[] images;
    private double wait;
    private boolean loop;
    private int iteration = 0;

    public Animation(BufferedImage[] images, double wait,boolean loop) {
        this.images = images;
        this.wait = wait;
        this.loop = loop;
    }

    public void flip(boolean horizontally){
        for (int i = 0; i < images.length;i++){
            if(horizontally) images[i] = FlipImage.flipX(images[i]);
            else images[i] = FlipImage.flipY(images[i]);
        }
    }
    public BufferedImage[] getImages() {
        return images;
    }

    public Double getWait() {
        return wait;
    }

    public boolean isLoop() {
        return loop;
    }

    @Override
    public boolean hasNext() {
        if(iteration < images.length){
            return true;
        } else if (loop){
            restart();
            return true;
        }
        return false;
    }

    @Override
    public BufferedImage next() {
        BufferedImage image =  images[iteration];
        iteration++;
        return image;
    }

    public void restart(){
        iteration = 0;
    }

    @Override
    public String toString() {
        return "anim.Animation{" +
                "images=" + images.length +
                ", wait=" + wait +
                ", loop=" + loop +
                '}';
    }
}
