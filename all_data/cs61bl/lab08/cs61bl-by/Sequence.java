
import java.util.Iterator;

public class Sequence <T> implements Iterable<T>{
	protected T[] myValues;
	//private T myItem;
	private int myCount;
	
	public Sequence(int capacity){
		myValues=(T[])new Object[capacity];
		myCount=0;
	}
	
    public void insert(T item, int insertPos) {
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = item;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	if (myCount == 0){
    		return true;
    	} else {
    		return false;
    	}
    }

    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos){
    	if (pos < myCount){
    		return myValues[pos];
    	} else {
    		System.err.println("Position not included");
    		System.exit(1);
    		return null; //to compile
    	}
    }
    
    public String toString(){
    	if (myCount == 0){
    		return "";
    	} else {
    	String s = new String("");
    	for (int k=0; k<myCount-1;k++){
    		s+=myValues[k] + " ";		
    		}
    	return s + myValues[myCount-1];
    	}
    }
    
	public T remove (int pos) {
		if (pos < 0 || pos >= myValues.length) {
			System.err.println("attempt to remove invalid pos");
			System.exit(1);
		}
		T a = myValues[pos];
		for(int k = 0; k<= myValues.length-1; k++){
			if (k<pos){
				continue;
			} else if (k==myValues.length-1){
				myValues[k] = null;
			} else {
				myValues[k] = myValues[k+1];
			}
		}
		myCount --;
		return a;
	}

	public boolean contains(T item){
		for (int i=0; i < myCount; i++){
			if (item==myValues[i]){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		Sequence<String> s = new Sequence<String>(3);
		
		s.insert("YAY",0);
		s.insert("stuff",1);
		s.insert("ffffffff",2);
		System.out.println(s.elementAt(0)); //expect YAY
		System.out.println(s.toString());  //expect YAY stuff fffffff
		s.remove(0);
		System.out.println(s.elementAt(0)); //expect stuff
		System.out.println(s.toString());  //expect stuff fffffff
		
		// do we have to not have a null between indices
		
		Iterator<String> t=s.iterator();
		while (t.hasNext()){
			System.out.println(t.next());
		}
		
		
		Sequence<Integer> s1 = new Sequence<Integer>(4);
		s1.insert(5,0);
		s1.insert(3,1);
		s1.insert(10,2);
		System.out.println(s1.elementAt(0));
		System.out.println(s1.toString());
		s1.remove(0);
		System.out.println(s1.elementAt(0));
		System.out.println(s1.toString());
		
		Iterator<Integer> t1=s1.iterator();
		while (t1.hasNext()){
			System.out.println(t1.next());
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		
		return new SequenceIterator<T>();
	}
	
	private class SequenceIterator<T> implements Iterator<T>{
		
		int nextValueToReturn=0;
		

//		public void initIterator() {
//			nextValueToReturn=0;
//		}
		
		public boolean hasNext(){
			if(nextValueToReturn<myCount){
				return true;
			}else{
				return false;
			}
		}
		
		public T next(){
			//nextValueToReturn++;
			T myItem= (T)myValues[nextValueToReturn++];
			return myItem;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}
}