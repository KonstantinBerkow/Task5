package com.company;

import java.io.*;

public class Main {

    public static void saveToFile(FileSystemEntry fse, String destination) throws IOException {
        FileOutputStream fos = new FileOutputStream(destination);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(fse);
        oos.flush();
        oos.close();
    }

    public static FileSystemEntry getFromFile(String destination) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(destination);
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (FileSystemEntry) oin.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
	// write your code here

    }
}
