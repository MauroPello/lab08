package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *            the views to attach
     * @throws IOException
     */
    public DrawNumberApp(final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        int min = 0, max = 100, attempts = 10;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("config.yml")))) {
            String setting;
            while ((setting = reader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(setting, ": ");
                switch (stringTokenizer.nextToken()) {
                    case "minimum":
                        min = Integer.valueOf(stringTokenizer.nextToken());
                        break;
                    case "maximum":
                        max = Integer.valueOf(stringTokenizer.nextToken());
                        break;
                    case "attempts":
                        attempts = Integer.valueOf(stringTokenizer.nextToken());
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            displayErrors("Couldn't open config file! Using default values...");
        }
        this.model = new DrawNumberImpl(min, max, attempts);
    }

    private void displayErrors(String msg) {
        for (final DrawNumberView view : views) {
            view.displayError(msg);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl(), new DrawNumberViewImpl(), new PrintStreamView("main.log"), new PrintStreamView(System.out));
    }
}
