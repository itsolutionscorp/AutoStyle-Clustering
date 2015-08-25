import java.util.Iterator;

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable, V>  {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	
	private BinarySearchTree<KVPair> myBST;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myBST=new BinarySearchTree<KVPair>();
		
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns whether the map contains the given key.
	 */
	public boolean containsKey(K key) {
		// TODO Complete this!
		return false;
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		// TODO Complete this!		
		KVPair backVal=myBST.put(new KVPair(key,value));
		if(backVal!=null){
			return backVal.value;
		}else{
			size++;
			return null;
		}
		
		
	}
	
	


	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		//updated the size
		size--;
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		KVPair result= myBST.get(new KVPair(key,null));
		if (result!=null)
		return result.value;
		else return null;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable{
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}


		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			KVPair p=(KVPair)o;
			if(key.compareTo(p.key)<0)
				return -1;
			else if(key.compareTo(p.key)==0)
				return 0;
			else
				return 1;
		}
	}
}
