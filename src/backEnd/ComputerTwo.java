package backEnd;

import java.awt.Color;
import java.util.Random;

public class ComputerTwo implements Player {

	private Hand hand;
	private Hand tempHand;
	private Color color;
	private Random prnGenerator = new Random();

	public ComputerTwo(Color color){
		this.color = color;
		hand = new Hand();
	}


	@Override
	public boolean isHuman(){
		return false;
	}

	@Override
	public boolean checkIsWin(){
		return hand.isWin();
	}

	@Override
	public void cardPicked(int x) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int pickCard(Hand handB, Pool pool) {
		int choice = findWin(pool);
		if ( choice == -1 ){
			choice = findBlock(handB, pool);
			if ( choice == -1 ){
				choice = startPair(pool);
				if ( choice == -1 ){
					choice = doRandom(pool);
				}
			}
		}     
		hand.add(choice);
		return choice;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Hand getHand() {
		return hand;
	}

	private int findWin(Pool l_pool){
		for ( int i:l_pool ){
			fillTempHand(hand);
			tempHand.add(i);
			if ( checkIsWin() == true ){
				return i;
			}
		}
		return -1;
	}

	private int findBlock(Hand l_handB, Pool l_pool){
		for ( int i:l_pool ){
			fillTempHand(l_handB);
			tempHand.add(i);
			if ( tempHand.isWin() == true ){
				return i;
			}
		}
		return -1;
	}

	private int startPair(Pool l_pool){
		for ( int i:l_pool ){
			Pool tempPool = new Pool(l_pool);
			tempPool.remove(i);
			for ( int j:l_pool ){
				//tempPool.remove(j);
				fillTempHand(hand);
				if ( tempHand.isWin() == true ){
					if ( prng(0,1) == 1 ){
						return i;
					} else {
						return j;
					}
				}
			}
		}
		return -1;
	}

	private int doRandom(Pool l_pool){
		int random;
		do {
			random = prng(1,9);
		} while ( !l_pool.contains(random) );
		return random;
	}

	private void fillTempHand(Hand whichHand){
		tempHand = new Hand(whichHand);
	}

	private int prng(int lowLimit, int highLimit){
		int index = prnGenerator.nextInt( highLimit - lowLimit + 1 );
		return lowLimit + index;
	}

}
