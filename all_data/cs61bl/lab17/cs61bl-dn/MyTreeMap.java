

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	BinarySearchTree<KVPair> bst;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		bst = new BinarySearchTree<KVPair>();
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
		KVPair temp = new KVPair(key, null);
		return bst.contains(temp);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		KVPair temp = putHelper(new KVPair(key, value));
		if (temp == null) {
			size ++;
			return null;
		}
		return temp.getValue();
	}
	
	public KVPair putHelper(KVPair kv) {
		return bst.putHelper(kv);
	}
	
	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair temp = removeHelper(new KVPair(key, null));
		if (temp == null) {
			return null;
		}
		return temp.getValue();
	}
	
	public KVPair removeHelper(KVPair kp) {
		return bst.remove(kp);
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		KVPair temp = getHelper(new KVPair(key, null));
		if (temp == null) {
			return null;
		}
		return temp.getValue();
	}
	
	public KVPair getHelper(KVPair kp) {
		return bst.get(kp);
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

		public void setValue(V v) {
			value = v;
		}
		
		public V getValue() {
			return value;
		}
		
		public int compareTo(KVPair anotherpair){
			return key.compareTo(anotherpair.key);
		}
	}
}
