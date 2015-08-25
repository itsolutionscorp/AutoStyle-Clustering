/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> t;
	
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		size = 0;
		t = new BinarySearchTree<KVPair> ();
		t.myRoot = null;
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
		if(t.myRoot == null){
			return false;
		} else {
			KVPair temp = new KVPair(key, null);
			return t.contains(temp);
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
		if (t.myRoot == null) {
			t.myRoot.myItem = new KVPair(key, value);
		}
		KVPair temp = new KVPair(key, value);
		if (t.contains(temp)) {
			KVPair k = (MyTreeMap<K, V>.KVPair) t.search(t.myRoot, temp);
			if (k == null) {
				return null;
			}
			return k.value;
		}
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	
	public V remove(K key) {
		// TODO Complete this!
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		KVPair temp = new KVPair(key, null);
		if (t.get(t.myRoot, temp) == null) {
			return null;
		}
		return ((MyTreeMap<K, V>.KVPair) t.get(t.myRoot, temp)).value;
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
		
		public int compareTo(KVPair k) {
			if (key.compareTo(k.key) == 0) {
				return 0;
			} else if (key.compareTo(k.key) == -1) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
