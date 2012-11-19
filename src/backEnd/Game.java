package backEnd;

public class Game{
	private Player playerA, playerB;
	private boolean isPlayerA;
	private boolean endGame;
	private Pool pool;

	public Game(Player playerOne, Player playerTwo){
		playerA = playerOne;
		playerB = playerTwo;
		isPlayerA = true;
		endGame = false;
		pool = new Pool();
	}

	public Player getCurrentPlayer(){
		Player ret;
		if(isPlayerA){
			ret = playerA;
		}else{
			ret = playerB;
		}
		return ret;
	}
	public Player getNextPlayer(){
		Player ret;
		if(isPlayerA){
			ret = playerB;
		}else{
			ret = playerA;
		}
		return ret;
	}

	public void advanceTurn(){
		isPlayerA = !isPlayerA;
	}

	public int getCurrentPlayerTurn(){
		return (isPlayerA?1:2);
	}

	public int takeTurn(){
		int choice = getCurrentPlayer().pickCard(
				getNextPlayer().getHand(),
				pool
				);
		pool.remove(choice);
		return choice;
	}

	public boolean isDone(){
		endGame |= playerA.checkIsWin();
		endGame |= playerB.checkIsWin();
		endGame |= (pool.size()==0);
		return endGame;
	}

	public void cardPicked(Integer x) {
		getCurrentPlayer().cardPicked(x);
		pool.remove(x);
	}
}
