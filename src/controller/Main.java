package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import lang.Language;

import ui.Credits;
import ui.MainDisplay;
import ui.Options;
import backEnd.*;

public class Main implements ActionListener, KeyListener {

	public static void main(String [] args){
		new Main();
	}

	private transient MainDisplay display;
	private transient Game theGame;
	private transient Timer timer;
	public int p1Type;
	public int p2Type;
	public int sliderPosition;
	public Color p1Color;
	public Color p2Color;
	public String langType;
	public Language language;
	private boolean pref = true;



	public Main(){
		p1Type = 0;
		p2Type = 1;
		sliderPosition = 0;
		p1Color = Options.COLORS[0];
		p2Color = Options.COLORS[4];
		langType = "English";
		setup();
	}

	public final void setup(){
		try {
			language = new Language(langType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		theGame = new Game(assignPlayer(p1Type,p1Color),
				assignPlayer(p2Type,p2Color));
		if(display==null){
			display = new MainDisplay(this,language);
		}else{
			display.reset(language);
		}
		timer = new Timer(display.timerDisplay);
		timer.reset();
		timer.start();
		display.validate();
		display.requestFocus();
		if(!theGame.getCurrentPlayer().isHuman()){
			doComputer();
		}
	}

	private void setHandDisplay(){
		display.setHand(theGame.getCurrentPlayerTurn(),theGame.getCurrentPlayer().getHand().toString());
		display.setHand(3-theGame.getCurrentPlayerTurn(),theGame.getNextPlayer().getHand().toString());
	}

	private Player assignPlayer(int type, Color color) {
		Player ret;
		switch(type){
		case 0: ret=new Human(color); break;
		case 1: ret=new ComputerTwo(color); break;
		default: ret=new Computer(color); break;
		}
		return ret;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals("card")){
			JButton button = (JButton)event.getSource();
			button.setBackground(theGame.getCurrentPlayer().getColor());
			button.setName("click");
			button.removeActionListener(this);
			setHandDisplay();
			display.requestFocus();
			display.validate();

			if(theGame.isDone()){
				doEndGame();
			}else{
				if(theGame.getCurrentPlayer().isHuman()){
					theGame.cardPicked(Integer.valueOf(
							((JButton)event.getSource()).getText()));
					setHandDisplay();
				}
				theGame.advanceTurn();
				if(theGame.isDone()){
					doEndGame();
				}else if(!theGame.getCurrentPlayer().isHuman()){
					doComputer();
				}
			}
		}else if(command.equals("newGame")){
			setup();
		}else if(command.equals("credits")){
			new Credits(language.Credits);
		}else if(command.equals("preferences")){
			if(pref){
				(new Options(display,this)).setVisible(true);
			}
			pref = !pref;
		}
	}

	private void doComputer(){
		if(!theGame.isDone()){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					try {
						Thread.sleep(sliderPosition);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String chosen = String.valueOf(theGame.takeTurn());
					display.clickButton(chosen);
				}
			});
		}else{
			doEndGame();
		}
	}

	private void doEndGame() {
		if(theGame.getCurrentPlayer().checkIsWin()){
			display.setHand(theGame.getCurrentPlayerTurn(),language.Win+"!");
			display.setHand(3-theGame.getCurrentPlayerTurn(),language.Lose+"!");
		}else if(theGame.getNextPlayer().checkIsWin()){
			display.setHand(theGame.getCurrentPlayerTurn(),language.Lose+"!");
			display.setHand(3-theGame.getCurrentPlayerTurn(),language.Win+"!");
		}else{
			display.setHand(1,language.Draw);
			display.setHand(2,language.Draw);
		}
		timer.stop();
		display.doEndGame();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(theGame.getCurrentPlayer().isHuman()){
			final String button = String.valueOf(event.getKeyChar());
			display.clickButton(button);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		//fail
	}

	@Override
	public void keyTyped(KeyEvent event) {
		//fail
	}
}
