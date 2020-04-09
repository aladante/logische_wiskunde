import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;


public class RsaFrame extends JFrame {

	private final int WIDTH = 800;
	private final int HEIGHT = 500;

	private JTextArea rSpace;
	private JTextArea iSpace;
	private JButton decrypt, dstep1 ,dstep2 ;
	private JButton encrypt, step1, step2, step3;

	private Rsa tool;

	public RsaFrame() {
		super("encypte decrypte");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);

		setLayout(new GridLayout(3, 1));

		String bitLength = JOptionPane.showInputDialog(this, "Please provide a bit length");

		Font resultFont = new Font("Arial", Font.PLAIN, 12);

		if (bitLength == null) {
			System.exit(0);
		} else {
			try {
				tool = new Rsa(Integer.parseInt(bitLength));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Please provide a legal integer!");
			}
		}


		JTextArea pKey = new JTextArea(tool.getPublicKey().toString(), 8,70);
		pKey.setLineWrap(true);
		pKey.setWrapStyleWord(true);
		pKey.setEditable(false);

		rSpace = new JTextArea(8, 20);
		rSpace.setLineWrap(true);
		rSpace.setWrapStyleWord(true);

		iSpace = new JTextArea(8, 20);
		iSpace.setLineWrap(true);
		iSpace.setWrapStyleWord(true);
		

		rSpace.setFont(resultFont);
		iSpace.setFont(resultFont);

		rSpace.setMargin(new Insets(5, 5, 5, 5));
		iSpace.setMargin(new Insets(5, 5, 5, 5));


		decrypt = new JButton("Decrypt");
		dstep1 = new JButton("step1");
		dstep2 = new JButton("step2");
		encrypt = new JButton("Encrypt");
		step1 = new JButton("step1");
		step2 = new JButton("step2");
		step3 = new JButton("step3");

		decrypt.addActionListener(new ButtonListener());
		step1.addActionListener(new ButtonListener());
		step2.addActionListener(new ButtonListener());
		step3.addActionListener(new ButtonListener());
		encrypt.addActionListener(new ButtonListener());
		dstep1.addActionListener(new ButtonListener());
		dstep2.addActionListener(new ButtonListener());

		JPanel buttonPane = new JPanel();
		buttonPane.add(encrypt);
		buttonPane.add(step1);
		buttonPane.add(step2);
		buttonPane.add(step3);
		buttonPane.add(decrypt);
		buttonPane.add(dstep1);
		buttonPane.add(dstep2);
		buttonPane.add(pKey);
		add(iSpace);
		add(buttonPane);
		add(rSpace);
		pack();
	}


	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String result = "";

			if(e.getSource() == encrypt){
				result = tool.encrypt(iSpace.getText().trim());
			}
			else if(e.getSource() == step1) {
				result = tool.getCalculateTime();
			}
			else if(e.getSource() == step2) {
				result = tool.getMod();
			}
			else if(e.getSource() == step3) {
				result = tool.encrypt(iSpace.getText().trim());
			}
			else if(e.getSource() == dstep1) {}
			else if(e.getSource() == dstep2) {}
			else if(e.getSource() == decrypt) {
				result = tool.decrypt(iSpace.getText().trim());
			}

			rSpace.setText(result);
		}
	}
}


