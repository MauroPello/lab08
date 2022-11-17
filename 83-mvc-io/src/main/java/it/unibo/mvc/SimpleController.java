package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public final class SimpleController implements Controller {

    private String nextString = "";
    private final List<String> history;

    /**
     * 
     */
    public SimpleController() {
        history = new ArrayList<>(0);
    }

    @Override
    public void setNextString(final String nextString) {
        if (nextString == null) {
            throw new IllegalArgumentException("The passed argument is null");
        }

        this.nextString = nextString;
    }

    /**
     * @return next string to be printed
     */
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(this.history);
    }

    @Override
    public void printCurrentString() {
        if (nextString == null) {
            throw new IllegalStateException("Next string is null");
        }

        System.out.println(nextString); // NOPMD: required
        history.add(nextString);
        nextString = "";
    }
}
