package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Controller {

    private File currentFile = new File(System.getProperty("user.home") + 
                                        System.getProperty("file.separator") + 
                                        "output.txt");

    public File getCurrentFile() {
        return currentFile;
    }

    public String getCurrentFilePath() {
        return currentFile.getPath();
    }

    public void save(final String text) throws IOException {
        try (PrintStream ps = new PrintStream(currentFile)) {
            ps.println(text);
        }
    }

    public void setCurrentFile(final File file) {
        // we check that the file has a parent so that we know that the file path is valid
        final File parent = file.getParentFile();
        if (parent.exists()) {
            this.currentFile = file;
        } else {
            throw new IllegalArgumentException("File path is not valid");
        }
    }
}
