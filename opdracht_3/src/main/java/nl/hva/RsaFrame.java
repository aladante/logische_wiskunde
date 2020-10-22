package nl.hva;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigInteger;

public class RsaFrame {

    private static Rsa rsa = new Rsa();

    private static int width = 750;
    private static int height = 675;
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


    private static JPanel leftPanel() {
        JPanel panelLeft = genericPanel();
        panelLeft.add(new JLabel("Encryption"));
        panelLeft.add(Box.createRigidArea(spacing));
        JPanel panelInputN = inputPanel("N = ");
        JLabel q = new JLabel("p is  <value>");
        JLabel p = new JLabel("q is  <value>");
        JLabel time = new JLabel("time is ");
        JLabel panelE = new JLabel("e is  <value>");
        JLabel panelM = new JLabel("Message after encryption is: <c>");
        panelLeft.add(panelInputN);
        panelLeft.add(Box.createRigidArea(spacing));
        panelLeft.add(Box.createRigidArea(spacing));
        panelLeft.add(p);
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(q);
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(time);

        panelLeft.add(new JButton(new AbstractAction("Step 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 1
                rsa.calculatePandQ(getTextFromInputPanel(panelInputN));
                p.setText("p = " + rsa.getP());
                q.setText("q = " + rsa.getQ());
                time.setText("Tijd nodig = " + rsa.getCalculateTime());
            }
        }));

        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(new JButton(new AbstractAction("Step 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 2
                rsa.generateE();
                System.out.println(rsa.getE() + "   is e");
                panelE.setText("e is  " + rsa.getE());
            }
        }));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(panelE);
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel panelInputM = inputPanel("M = ");
        panelLeft.add(panelInputM);
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(new JButton(new AbstractAction("Step 3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 3
                String message = getTextFromInputPanel(panelInputM);
                BigInteger encryptedMessage = rsa.encryptMessage(message);
                panelM.setText("M encrypted = " + encryptedMessage);
            }
        }));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(panelM);
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));

        return panelLeft;
    }

    private static JPanel rightPanel() {
        JPanel panelRight = genericPanel();
        JPanel panelInputE = inputPanel("E = ");
        JPanel panelInputN = inputPanel("N = ");
        JPanel panelInputC = inputPanel("C = ");
        JLabel valueD = new JLabel("d is <value>");
        JLabel valueM = new JLabel("Message after decryption is: <m>");

        panelRight.add(new JLabel("Decryption"));
        panelRight.add(Box.createRigidArea(spacing));

        panelRight.add(panelInputE);
        panelRight.add(panelInputN);
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(Box.createRigidArea(spacing));

        panelRight.add(valueD);
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(panelInputC);
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(valueM);
        // this step takes input N and E to find D If
        // if p and q are to big this will take a long time
        panelRight.add(new JButton(new AbstractAction("Step 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ee = getTextFromInputPanel(panelInputE);
                String n = getTextFromInputPanel(panelInputN);
                rsa.decodingPart1(ee, n);
                valueD.setText("d = " + rsa.getD());
                // Perform Step 1
            }
        }));

        panelRight.add(new JButton(new AbstractAction("Step 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mess = getTextFromInputPanel(panelInputC);
                String message = rsa.decodeCipher(mess);
                valueM.setText("M =" + message);
                // Perform Step 2
            }
        }));

        panelRight.add(Box.createRigidArea(spacing));

        return panelRight;
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
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        panelInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.X_AXIS));
        panelInput.add(new JLabel(labelText));
        panelInput.add(textArea);

        return panelInput;
    }

    private static void setTextFromInputPanel(JPanel inputPanel, String message) {
        JTextArea textField = (JTextArea) inputPanel.getComponents()[1];
        textField.setText(message);
    }

    private static String getTextFromInputPanel(JPanel inputPanel) {
        JTextArea textField = (JTextArea) inputPanel.getComponents()[1];
        return textField.getText();
    }
}

