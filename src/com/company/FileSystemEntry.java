package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class which defines files and directories common functionality.
 */
public abstract class FileSystemEntry implements Serializable {

    /**
     * String which represents name of FileSystemEntry object.
     */
    private String name;
    /**
     * FileSystemEntry object which refers as "parent" for this object.
     */
    protected FileSystemEntry parent;

    /**
     * Constructs a FileSystemEntry object with given name and no parent(null).
     *
     * @param name The name for FileSystemEntry object.
     */
    public FileSystemEntry(String name) {
        this.name = name;
        this.parent = null;
    }

    /**
     * Constructs a FileSystemEntry object with given name and parent.
     *
     * @param name   The name for FileSystemEntry object.
     * @param parent The parent FileSystemEntry object.
     */
    public FileSystemEntry(String name, FileSystemEntry parent) {
        this.name = name;
        this.parent = parent;
        if (parent != null) {
            ((MyDirectory) parent).add(this);
        }
    }

    /**
     * Static method to create instance of MyFile or MyDirectory using java.io.File object.
     * If i is file will be returned MyFile if it is directory result will be MyDirectory with all it content.
     * It do not guarantees whether this file really exists.
     *
     * @param file instance of java.io.File.
     * @return instance of FileSystemEntry.
     */
    public static FileSystemEntry newFileSystemEntry(File file) {
        String name = file.getName();
        if (file.isFile()) {
            return new MyFile(name, file.length());
        }
        File[] subFiles = file.listFiles();
        if (subFiles == null) return new MyDirectory(name);
        ArrayList<FileSystemEntry> arr = new ArrayList<FileSystemEntry>(subFiles.length);
        for (int i = 0; i < subFiles.length; i++) {
            arr.add(i, newFileSystemEntry(subFiles[i]));
        }
        return new MyDirectory(file.getName(), null, arr);
    }

    /**
     * Static method which prints all content of given FileSystemEntry object.
     *
     * @param file FileSystemEntry object to be proceeded.
     * @param offset Initial offset allows somewhat user-friendly output
     * @param offsetCharacters String which will be used to show offset for content of directories
     */
    public static void printFSE(FileSystemEntry file, int offset, String offsetCharacters) {
        for (int i = 0; i < offset; i++)
            System.out.print(offsetCharacters);
        System.out.println(file);
        if (file.isDirectory()) {
            List<FileSystemEntry> tmp = ((MyDirectory) file).getChildren();
            for (FileSystemEntry aTmp : tmp) {
                printFSE(aTmp, offset + 1, offsetCharacters);
            }
        }
    }

    /**
     * This static method saves serialized FileSystemEntry at given destination.
     *
     * @param fse FileSystemEntry object to save.
     * @param destination Path with name of output file.
     * @throws IOException
     */
    public static void saveToFile(FileSystemEntry fse, String destination) throws IOException {
        FileOutputStream fos = new FileOutputStream(destination);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(fse);
        oos.flush();
        oos.close();
    }

    /**
     * This static method allows easily load saved FileSystemEntry objects from files.
     *
     * @param destination path to file where ONE FileSystemEntry object saved.
     * @return new instance of FileSystemEntry.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static FileSystemEntry getFromFile(String destination) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(destination);
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (FileSystemEntry) oin.readObject();
    }

    /**
     * Returns the name of the FileSystemEntry object.
     *
     * @return The name of the FileSystemEntry.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the parent FileSystemEntry.
     *
     * @return The FileSystemEntry object or null if this FileSystemEntry has no parent.
     */
    public FileSystemEntry getParent() {
        return parent;
    }

    /**
     * Renames FileSystemEntry object with given newName.
     *
     * @param newName New name for this FileSystemEntry object.
     */
    public void rename(String newName) {
        name = newName;
    }

    /**
     * Return size of the FileSystemEntry object.
     *
     * @return Long value which represents file's size or if it is a directory the common size of all files in this directory and it's subdirectories.
     */
    public abstract long getSize();

    /**
     * Determines whether FileSystemEntry object is file.
     *
     * @return true if FileSystemEntry object is file
     */
    public boolean isFile() {
        return this instanceof MyFile;
    }

    /**
     * Determines whether FileSystemEntry object is directory.
     *
     * @return true if FileSystemEntry object is directory
     */
    public boolean isDirectory() {
        return this instanceof MyDirectory;
    }

    /**
     * Returns the name string of this FileSystemEntry object.
     *
     * @return The string name of this FileSystemEntry object
     */
    @Override
    public String toString() {
        return name;
    }

}
