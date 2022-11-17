package it.unibo.mvc;

import java.util.List;

/**
 * 
 */
public interface Controller {
    /**
     * @param nextString next string to be printed
     */
    void setNextString(String nextString);

    /**
     * 
     */
    void printCurrentString();

    /**
     * @return history of all printed strings
     */
    List<String> getHistory();
}
