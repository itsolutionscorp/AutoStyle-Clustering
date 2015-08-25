
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree structure;
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		structure = new BinarySearchTree();
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
		if (structure == null) return false;
		else return containsKey(structure, key);
	}
	
	private boolean containsKey(BinarySearchTree alpha, K key) {
		if(alpha.myRoot == null) return false;
		else if (((KVPair) alpha.myRoot.myItem).getKey() == key) return true;
		else I don;t knwo how to do the recursion; 
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
		if (key!=null) return structure.put(key, value);
		else return null;
	}


	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (structure == null) return null;
		if (key!= null) return structure.remove(key);
		else return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (structure == null) return null;
		if (key!= null) return structure.get(key);
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

		public K getKey() {
			return key;
		}
		
		public void setValue(V v) {
			value = v;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
