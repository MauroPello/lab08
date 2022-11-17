package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("Simple GUI");

    private SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.save(textArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        final JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());

        final JTextField textField = new JTextField(controller.getCurrentFilePath());
        final JButton browse = new JButton("Browse...");
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                final JFileChooser chooser = new JFileChooser();
                final int returnVal = chooser.showOpenDialog(browse);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(chooser.getSelectedFile());
                    textField.setText(controller.getCurrentFilePath());
                } else if (returnVal != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Error");
                }
            }
        });

        secondPanel.add(textField, BorderLayout.CENTER);
        secondPanel.add(browse, BorderLayout.LINE_END);

        firstPanel.add(secondPanel, BorderLayout.NORTH);
        firstPanel.add(textArea, BorderLayout.CENTER);
        firstPanel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(firstPanel);

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
        new SimpleGUIWithFileChooser(new Controller());
    }
}
