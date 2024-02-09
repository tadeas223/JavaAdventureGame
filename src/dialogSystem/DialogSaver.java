package dialogSystem;

import java.io.*;

public class DialogSaver {
    public static void save(String path, DialogHandler dialogHandler) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path));

        outputStream.writeObject(dialogHandler);

        outputStream.close();
    }

    public static DialogHandler load(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
        return (DialogHandler)inputStream.readObject();
    }
}
