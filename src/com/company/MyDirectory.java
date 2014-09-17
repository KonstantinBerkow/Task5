package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which defines directories functionality.
 */
class MyDirectory extends FileSystemEntry implements Serializable {

    /**
     * List of FileSystemEntry objects which represents children objects for this MyDirectory object.
     */
    private List<FileSystemEntry> children;

    /**
     * Constructs a MyDirectory object with given name and no parent(null), sets children to null.
     *
     * @param name The name for this MyDirectory object.
     */
    public MyDirectory(String name) {
        super(name);
        this.children = null;
    }

    /**
     * Constructs a MyDirectory object with given name and parent, sets children to null.
     *
     * @param name   The name for this MyDirectory object.
     * @param parent The parent FileSystemEntry object for this MyDirectory object.
     */
    public MyDirectory(String name, FileSystemEntry parent) {
        super(name, parent);
        this.children = null;
    }

    /**
     * Constructs a MyDirectory object with given name, parent and children.
     *
     * @param name     The name for this MyDirectory object.
     * @param parent   The parent FileSystemEntry object for this MyDirectory object.
     * @param children The list of FileSystemEntry objects which lies in this myDirectory object.
     */
    public MyDirectory(String name, FileSystemEntry parent, List<FileSystemEntry> children) {
        super(name, parent);
        this.children = children;
    }

    public MyDirectory(String name, FileSystemEntry parent, FileSystemEntry[] children) {
        super(name, parent);
        this.children = new ArrayList<FileSystemEntry>(children.length);
        for (int i = 0; i < children.length; i++) {
            this.children.add(i, children[i]);
        }
    }

    /**
     * Returns size of MyDirectory object.
     *
     * @return Long value which represents the common size of all files in this directory and it's subdirectories.
     */
    @Override
    public long getSize() {
        if (children == null) return 0;
        long realSize = 0;
        for (FileSystemEntry fse : children) {
            realSize += fse.getSize();
        }
        return realSize;
    }

    /**
     * Return the list of FileSystemEntry objects which lies int this MyDirectory object.
     *
     * @return List of FileSystemEntry objects or null if this MyDirectory object has no children.
     */
    public List<FileSystemEntry> getChildren() {
        return children;
    }

    /**
     * Modifies this MyDirectory object by adding a new FileSystemEntry to it's children.
     * If children wasn't previously initialized it will be initialized with empty ArrayList of FileSystemEntry.
     *
     * @param newEntry New FileSystemEntry object.
     */
    public void add(FileSystemEntry newEntry) {
        newEntry.parent = this;
        if (children == null) children = new ArrayList<FileSystemEntry>();
        children.add(newEntry);
    }

}
