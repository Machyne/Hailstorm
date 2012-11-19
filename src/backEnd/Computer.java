package backEnd;

import java.awt.Color;
import java.util.Random;

public class Computer implements Player {

	private Hand hand;
	private Color color;

	public Computer(Color color){
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
		int dummie = Integer.MIN_VALUE;
		int choice = 0;
		int accum = 0;
		if(pool.size()==9){
			//The optimal first choice is an even number
			choice = 2+2*(new Random()).nextInt(4);
		}
		else{
			for(int x:pool){
				Hand handA = new Hand(hand);
				handA.add(x);
				Pool pTemp = new Pool (pool);
				pTemp.remove(x);
				int val = minValue(handA, handB, pTemp,
						Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
				if(val>dummie){
					accum = 1;
					dummie = val;
					choice = x;
				}else if(val==dummie && (new Random()).nextInt(accum)==0 ){
					accum++;
					dummie = val;
					choice = x;
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


	private int maxValue(final Hand handA, final Hand handB, final Pool pool, final int Alpha, final int Beta, final int steps){
		int alpha = Alpha;
		if(handA.isWin()){
			return 10-steps;
		}else if(handB.isWin()){
			return -10-steps;
		}else if(pool.size()==0){
			return 0-steps;
		}
		for(int i:pool){
			final Hand hTemp = new Hand(handA);
			hTemp.add(i);
			final Pool pTemp = new Pool(pool);
			pTemp.remove(i);
			alpha = Math.max(alpha, minValue(hTemp, handB, pTemp, alpha, Beta, steps+1) );
			if(alpha>=Beta){
				return Beta;
			}
		}
		return alpha;
	}

	private int minValue(final Hand handA, final Hand handB, final Pool pool, final int Alpha, final int Beta, final int steps){
		int beta = Beta;
		if(handA.isWin()){
			return 10-steps;
		}else if(handB.isWin()){
			return -10-steps;
		}else if(pool.size()==0){
			return 0-steps;
		}
		for(int i:pool){
			Hand hTemp = new Hand(handB);
			hTemp.add(i);
			Pool pTemp = new Pool(pool);
			pTemp.remove(i);
			beta = Math.min(beta, maxValue(handA, hTemp, pTemp, Alpha, beta, steps+1) );
			if(beta<=Alpha){
				return Alpha;
			}
		}
		return beta;
	}

}
