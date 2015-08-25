/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree<KVPair> myBST;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		myBST = new BinarySearchTree<KVPair>();
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
		KVPair dummyPair = new KVPair(key, null);
		if (myBST.contains(dummyPair)){
			return true;
		}
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
		KVPair pair = new KVPair(key, value);
		size++;
		myBST.add(pair);
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair dummyPair = new KVPair(key, null);
		if (myBST.contains(dummyPair)){
			size--;
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		KVPair dummyPair = new KVPair(key, null);
		if (myBST.contains(dummyPair)){
			return myBST.
		}
		return null;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable<KVPair> {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object o){
			if (o instanceof MyTreeMap.KVPair && key.equals(((KVPair) o).key)){
				return true;
			} 
			return false;
		}

		@Override
		public int compareTo(MyTreeMap<K, V>.KVPair o) {
			return key.compareTo(o.key);
		}
	}
}
