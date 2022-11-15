package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 */
public class Controller {

    private File currentFile = new File(System.getProperty("user.home") + 
                                        System.getProperty("file.separator") + 
                                        "output.txt");

    /**
     * @return the current file
     */
    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * @return the current file path
     */
    public String getCurrentFilePath() {
        return currentFile.getPath();
    }

    /**
     * Saves some text on the designed file.
     * 
     * @param text
     *            the text to save
     * @throws IOException
     *             if the writing fails
     */
    public void save(final String text) throws IOException {
        try (PrintStream ps = new PrintStream(currentFile)) {
            ps.println(text);
        }
    }

    /**
     * @param file
     *            the file where to write
     */
    public void setCurrentFile(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            currentFile = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    /**
     * @param file
     *            the file where to write
     */
    public void setCurrentFile(final String file) {
        setCurrentFile(new File(file));
    }

}
