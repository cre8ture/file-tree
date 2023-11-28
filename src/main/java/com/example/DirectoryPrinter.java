package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is used to print the structure of a directory.
 */
public final class DirectoryPrinter {
    /**
     * ANSI escape code for green text.
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * ANSI escape code to reset the color.
     */
    public static final String ANSI_RESET = "\u001B[0m";

    private DirectoryPrinter() {
        // Prevent instantiation
    }

    /**
     * Prints the structure of the directory at the given path.
     *
     * @param path the path of the directory
     * @throws IOException if an I/O error occurs
     */
    public static void printDirectoryStructure(Path path) throws IOException {
        Files.walk(path).forEach(filePath -> {
            int count = filePath.getNameCount() - path.getNameCount();
            String output = " ".repeat(count * 2)
                + (count > 0 ? ANSI_GREEN + "|--" + ANSI_RESET : "")
                + filePath.getFileName();
            System.out.println(output);
        });
    }

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java DirectoryPrinter <directory>");
            System.exit(1);
        }

        Path path = Paths.get(args[0]);
        try {
            printDirectoryStructure(path);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the directory structure.");
            e.printStackTrace();
        }
    }
}
