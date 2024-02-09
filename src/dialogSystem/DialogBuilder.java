package dialogSystem;

import gameObjects.DanceNpc;
import gameObjects.Dialog;

import java.io.*;

public class DialogBuilder {
    public static void main(String[] args) throws IOException {
        DialogNode crazyNeco = new DialogNode("Bura Nya, I'm Crazy Neco and i know everything!","(1) Why are we here?\n(2) How can i exit?");
        DialogNode answer1_1 = new DialogNode("HUMANS!!! trapped us here.","(1) next");
        DialogNode answer1_2 = new DialogNode("We were peacefully living outside.","(1) next");
        DialogNode answer1_3 = new DialogNode("And then human came and trapped us here.","(1) Ok");

        DialogNode answer2_1 = new DialogNode("You need to find a key that opens the doors in the west.","(1) next");
        DialogNode answer2_2 = new DialogNode("The thing is nobody knows where the key is.","(1) Ok");

        crazyNeco.addTrigger("(1) Why are we here?",answer1_1);
        answer1_1.addTrigger("(1) next",answer1_2);
        answer1_2.addTrigger("(1) next",answer1_3);
        answer1_3.addTrigger("(1) Ok",crazyNeco);

        crazyNeco.addTrigger("(2) How can i exit?",answer2_1);
        answer2_1.addTrigger("(1) next",answer2_2);
        answer2_2.addTrigger("(1) Ok",crazyNeco);

        DialogHandler dialogHandler = new DialogHandler(crazyNeco);
        File file = new File("data/dialogs/crazyNeco.dg");
        file.createNewFile();
        new ObjectOutputStream(new FileOutputStream(file)).writeObject(dialogHandler);
    }
}
