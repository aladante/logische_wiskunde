package nl.hva;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RsaFrame {

  private static final int WIDTH = 100;
  private static final int HEIGHT = 100;

  public RsaFrame() {
    JFrame frameMain = new JFrame("RSA Calculator");
    frameMain.setMinimumSize(new Dimension(WIDTH, HEIGHT));

    Container containerMain = frameMain.getContentPane();
    containerMain.setLayout(new BorderLayout());

    JPanel panelTop = new JPanel();
    panelTop.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

    JPanel panelInputE = inputPanel("E = ");
    panelRight.add(panelInputE);
    panelRight.add(new JButton(new AbstractAction("Step 1") {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Perform Step 1
      }
    }));
    panelRight.add(new JLabel("d is <value>"));
    JPanel panelInputC = inputPanel("C = ");
    panelRight.add(panelInputC);
    panelRight.add(new JButton(new AbstractAction("Step 2") {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Perform Step 2
      }
    }));
    panelRight.add(new JLabel("Message after decryption is: <m>"));

    return panelRight;
  }

  private static JPanel leftPanel() {
    JPanel panelLeft = genericPanel();

    panelLeft.add(new JLabel("Encryption"));
    JPanel panelInputN = inputPanel("N = ");
    panelLeft.add(panelInputN);
    panelLeft.add(new JButton(new AbstractAction("Step 1") {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Perform Step 1
        System.out.println(getTextFromInputPanel(panelInputN)); // String user input
      }
    }));
    panelLeft.add(new JLabel("p is <value>"));
    panelLeft.add(new JLabel("q is <value>"));
    panelLeft.add(new JLabel("time: "));
    panelLeft.add(new JButton(new AbstractAction("Step 2") {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Perform Step 2
      }
    }));
    panelLeft.add(new JLabel("e is <value>"));
    JPanel panelInputM = inputPanel("M = ");
    panelLeft.add(panelInputM);
    panelLeft.add(new JButton(new AbstractAction("Step 3") {
      @Override 
      public void actionPerformed(ActionEvent e) {
        // Perform Step 3
      }
    }));
    panelLeft.add(new JLabel("Message after encryption is: <c>"));

    return panelLeft;
  }

  private static JPanel genericPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
