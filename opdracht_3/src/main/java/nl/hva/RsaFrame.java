package nl.hva;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RsaFrame {

    private static Rsa rsa = new Rsa();

    private static int width = 750;
    private static int height = 675;
    private static Border border = BorderFactory.createCompoundBorder(
            new EtchedBorder(),
            new EmptyBorder(10, 10, 10, 10)
    );
    private static Dimension spacing = new Dimension(0, 20);

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

        addWithSpacingBelow(panelLeft, new JLabel("Encryption"));

        JPanel panelInputN = inputPanel("N = ");

        JPanel p = inputPanel("p = ");

        JPanel q = inputPanel("q = ");

        DocumentListener dl = new DocumentListener() {
          @Override
          public void insertUpdate(DocumentEvent e) {
              updateFieldState();
          }

          @Override
          public void removeUpdate(DocumentEvent e) {
              updateFieldState();
          }

          @Override
          public void changedUpdate(DocumentEvent e) {
              updateFieldState();
          }

          protected void updateFieldState() {
              String text = "";
              try {
                 long pNum = Long.parseLong(getTextFromInputPanel(p));
                 long qNum = Long.parseLong(getTextFromInputPanel(q));
                 text = String.valueOf((pNum * qNum));
              }
              catch (NumberFormatException e) { }
              setTextFromInputPanel(panelInputN, text);
          }
        };

        JTextField pTextField = (JTextField) p.getComponents()[1];
        JTextField qTextField = (JTextField) q.getComponents()[1];
        pTextField.getDocument().addDocumentListener(dl);
        qTextField.getDocument().addDocumentListener(dl);

        addWithSpacingBelow(panelLeft, panelInputN);
        addWithSpacingBelow(panelLeft, p);
        addWithSpacingBelow(panelLeft, q);


        JTextArea time = multilineLabel("time taken finding p and q = <value>");
        addWithSpacingBelow(panelLeft, addMultilineLabelToScrollPane(time));

        addWithSpacingBelow(panelLeft, new JButton(new AbstractAction("Step 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 1
                rsa.calculatePandQ(getTextFromInputPanel(panelInputN));
                setTextFromInputPanel(p, String.valueOf(rsa.getP()));
                setTextFromInputPanel(q, String.valueOf(rsa.getQ()));
                time.setText("time taken finding p and q = " + rsa.getCalculateTime() + " milliseconds");
            }
        }));

        JTextArea textE = multilineLabel("e = <value>");

        addWithSpacingBelow(panelLeft, new JButton(new AbstractAction("Step 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 2
                rsa.generateE();
                System.out.println(rsa.getE() + "   is e");
                textE.setText("e = " + rsa.getE());
            }
        }));

        addWithSpacingBelow(panelLeft, addMultilineLabelToScrollPane(textE));

        JPanel panelInputM = inputPanel("M = ");
        addWithSpacingBelow(panelLeft, panelInputM);

        JTextArea labelM = multilineLabel("message after encryption = <c>");

        addWithSpacingBelow(panelLeft, new JButton(new AbstractAction("Step 3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Step 3
                String message = getTextFromInputPanel(panelInputM);
                String encryptedMessage = rsa.encryptMessage(message);
                System.out.println(encryptedMessage);
                labelM.setText("M encrypted = " + encryptedMessage);
            }
        }));

        addWithSpacingBelow(panelLeft, addMultilineLabelToScrollPane(labelM));

        return panelLeft;
    }

    private static JPanel rightPanel() {
        JPanel panelRight = genericPanel();

        addWithSpacingBelow(panelRight, new JLabel("Decryption"));
        
        JPanel panelInputE = inputPanel("E = ");
        addWithSpacingBelow(panelRight, panelInputE);

        JPanel panelInputN = inputPanel("N = ");
        addWithSpacingBelow(panelRight, panelInputN);

        JTextArea valueD = multilineLabel("d = <value>");
        addWithSpacingBelow(panelRight, addMultilineLabelToScrollPane(valueD));

        JPanel panelInputC = inputPanel("C = ");
        addWithSpacingBelow(panelRight, panelInputC);

        JTextArea valueM = multilineLabel("message after decryption = <m>");
        addWithSpacingBelow(panelRight, addMultilineLabelToScrollPane(valueM));

        // this step takes input N and E to find D If
        // if p and q are to big this will take a long time
        addWithSpacingBelow(panelRight, new JButton(new AbstractAction("Step 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ee = getTextFromInputPanel(panelInputE);
                String n = getTextFromInputPanel(panelInputN);
                rsa.decodingPart1(ee, n);
                valueD.setText("d = " + rsa.getD());
                // Perform Step 1
            }
        }));

        addWithSpacingBelow(panelRight, new JButton(new AbstractAction("Step 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mess = getTextFromInputPanel(panelInputC);
                String message = rsa.decodeCipher(mess);
                System.out.println(message);
                valueM.setText("M = " + message);
                // Perform Step 2
            }
        }));

        return panelRight;
    }

    private static JTextArea multilineLabel(String text) {
        JTextArea multilineLabel = new JTextArea();
        multilineLabel.setText(text);
        multilineLabel.setLineWrap(true);
        multilineLabel.setEditable(false);
        multilineLabel.setOpaque(false);
        Font boldFont = new Font(multilineLabel.getFont().getName(), Font.BOLD, multilineLabel.getFont().getSize());
        multilineLabel.setFont(boldFont);
        return multilineLabel;
    }

    private static JScrollPane addMultilineLabelToScrollPane(JTextArea multilineLabel) {
        JScrollPane scrollPane = new JScrollPane(multilineLabel);
        scrollPane.setPreferredSize(new Dimension(width / 2, 150));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        return scrollPane;
    }

    private static void addWithSpacingBelow(JPanel panel, JComponent component) {
        panel.add(component);
        panel.add(Box.createRigidArea(spacing));
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
        JTextField textField = new JTextField();
        panelInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.X_AXIS));
        panelInput.add(new JLabel(labelText));
        panelInput.add(textField);

        return panelInput;
    }

    private static void setTextFromInputPanel(JPanel inputPanel, String message) {
        JTextField textField = (JTextField) inputPanel.getComponents()[1];
        textField.setText(message);
    }

    private static String getTextFromInputPanel(JPanel inputPanel) {
        JTextField textField = (JTextField) inputPanel.getComponents()[1];
        System.out.println(textField.getText());
        return textField.getText();
    }
}

