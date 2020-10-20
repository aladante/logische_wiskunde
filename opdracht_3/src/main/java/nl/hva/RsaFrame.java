package nl.hva;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.interfaces.RSAKey;

public class RsaFrame {

    private static Rsa rsa;

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

    private static JPanel rightPanel() {
        JPanel panelRight = genericPanel();

        panelRight.add(new JLabel("Decryption"));
        panelRight.add(Box.createRigidArea(spacing));
        JPanel panelInputE = inputPanel("E = ");
        panelRight.add(panelInputE);
        panelRight.add(Box.createRigidArea(spacing));
        panelRight.add(Box.createRigidArea(spacing));

        panelRight.add(new JLabel("d is <value>"));
        panelRight.add(Box.createRigidArea(spacing));
        JPanel panelInputC = inputPanel("C = ");
        panelRight.add(panelInputC);
        panelRight.add(Box.createRigidArea(spacing));

        panelRight.add(new JButton(new AbstractAction("Step 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = rsa.decrypt(getTextFromInputPanel(panelInputE));
                setTextFromInputPanel(panelInputE, message );
                // Perform Step 1
            }
        }));

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
                System.out.println(getTextFromInputPanel(panelInputN)); // String user input
                rsa = new Rsa(Integer.valueOf(getTextFromInputPanel(panelInputN)));
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
                panelM.setText("M encrypted = " + rsa.encrypt(message));
            }
        }));
        panelLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLeft.add(panelM);
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

    private static void setTextFromInputPanel(JPanel inputPanel, String message) {
        JTextField textField = (JTextField) inputPanel.getComponents()[1];
        textField.setText(message);
    }

    private static String getTextFromInputPanel(JPanel inputPanel) {
        JTextField textField = (JTextField) inputPanel.getComponents()[1];
        return textField.getText();
    }
}

