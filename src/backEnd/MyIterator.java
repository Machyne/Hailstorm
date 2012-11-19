package backEnd;

import java.util.Iterator;

public class MyIterator implements Iterator<Integer> {
	private int count;
	private int[] collection;
	private int size;

	public MyIterator(int[] collection){
		this.collection = collection;
		count = 0;
		for(int x:this.collection){
			if(x!=0){
				++this.size;
			}
		}
	}


	@Override
	public boolean hasNext() {
		return count<size;
	}

	@Override
	public Integer next() {
		++count;
		return collection[count-1];
	}

	@Override
	public void remove() {
		this.collection[count] = 0;
	}

}
