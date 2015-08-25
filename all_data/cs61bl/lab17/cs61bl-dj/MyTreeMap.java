import java.security.Key;
import java.util.Set;


/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> myTree;


	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myTree = new BinarySearchTree<KVPair>();
		size = 0;
		
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
		KVPair tempKey = new KVPair(key, null);
		if (myTree.contains(tempKey)){
			return true;
		} else {
			return false;
		}
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
		KVPair tempKey = new KVPair(key, null);
		V rtn = null;
		if (myTree.contains(tempKey)) {
			rtn = get(key);
			remove(key);
			KVPair toAdd = new KVPair(key, value);
			myTree.add(toAdd);		
		}else {		
			KVPair toAdd = new KVPair(key, value);
			myTree.add(toAdd);
			size +=1;
		}	
		return rtn;	
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		KVPair tempKey = new KVPair(key, null);
		V rtn = null;
		if (!myTree.contains(tempKey)) {
			rtn = null;
		}else {
			rtn = get(key);
			myTree.myRoot = myTree.remove(myTree.myRoot, tempKey);		
		}
		size --;
		return rtn;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		KVPair tempKey = new KVPair(key, null);
		V rtn = null;
		if (!myTree.contains(tempKey)) {
			rtn = null;
		} else {		
			for (KVPair k: myTree){
				if (k.key == key) {
					rtn = k.value;
				}
			} 
		} return rtn;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable<KVPair>{
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}
		
		public KVPair() {
			key = null;
			value = null;
		}

		public void setValue(V v) {
			value = v;
		}
		
		public int compareTo(KVPair o) {
			if (o == null) {
				throw new NullPointerException();
			} else if (key.toString().charAt(0) < (o).key.toString().charAt(0)) {
				return -1;
			} else if (key.toString().charAt(0) > (o).key.toString().charAt(0)) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
