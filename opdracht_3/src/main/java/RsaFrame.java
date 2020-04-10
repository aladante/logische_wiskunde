import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RsaFrame extends JFrame {

	private final int WIDTH = 800;
	private final int HEIGHT = 500;

	private JTextArea rSpace;
	private JTextArea iSpace;
	private JButton dstep1, dstep2;
	private JButton step1, step2, step3;
	private JTextArea pKey;

	private Rsa theCrypterr;

	public RsaFrame() {
		super("encypte decrypte");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);

		setLayout(new GridLayout(3, 1));

		String lengthInBits = JOptionPane.showInputDialog(this, "Step 1 provide n");
		Font resultFont = new Font("Arial", Font.PLAIN, 12);

		if (lengthInBits == null) {
			System.exit(0);
		} else {
			try {
				theCrypterr = new Rsa(Integer.parseInt(lengthInBits));

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Please provide a legal integer!");
			}
		}


		pKey = new JTextArea(8, 70);
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

		dstep1 = new JButton("Decrypte step1");
		dstep2 = new JButton("Decrypte step2");
		step2 = new JButton("step2 Enqrypte");
		step3 = new JButton("step3 Enqrypte");

		step2.addActionListener(new ButtonListener());
		step3.addActionListener(new ButtonListener());
		dstep1.addActionListener(new ButtonListener());
		dstep2.addActionListener(new ButtonListener());

		JPanel buttonPane = new JPanel();
		buttonPane.add(step2);
		buttonPane.add(step3);
		buttonPane.add(dstep1);
		buttonPane.add(dstep2);
		buttonPane.add(pKey);
		add(iSpace);
		add(buttonPane);
		add(rSpace);
		theCrypterr.generatePandQ();
		rSpace.setText(theCrypterr.getCalculateTime());
		pack();
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String result = "";
			if (e.getSource() == step2) {
				pKey.setText(theCrypterr.getPublicKey().toString() + "  is public key");
			} else if (e.getSource() == step3) {
				result = theCrypterr.encrypt(iSpace.getText().trim());
			} else if (e.getSource() == dstep1) {
				result = theCrypterr.getPrivateKey();
			} else if (e.getSource() == dstep2) {
				pKey.setText(theCrypterr.decrypt(iSpace.getText().trim()));
			}
			rSpace.setText(result);
		}
	}
}
