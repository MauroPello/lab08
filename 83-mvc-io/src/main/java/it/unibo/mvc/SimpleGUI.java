package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Simple GUI");

    private SimpleGUI(final Controller controller) {
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        final JTextField textField = new JTextField();
        mainPanel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        mainPanel.add(textArea, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        final JButton print = new JButton("Print");
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                controller.setNextString(textField.getText());
                controller.printCurrentString();
            }
        });
        buttonPanel.add(print, BorderLayout.WEST);
        final JButton showHistory = new JButton("Show history");
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                final StringBuilder stringBuilder = new StringBuilder();
                for(final String elem : controller.getHistory()) {
                    stringBuilder.append(elem + "\n");
                }
                textArea.setText(stringBuilder.toString());
            }
        });
        buttonPanel.add(showHistory, BorderLayout.EAST);
        
        frame.setContentPane(mainPanel);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 3, sh / 3);
        frame.setLocationByPlatform(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * @param a
     *            unused
     */
    public static void main(final String... a) {
        new SimpleGUI(new SimpleController());
    }
}
