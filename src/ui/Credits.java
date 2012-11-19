package ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Credits extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;

	public Credits(final String name){
		super();
		this.setSize(353,230);
		final JLabel creditsLabel = new JLabel();
		creditsLabel.setIcon(new ImageIcon("src/images/CreditsImage.png"));
		this.getContentPane().add(creditsLabel);
		this.setIconImage(new ImageIcon("src/images/alphabeta.png").getImage());
		this.setTitle(name);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
