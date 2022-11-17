package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private File currentFile = new File(System.getProperty("user.home")
                                        + System.getProperty("file.separator") 
                                        + "output.txt");

    /**
    * Returns the current file.
    *
    * @return the current file
    */
    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * Returns the current file path.
     *
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
        try (PrintStream ps = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            ps.println(text);
        }
    }

    /**
     * Sets a new destination file.
     *
     * @param file
     *            the file where to write
     */
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
