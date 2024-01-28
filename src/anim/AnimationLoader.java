package anim;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimationLoader {
    public static Animation loadAnimation(String path) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        double wait = 0;
        boolean loop = false;
        ArrayList<String> lines = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(path + "/data.anim"));

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("cant load animation");
            throw new RuntimeException(e);
        }

        for (String s : lines) {
            if (s.contains("IMAGES")) {
                String[] split1 = s.split(" ");
                String[] split2 = split1[1].split(("-"));
                for (int i = Integer.parseInt(split2[0]); i <= Integer.parseInt(split2[1]); i++) {
                    try {
                        images.add(ImageIO.read(new File(path + "/" + i + ".png")));
                    } catch (IOException e) {
                        System.out.println("cant load animation");
                        throw new RuntimeException(e);
                    }
                }
            }
            if (s.contains("WAIT")) {
                String[] split1 = s.split(" ");
                wait = Double.parseDouble(split1[1]);
            }
            if (s.contains("LOOP")) {
                String[] split1 = s.split(" ");
                loop = Boolean.parseBoolean(split1[1]);
            }
        }

        return new Animation(images.toArray(new BufferedImage[0]), wait,loop);
    }

    public static AnimationController loadAnimationController(String path){
        AnimationController animationController = new AnimationController();
        String defAnimation = "";
        ArrayList<String> lines = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(path + "/data.animc"));

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("cant load animation");
            throw new RuntimeException(e);
        }

        ArrayList<String> animNames = new ArrayList<>();

        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).contains("ANIMATIONS")){
                int j = i+1;
                while(!lines.get(j).contains("END")){
                    animNames.add(lines.get(j));
                    j++;
                }
            }
        }

        for(String animName : animNames){
            String animPath = "";

            for(int i = 0; i < lines.size(); i++){
                if(lines.get(i).contains(animName)){
                    int j = i+1;
                    while(!lines.get(j).contains("END")){
                        if(lines.get(j).contains("PATH")){
                            String[] split1 = lines.get(j).split(" ");
                            String[] split2 = split1[1].split("\"");
                            animPath = split2[1];
                        }
                        j++;
                    }
                }
            }

            Animation anim = AnimationLoader.loadAnimation(path + "/" + animPath);

            for(int i = 0; i < lines.size(); i++){
                if(lines.get(i).contains(animName)){
                    int j = i+1;
                    while(!lines.get(j).contains("END")){
                        if(lines.get(j).contains("FLIP")){
                            String[] split1 = lines.get(j).split(" ");
                            if(split1[1].equals("X")){
                                anim.flip(true);
                            }
                            else if(split1[1].equals("Y")){
                                anim.flip(false);
                            }
                        }
                        j++;
                    }
                }
            }

            animationController.addAnimation(animName,anim);
        }

        return animationController;
    }
}
