import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RsaFrame extends JFrame {

	private final int WIDTH = 400;
	private final int HEIGHT = 300;

	private JTextArea resultArea;
	private JTextArea input;
	private JButton decrypt, dstep1 ,dstep2 ;
	private JButton encrypt, step1, step2, step3;

	private Rsa tool;

	public RsaFrame() {
		super("Johans RSA");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);

		setLayout(new GridLayout(3, 1));

		String bitLength = JOptionPane.showInputDialog(this, "Please provide a bit length");

		if (bitLength == null) {
			System.exit(0);
		} else {
			try {
				tool = new Rsa(Integer.parseInt(bitLength));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Please provide a legal integer!");
			}
		}


		JLabel pubKey = new JLabel(tool.getPublicKey().toString());

		Font resultFont = new Font("Monaco", Font.PLAIN, 13);

		resultArea = new JTextArea(8, 20);
		input = new JTextArea(8, 20);
		resultArea.setFont(resultFont);
		input.setFont(resultFont);
		resultArea.setMargin(new Insets(5, 5, 5, 5));
		input.setMargin(new Insets(5, 5, 5, 5));


		decrypt = new JButton("Decrypt");
		dstep1 = new JButton("step1");
		dstep2 = new JButton("step2");
		encrypt = new JButton("Encrypt");
		step1 = new JButton("step1");
		step2 = new JButton("step2");
		step3 = new JButton("step3");

		decrypt.addActionListener(new ButtonListener());
		encrypt.addActionListener(new ButtonListener());


		JPanel buttonPane = new JPanel();
		buttonPane.add(encrypt);
		buttonPane.add(step1);
		buttonPane.add(step2);
		buttonPane.add(step3);
		buttonPane.add(decrypt);
		buttonPane.add(dstep1);
		buttonPane.add(dstep2);
		buttonPane.add(pubKey);
		add(input);
		add(buttonPane);
		add(resultArea);
		pack();
	}


	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String result;

			if(e.getSource() == encrypt){
				result = tool.encrypt(input.getText().trim());
			}
			else if(e.getSource() == step1) {}
			else if(e.getSource() == step1) {}
			else if(e.getSource() == step1) {}
			else if(e.getSource() == step1) {}
			else{
				result = tool.decrypt(input.getText().trim());
			}

			resultArea.setText(result);

		}

	}
}


