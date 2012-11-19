package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.WindowConstants;

import controller.Main;

import lang.Language;



public class MainDisplay extends javax.swing.JFrame implements ActionListener {
	private transient ArrayList<JButton> buttons;
	private static final long serialVersionUID = 1L;
	public JLabel timerDisplay;
	private JLabel playerAHand, playerBHand;
	final private Main controller;
	final private String[] humanIcons = {"einstein","hawking","jobs","newton","turing"};
	final private String[] computerIcons = {"r2d2","hal9000"};

	public MainDisplay(final Main Controller,final Language language){
		super();
		this.controller = Controller;
		initGUI(language);
		setLocationRelativeTo(null);
		validate();
	}

	public void reset(final Language language){
		this.getContentPane().removeAll();
		initGUI(language);
	}

	private void initGUI(final Language lang) {
		try {
			final Container contentPane = getContentPane();
			contentPane.setLayout(null);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			JMenuBar menuBar;
			{
				menuBar = new JMenuBar();
				setJMenuBar(menuBar);
				{
					JMenu gameMenu;
					gameMenu = new JMenu();
					menuBar.add(gameMenu);
					gameMenu.setText(lang.Game);
					{
						JMenuItem newGameItem;
						newGameItem = new JMenuItem();
						gameMenu.add(newGameItem);
						newGameItem.setText(lang.NewGame);
						newGameItem.setActionCommand("newGame");
						newGameItem.setName("New Game");
						newGameItem.addActionListener(controller);
						newGameItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
								java.awt.Event.CTRL_MASK));


					}
					{
						JMenuItem quitItem;
						quitItem = new JMenuItem();
						gameMenu.add(quitItem);
						quitItem.setText(lang.Quit);
						quitItem.setName("Quit");
						quitItem.addActionListener(this);
						quitItem.addActionListener(controller);
						quitItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q,
								java.awt.Event.CTRL_MASK));
					}
				}
				{
					JMenu settingsMenu;
					settingsMenu = new JMenu();
					menuBar.add(settingsMenu);
					settingsMenu.setText(lang.Settings);
					{
						JMenuItem preferancesItem;
						preferancesItem = new JMenuItem();
						settingsMenu.add(preferancesItem);
						preferancesItem.setText(lang.Preferences);
						preferancesItem.setName("Preferences");
						preferancesItem.setActionCommand("preferences");
						preferancesItem.addActionListener(controller);
						preferancesItem.addActionListener(controller);
						preferancesItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P,
								java.awt.Event.CTRL_MASK));
					}
				}
				{
					JMenu aboutMenu;
					aboutMenu = new JMenu();
					menuBar.add(aboutMenu);
					aboutMenu.setText(lang.About);
					{
						JMenuItem creditsItem;
						creditsItem = new JMenuItem();
						aboutMenu.add(creditsItem);
						creditsItem.setText(lang.Credits);
						creditsItem.setName("Credits");
						creditsItem.setActionCommand("credits");
						creditsItem.addActionListener(controller);
					}
				}
			}
			buttons = new ArrayList<JButton>();
			for(Integer i = 1; i<=9;++i){
				JButton button = new JButton(i.toString());
				button.setFont(new java.awt.Font("Times",1,22));
				button.setName(i.toString());
				button.addActionListener(controller);
				button.addActionListener(this);
				button.setBounds(40+65*(i-1), 20, 50, 70);
				button.setActionCommand("card");
				buttons.add(button);
			}
			JLabel playerTwoLabel;
			JLabel playerOneLabel;
			JLabel timeLabel;
			JSeparator vSeparator;
			JSeparator hSeparator;

			{
				playerOneLabel = new JLabel();
				playerOneLabel.setText(lang.Player+" "+lang.One+":");
				playerOneLabel.setFont(new java.awt.Font("Tahoma",0,20));
				playerOneLabel.setBounds(130, 140, 140, 31);
			}
			{
				playerTwoLabel = new JLabel();
				playerTwoLabel.setText(lang.Player+" "+lang.Two+":");
				playerTwoLabel.setFont(new java.awt.Font("Tahoma",0,20));
				playerTwoLabel.setBounds(415, 140, 140, 31);
			}
			{
				playerAHand = new JLabel();
				playerAHand.setFont(new java.awt.Font("Tahoma",0,18));
				playerAHand.setBounds(130, 164, 110, 31);
			}
			{
				playerBHand = new JLabel();
				playerBHand.setFont(new java.awt.Font("Tahoma",0,18));
				playerBHand.setBounds(415, 164, 110, 31);
			}
			{
				vSeparator = new JSeparator();
				vSeparator.setOrientation(SwingConstants.VERTICAL);
				vSeparator.setBounds(325, 134, 1, 140);
			}
			{
				hSeparator = new JSeparator();
				hSeparator.setOrientation(SwingConstants.HORIZONTAL);
				hSeparator.setBounds(40, 110, 570, 1);
			}
			{
				timeLabel = new JLabel();
				contentPane.add(timeLabel);
				timeLabel.setText(lang.Time+":");
				timeLabel.setFont(new java.awt.Font("Tahoma",0,16));
				timeLabel.setBounds(275, 290, 70, 20);
			}
			{
				timerDisplay = new JLabel();
				timerDisplay.setText("0:00");
				timerDisplay.setName("Timer");
				timerDisplay.setFont(new java.awt.Font("Tahoma",0,16));
				timerDisplay.setBounds(340, 290, 38, 20);
			}
			final JLabel avatar1 = new JLabel();
			{
				avatar1.setOpaque(true);
				String path;
				if(controller.p1Type==0){
					path = humanIcons[(new Random()).nextInt(5)];
				}else{
					path = computerIcons[controller.p1Type-1];
				}
				avatar1.setIcon(new ImageIcon("src/images/"+path+".png"));
				avatar1.setBounds(20,200,128,128);
			}
			final JLabel avatar2 = new JLabel();
			{
				avatar2.setOpaque(true);
				String path;
				if(controller.p2Type==0){
					path = humanIcons[(new Random()).nextInt(5)];
				}else{
					path = computerIcons[controller.p2Type-1];
				}
				avatar2.setIcon(new ImageIcon("src/images/"+path+".png"));
				avatar2.setBounds(504,200,128,128);
			}
			{
				contentPane.add(timerDisplay);
				contentPane.add(playerOneLabel);
				contentPane.add(playerTwoLabel);
				contentPane.add(playerAHand);
				contentPane.add(playerBHand);
				contentPane.add(vSeparator);
				contentPane.add(hSeparator);
				contentPane.add(avatar1);
				contentPane.add(avatar2);
				for(JButton b: buttons){
					contentPane.add(b);
				}
			}

			pack();
			setTitle("Hailstorm");
			setSize(656,391);
			validate();
			setVisible(true);
			this.requestFocus();
			this.addKeyListener(controller);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setIconImage( new ImageIcon("src/images/alphabeta.png").getImage() );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void clickButton(String name){
		for(JButton b:buttons){
			if(b.getName().equals(name)){
				b.doClick();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if("Quit".equals(command)){
			this.dispose();
			//   System.exit(0);
		}else{
			this.requestFocus();
		}
	}

	public void setHand(int num,String val){
		if(num==1){
			playerAHand.setText(val);
		}else{
			playerBHand.setText(val);
		}
	}

	public void doEndGame() {
		for(JButton b:buttons){
			if(b.getName()!="click"){
				b.setEnabled(false);
			}
			b.removeActionListener(controller);
		}
	}

}
