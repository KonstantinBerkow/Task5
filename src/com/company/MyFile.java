package com.company;

import java.io.Serializable;

/**
 * Class which defines files functionality.
 */
class MyFile extends FileSystemEntry implements Serializable {

    /**
     * Long value which represents size of this MyFile object.
     */
    private long size;

    /**
     * Constructs a MyFile object with given name and no parent(null), sets size to 0.
     *
     * @param name The name for this MyFile object.
     */
    public MyFile(String name) {
        super(name);
        this.size = 0;
    }

    /**
     * Constructs a MyFile object with given name and no parent(null), sets size.
     *
     * @param name The name for this MyFile object.
     * @param size The size for this MyFile object.
     */
    public MyFile(String name, long size) {
        super(name);
        this.size = size;
    }

    /**
     * Constructs a MyFile object with given name and parent, sets size.
     *
     * @param name   The name for this MyFile object.
     * @param size   The size for this MyFile object.
     * @param parent The parent FileSystemEntry object for this MyFile object.
     */
    public MyFile(String name, long size, FileSystemEntry parent) {
        super(name, parent);
        this.size = size;
    }

    /**
     * Return MyFile object's size.
     *
     * @return Long value which represents file's size.
     */
    @Override
    public long getSize() {
        return size;
    }

}
