package backEnd;

import java.util.Iterator;
import java.util.Arrays;

public class Hand implements Iterable<Integer>{
	private int[] hand = new int[5];
	private int size;
	private final int BIG = 100;

	public Hand(){
		size = 0;
	}

	public Hand(Hand other){
		size = 0;
		for(int x:other){
			this.add(x);
		}
	}

	public boolean isWin(){ //TODO Optimize
		int sum = 0;
	if (size()==3){
		sum = getSum();
	} else if (size()==4){
		for (int i=1; i<=4; i++){
			if (getSum() - get(i)==15){
				sum = 15;
			}
		}
	} else if (size()==5){
		for (int j=1; j<=5; j++){
			for (int k=2; k<=5; k++){
				if ( (j!=k) && (getSum() - get(j) - get(k)==15) ){
					sum = 15;
				}
			}
		}
	}
	return sum == 15;
	}

	@Override
	public Iterator<Integer> iterator() { 
		return new MyIterator(hand);
	}

	public final void add(int item){
		if(size>=5){
			throw new ArrayIndexOutOfBoundsException();
		}
		hand[size]=item;
		++size;
		for(int i=size;i<5;i++){
			hand[i]=BIG;
		}
		Arrays.sort(hand);
		for(int i=size;i<5;i++){
			hand[i]=0;
		}
	}

	public int size(){
		return size;
	}

	public int get(int index){
		if(index>size){
			throw new ArrayIndexOutOfBoundsException();
		}
		return hand[index-1];
	}

	private int getSum(){
		int localSum = 0;
		for(int i:this){
			localSum += i;
		}
		return localSum;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(int x:this){
			builder.append(x);
			builder.append(" ");
		}
		if(size!=0){
			builder.delete(2*size-1,2*size);
		}
		return builder.toString();
	}
}
