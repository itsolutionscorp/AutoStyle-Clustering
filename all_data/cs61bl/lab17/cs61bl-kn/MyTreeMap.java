/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> myMap;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		size = 0;
		myMap = new BinarySearchTree();
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
		return myMap.contains(temp);
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
		KVPair k = new KVPair(key, value);
		KVPair temp = myMap.replace(k);
		if (temp == null) {
			return null;
		} else {
			return temp.value;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair k = new KVPair(key, null);
		KVPair temp = myMap.remove(k);
		if (temp == null) {
			return null;
		} else {
			return temp.value;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		KVPair k = new KVPair(key, null);
		KVPair temp = myMap.find(k);
		if (temp == null) {
			return null;
		} else {
			return temp.value;
		}
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
		
		public boolean equals(KVPair pair) {
			return key.equals(pair.key);
		}
		
		@Override
		public int compareTo(KVPair pair) {
			return key.compareTo(pair.key);
		}
	}
}
