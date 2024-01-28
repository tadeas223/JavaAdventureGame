package tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FlipImage {
    public static BufferedImage flipX(BufferedImage image){
        BufferedImage image2 = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) image2.createGraphics();

        g2.drawImage(image,image.getWidth(),0,-image.getWidth(),image.getHeight(),null);
        g2.dispose();

        return image2;
    }

    public static BufferedImage flipY(BufferedImage image){
        BufferedImage image2 = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) image2.createGraphics();

        g2.drawImage(image,0,image.getHeight(),image.getWidth(),-image.getHeight(),null);
        g2.dispose();

        return image2;
    }
}
