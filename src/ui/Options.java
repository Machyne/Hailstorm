package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import controller.Main;

public class Options extends javax.swing.JDialog implements ActionListener {

	private static final float S = 0.50f;
	private static final float B = 1.00f;
	private final String[] colorTypes;
	public static final Color[] COLORS = new Color[] {
		Color.getHSBColor(0.00f, S+0.1f, B),
		Color.getHSBColor(0.04f, S, B),
		Color.getHSBColor(0.11f, S, B),
		Color.getHSBColor(0.30f, S, B),
		Color.getHSBColor(0.60f, S, B),
		Color.getHSBColor(0.70f, S, B),
	};

	private static final int CBOX = 120;

	private static final String[] LANGUAGES = new String[] { "English", "French", "German", "Italian", "Spanish" };
	public final String[] playerTypes;
	private static final long serialVersionUID = 1L;
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox<String> player1Box;
	private JLabel labelColor2;
	private JLabel labelColor1;
	private JSeparator jSeparator;
	private JLabel labelLanguage;
	private JLabel labelPlayer2;
	private JLabel labelPlayer1;
	private JComboBox<String> player2Color;
	private JComboBox<String> player1Color;
	private JComboBox<String> languageBox;
	private JComboBox<String> player2Box;
	private final Main main;
	private JSlider slider;


	public Options(JFrame frame,Main main) {
		super(frame);
		colorTypes = new String[] { main.language.Red, main.language.Orange, main.language.Yellow, main.language.Green, main.language.Blue, main.language.Violet };
		playerTypes = new String[] { main.language.Human, main.language.Easy+" "+main.language.Computer, main.language.Hard+" "+main.language.Computer};
		this.main = main;
		initGUI();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			this.setVisible(false);
			this.setResizable(false);
			{
				cancelButton = new JButton();
				cancelButton.addActionListener(this);
				cancelButton.setText(main.language.Cancel);
				cancelButton.setBounds(407, 158, 100, 23);
				okButton = new JButton();
				okButton.addActionListener(this);
				okButton.setText(main.language.Finish);
				okButton.setActionCommand("Finish");
				okButton.setBounds(304, 158, 100, 23);
			}
			final JLabel speedLabel = new JLabel();
			{
				speedLabel.setText(main.language.Computer+" "+main.language.Delay+":");
				speedLabel.setFont(new java.awt.Font("Tahoma",0,16));
				speedLabel.setBounds(25, 135, 200, 20);
			}
			{
				slider = new JSlider();
				slider.setMinimum(0);
				slider.setMaximum(2000);
				slider.setValue(main.sliderPosition);
				slider.setBounds(20, 160, 245, 20);
			}
			{
				getContentPane().add(okButton);
				getContentPane().add(cancelButton);
				getContentPane().add(getPlayer1Color());
				getContentPane().add(getPlayer2Color());
				getContentPane().add(getLanguageBox());
				getContentPane().add(getPlayer2box());
				getContentPane().add(getPlayer1Box());
				getContentPane().add(getLabelPlayer1());
				getContentPane().add(getLabelPlayer2());
				getContentPane().add(getLabelLanguage());
				getContentPane().add(getJSeparator());
				getContentPane().add(getLabelColor1());
				getContentPane().add(getLabelColor2());
				getContentPane().add(speedLabel);
				getContentPane().add(slider);
			}
			this.setIconImage(new ImageIcon("src/images/alphabeta.png").getImage());
			this.setTitle(main.language.Preferences);
			this.setSize(520, 220);
			this.setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JComboBox<String> getPlayer1Box() {
		if(player1Box == null) {
			ComboBoxModel<String> player1BoxModel = 
					new DefaultComboBoxModel<String>(
							playerTypes);
			player1Box = new JComboBox<String>();
			player1Box.setModel(player1BoxModel);
			player1Box.setSelectedIndex(main.p1Type);
			player1Box.setBounds(13, 34, CBOX, 20);
			player1Box.setBorder(new LineBorder(new java.awt.Color(171,173,179), 0, true));
			player1Box.setFocusable(false);
		}
		return player1Box;
	}

	private JComboBox<String> getPlayer2box() {
		if(player2Box == null) {
			ComboBoxModel<String> player2boxModel = 
					new DefaultComboBoxModel<String>(
							playerTypes);
			player2Box = new JComboBox<String>();
			player2Box.setModel(player2boxModel);
			player2Box.setSelectedIndex(main.p2Type);
			player2Box.setBounds(162, 34, CBOX, 20);
			player2Box.setBorder(new LineBorder(new java.awt.Color(171,173,179), 0, true));
			player2Box.setFocusable(false);
		}
		return player2Box;
	}

	private JComboBox<String> getLanguageBox() {
		if(languageBox == null) {
			ComboBoxModel<String> languageBoxModel = 
					new DefaultComboBoxModel<String>(
							LANGUAGES);
			languageBox = new JComboBox<String>();
			languageBox.setModel(languageBoxModel);
			languageBox.setSelectedItem(main.langType);
			languageBox.setBounds(326, 35, CBOX, 20);
			languageBox.setSize(100, 20);
			languageBox.setBorder(new LineBorder(new java.awt.Color(171,173,179), 0, true));
			languageBox.setFocusable(false);
		}
		return languageBox;
	}

	private JComboBox<String> getPlayer1Color() {
		if(player1Color == null) {
			ComboBoxModel<String> player1colorModel = 
					new DefaultComboBoxModel<String>(
							colorTypes);
			player1Color = new JComboBox<String>();
			player1Color.setModel(player1colorModel);
			for(int x=0; x<COLORS.length; x++){
				if(COLORS[x]==main.p1Color){
					player1Color.setSelectedIndex(x);
				}
			}
			player1Color.setBounds(14, 97, CBOX, 20);
			player1Color.setBorder(new LineBorder(new java.awt.Color(171,173,179), 0, true));
			player1Color.setFocusable(false);
		}
		return player1Color;
	}

	private JComboBox<String> getPlayer2Color() {
		if(player2Color == null) {
			ComboBoxModel<String> jComboBox1Model = 
					new DefaultComboBoxModel<String>(
							colorTypes);
			player2Color = new JComboBox<String>();
			player2Color.setModel(jComboBox1Model);
			for(int x=0; x<COLORS.length; x++){
				if(COLORS[x]==main.p2Color){
					player2Color.setSelectedIndex(x);
				}
			}
			player2Color.setBounds(161, 97, CBOX, 20);
			player2Color.setBorder(new LineBorder(new java.awt.Color(171,173,179), 0, true));
			player2Color.setFocusable(false);
		}
		return player2Color;
	}

	private JLabel getLabelPlayer1() {
		if(labelPlayer1 == null) {
			labelPlayer1 = new JLabel();
			labelPlayer1.setText(main.language.Player+" "+main.language.One+":");
			labelPlayer1.setFont(new java.awt.Font("Tahoma",0,14));
			labelPlayer1.setBounds(13, 11, 100, 17);
		}
		return labelPlayer1;
	}

	private JLabel getLabelPlayer2() {
		if(labelPlayer2 == null) {
			labelPlayer2 = new JLabel();
			labelPlayer2.setText(main.language.Player+" "+main.language.Two+":");
			labelPlayer2.setFont(new java.awt.Font("Tahoma",0,14));
			labelPlayer2.setBounds(162, 11, 100, 17);
		}
		return labelPlayer2;
	}

	private JLabel getLabelLanguage() {
		if(labelLanguage == null) {
			labelLanguage = new JLabel();
			labelLanguage.setText(main.language.Language+":");
			labelLanguage.setFont(new java.awt.Font("Tahoma",0,14));
			labelLanguage.setBounds(326, 11, 80, 17);
		}
		return labelLanguage;
	}

	private JSeparator getJSeparator() {
		if(jSeparator == null) {
			jSeparator = new JSeparator();
			jSeparator.setOrientation(SwingConstants.VERTICAL);
			jSeparator.setOpaque(false);
			jSeparator.setBounds(295, 11, 7, 170);
		}
		return jSeparator;
	}

	private JLabel getLabelColor1() {
		if(labelColor1 == null) {
			labelColor1 = new JLabel();
			labelColor1.setText(main.language.Color+" "+main.language.One+":");
			labelColor1.setBounds(14, 77, 80, 14);
			labelColor1.setFont(new java.awt.Font("Tahoma",0,14));
		}
		return labelColor1;
	}

	private JLabel getLabelColor2() {
		if(labelColor2 == null) {
			labelColor2 = new JLabel();
			labelColor2.setText(main.language.Color+" "+main.language.Two+":");
			labelColor2.setBounds(161, 77, 90, 14);
			labelColor2.setFont(new java.awt.Font("Tahoma",0,14));
		}
		return labelColor2;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if("Finish".equals(command)){
			this.main.p1Type = player1Box.getSelectedIndex();
			this.main.p2Type = player2Box.getSelectedIndex();
			this.main.sliderPosition = slider.getValue();
			this.main.p1Color = COLORS[player1Color.getSelectedIndex()];
			this.main.p2Color = COLORS[player2Color.getSelectedIndex()];
			this.main.langType = languageBox.getSelectedItem().toString();
			this.dispose();
			this.main.setup();
		}else{
			this.dispose();
		}
	}

}
