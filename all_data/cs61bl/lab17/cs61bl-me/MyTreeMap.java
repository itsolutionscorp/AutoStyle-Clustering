/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	private BinarySearchTree<KVPair> treeMap;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		treeMap = new BinarySearchTree<KVPair>();
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
		return treeMap.contains(new KVPair(key, null));
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		KVPair prevPair = treeMap.get(new KVPair(key, null));
		if (prevPair == null) {
			size++;
			treeMap.add(new KVPair(key, value));
			return null;
		}
		V prevValue = prevPair.value;
		prevPair.value = value;
		return prevValue;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V prevValue = get(key);
		if (prevValue != null) {
			treeMap.remove(new KVPair(key, null));
			size--;
		}
		return prevValue;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		KVPair get = treeMap.get(new KVPair(key, null));
		return get != null ? get.value : null;
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
		
		public boolean equals(Object o) {
			return key.equals(((KVPair)o).key);
		}

		public int compareTo(KVPair o) {
			return key.compareTo(o.key);
		}
	}
}
