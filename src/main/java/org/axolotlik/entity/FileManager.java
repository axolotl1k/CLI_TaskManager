package org.axolotlik.entity;

import java.io.*;
import java.util.List;

public class FileManager {
    public static List<Task> load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("tasks.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Task> tasks = (List<Task>) ois.readObject();
        ois.close();
        fis.close();
        return tasks;
    }

    public static void save(List<Task> tasks) throws IOException{
        FileOutputStream fos = new FileOutputStream("tasks.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tasks);
        oos.close();
        fos.close();
    }
}
