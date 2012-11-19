package backEnd;

import java.util.Iterator;

public class Pool implements Iterable<Integer>{
	private int[] pool = new int[9];
	private int size;

	public Pool(){
		for(int i = 0; i<9; ++i){
			pool[i]=i+1;
		}
		size = 9;
	}

	public Pool(Pool other) {
		for(int i = 0; i<9; ++i){
			pool[i]=i+1;
		}
		size = 9;

		for(int x = 1; x<=9; ++x){
			if(!other.contains(x)){
				remove(x);
			}
		}
	}

	public boolean contains(int x){
		boolean ret = false;
		for(int i:pool){
			ret = ret||(i==x);
		}
		return ret;
	}

	public int size(){
		return size;
	}

	public final void remove(int x){
		for(int i=0; i<9; ++i){
			if(pool[i]==x){
				pool[i]=0;
			}
		}
		--size;
		rebuild();
	}

	private void rebuild(){
		for(int i=0; i<9; ++i){
			if(pool[i]==0&&i!=8){
				pool[i]=pool[i+1];
				pool[i+1]=0;
			}
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new MyIterator(pool);
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for(int x:this){
			builder.append(x);
			builder.append(",");
		}
		builder.delete(2*size,2*size+1);
		builder.append("]");
		return builder.toString();
	}

}
