package nl.hva;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RsaFrame {

    private static int width = 750;
    private static int height = 475;
    private static Border border = BorderFactory.createCompoundBorder(
            new EtchedBorder(),
            new EmptyBorder(10, 10, 10, 10)
    );
    private static Dimension spacing = new Dimension(0, 10);

    public RsaFrame() {

        JFrame frameMain = new JFrame("RSA Calculator");
        frameMain.setPreferredSize(new Dimension(width, height));
        frameMain.setResizable(false);

        Container containerMain = frameMain.getContentPane();
        containerMain.setLayout(new BorderLayout());

        JPanel panelTop = new JPanel();
        panelTop.setBorder(border);
        panelTop.add(new JLabel("RSA Calculator"));

        containerMain.add(panelTop, BorderLayout.NORTH);
        containerMain.add(leftPanel(), BorderLayout.WEST);
        containerMain.add(rightPanel(), BorderLayout.EAST);

        frameMain.pack();
        frameMain.setVisible(true);
    }

    private static JPanel rightPanel() {
        JPanel panelRight = genericPanel();

        panelRight.add(new JLabel("Decryption"));
        panelRight.add(Box.createRigidArea(spacing));
        JPanel panelInputE = inputPanel("E = ");
        panelRight.add(panelInputE);
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(new JButton(new AbstractAction("Step 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 1
            }
        }));
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(new JLabel("d is <value>"));
        panelRight.add(Box.createRigidArea(spacing));
        JPanel panelInputC = inputPanel("C = ");
        panelRight.add(panelInputC);
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(new JButton(new AbstractAction("Step 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 2
            }
        }));
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(new JLabel("Message after decryption is: <m>"));
        panelRight.add(Box.createRigidArea(spacing));

        return panelRight;
    }

    private static JPanel leftPanel() {
        JPanel panelLeft = genericPanel();

        panelLeft.add(new JLabel("Encryption"));
        panelLeft.add(Box.createRigidArea(spacing));
        JPanel panelInputN = inputPanel("N = ");
        panelLeft.add(panelInputN);
        panelLeft.add(Box.createRigidArea(spacing));
        panelLeft.add(new JButton(new AbstractAction("Step 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 1
                System.out.println(getTextFromInputPanel(panelInputN)); // String user input
            }
        }));
        panelLeft.add(Box.createRigidArea(spacing));
        panelLeft.add(new JLabel("p is <value>"));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(new JLabel("q is <value>"));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(new JLabel("time: "));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(new JButton(new AbstractAction("Step 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 2
            }
        }));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(new JLabel("e is <value>"));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel panelInputM = inputPanel("M = ");
        panelLeft.add(panelInputM);
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(new JButton(new AbstractAction("Step 3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 3
            }
        }));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(new JLabel("Message after encryption is: <c>"));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));

        return panelLeft;
    }

    private static JPanel genericPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(border);
        panel.setPreferredSize(new Dimension(width / 2, height));

        return panel;
    }

    private static JPanel inputPanel(String labelText) {
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.X_AXIS));
        panelInput.add(new JLabel(labelText));
        panelInput.add(new JTextField());

        return panelInput;
    }

    private static String getTextFromInputPanel(JPanel inputPanel) {
        JTextField textField = (JTextField) inputPanel.getComponents()[1];
        return textField.getText();
    }

}
