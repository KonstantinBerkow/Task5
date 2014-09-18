package com.company;

import java.io.*;

/**
 * Class to test new methods of FileSystemEntry.
 */
public class Main {

    /**
     * Input path from which you want to create a FileSystemEntry object, then destination where to save it.
     *
     * @param args First is path to file from which to create new instance of FileSystemEntry. Second - path for outputed file.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
	// write your code here
        String pathFrom = args[0];
        String outputPath = args[1];
        FileSystemEntry fse = FileSystemEntry.newFileSystemEntry(new File(pathFrom));
        System.out.println("Now creating FSE object from given path:");
        System.out.println("Created FSE is:");
        FileSystemEntry.printFSE(fse, 0, "#");
        System.out.println("Now serializing it to file: " + outputPath);
        FileSystemEntry.saveToFile(fse, outputPath);
        System.out.println("Now extracting it from file: " + outputPath);
        System.out.println("Extracted FSE is:");
        FileSystemEntry.printFSE(FileSystemEntry.getFromFile(outputPath), 0, "#");
    }
}
