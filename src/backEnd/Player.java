package backEnd;

import java.awt.Color;

public interface Player {
	boolean checkIsWin();
	boolean isHuman();
	void cardPicked(int x);
	int pickCard(Hand handB, Pool pool);
	Color getColor();
	Hand getHand();
}
