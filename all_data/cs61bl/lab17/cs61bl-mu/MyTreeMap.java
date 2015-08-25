/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree<KVPair> treeMap;
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
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
		// TODO Complete this!
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
		// TODO Complete this!
		V x = null;
		if (get(key) == null) {
			
		
			treeMap.add(new KVPair(key, value));
	
		} else {
			x = get(key);
			treeMap.add(new KVPair(key, value));
		}
		
		return x;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		KVPair p = new KVPair(null, get(key));
		treeMap.removeContent(new KVPair(key, null));
		if (p.value != null) {
			return p.value;
		} else {
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		KVPair temp = treeMap.getNode(new KVPair(key, null));
		if (temp != null) {
			return temp.value;
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

		@Override
		public int compareTo(MyTreeMap<K, V>.KVPair t) {
			// TODO Auto-generated method stub
			return this.key.compareTo(t.key);
		}
	}
}
