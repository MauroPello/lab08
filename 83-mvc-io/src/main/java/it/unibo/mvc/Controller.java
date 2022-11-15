package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    void setNextString(String nextString);

    void printCurrentString();

    List<String> getHistory();
}
