package gameObjects;

import gameObject.GameObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class YouWin extends GameObject {
    public YouWin() {
        try{
            setTexture(ImageIO.read(new File("data/world/youWin.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
