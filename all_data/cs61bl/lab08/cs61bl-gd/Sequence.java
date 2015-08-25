package lab8;

import java.util.Iterator;

public class Sequence<T> implements Iterable<T>{
	
	private int count ;
	private T[] myValues;

	
	public Sequence(int capacity) {
        
    	 myValues= (T[]) new Object[capacity];
		
    			count =0;
    }
	public void add(T tobeAdded){
		if(count>=myValues.length){
	    	System.out.println("full sequence");
			System.exit(1);
	    }
		if (count<myValues.length){
			myValues[count]=tobeAdded;
	    	
	    	count++;
		}
	}
	public void insert(T toInsert, int insertPos) 
    {if(insertPos<0||insertPos>=count||count>=myValues.length){
    	System.out.println("invalid index");
		System.exit(1);
    }
		if (insertPos <count){
        for (int k = myValues.length-1; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        count++;}
    else{if (insertPos== count){
        	myValues[insertPos] = toInsert;
        	count++;
        	}
        }
    }
	
	public void remove(int pos){
		if(pos<0||pos>=count||count==0){
	    	System.out.println("invalid index");
			System.exit(1);
	    }
		if(pos<count){
    	for (int i =pos;i < count;i++){if (i == count-1)
    {myValues[i]=null;}else{
		
		myValues[i]= myValues[i+1];} 
			
		}
		count--;}
    	
    }
	public boolean isEmpty(){
    	if(count==0){return true;}else{return false;}
    	
    }
  
    	
    	
    public int size(){
    	return count;
    }
    public T elementAt(int pos){
    	if(pos>=count){
    	System.err.println("out of bound");
    	System.exit(1);
    	}
    	

        	return myValues[pos];
    	
    }
    public boolean contains(T k){
    	boolean result;
    	result = false;
    
    	for( int i =0;i<count;i++){
    		if(myValues[i]==k){
    			result = true;
    			break;}else{if(myValues[i].equals(k)){result = true;}}
    		
    	}
    	
    	
    	return result;
    }
    
    public String toString(){if(count==0){return "";}else{
    	String result = new String();
    	result = (myValues[0]).toString();
    	for( int i = 1;i<count;i++){
    		result +=" "+ myValues[i];
    	}
    	
    	
    	return result;}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	
	 private class subiterator implements Iterator<T> {
    	private int index ;
    	
    	public subiterator(){
    	index=0;
    		
    	}
    	public boolean hasNext(){
    		return index<=(count-1);
    		
    		
    	}
    	public T next(){
    		T item;
    		
    		if(index>=count){
    			throw new IllegalStateException();
    		}else{
    			item =(T)myValues[index];
    			index++;
    		}
    		return item;
    		
    	}
    	public void  remove(){
    		throw new UnsupportedOperationException();
    	}
    }



	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new subiterator();
	}



	
	
}