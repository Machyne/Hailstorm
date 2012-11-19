package backEnd;

import java.awt.Color;

public class Human implements Player {

	private Hand hand;
	private Color color;

	public Human(Color color){
		this.color = color;
		hand = new Hand();
	}


	@Override
	public boolean isHuman(){
		return true;
	}

	@Override
	public boolean checkIsWin(){
		return hand.isWin();
	}

	@Override
	public void cardPicked(int x) {
		hand.add(x);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public int pickCard(Hand other, Pool pool) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Hand getHand() {
		return hand;
	}

}
