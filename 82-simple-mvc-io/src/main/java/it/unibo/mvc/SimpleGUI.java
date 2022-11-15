package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Simple GUI");

    private SimpleGUI(final Controller controller) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JTextArea text = new JTextArea();
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.save(text.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(text, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel);

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
        new SimpleGUI(new Controller());
    }
}
