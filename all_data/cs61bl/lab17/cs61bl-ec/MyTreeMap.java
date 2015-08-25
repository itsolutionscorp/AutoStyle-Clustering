/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	BinarySearchTree<KVPair> b;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		size = 0;
		b = new BinarySearchTree<KVPair>();
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
		KVPair toSearch = new KVPair(key, null);
		return b.contains(toSearch);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		KVPair toPut = new KVPair(key, value);
		KVPair rtn = (MyTreeMap<K, V>.KVPair) b.find(toPut); 
		if (rtn == null) { 
			// first time you add this key 
			size++;
			b.add(toPut);
			return null; 
		} else {
			V temp = rtn.getValue(); 
			rtn.setValue(value);
			return temp; 
		}
		
		
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair removed = (MyTreeMap<K, V>.KVPair) b.delete(new KVPair(key, null)); 
		if (removed == null) {
			return null;
		} else {
			size--;
			return removed.getValue();
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		KVPair toFind = (MyTreeMap<K, V>.KVPair) b.find(new KVPair(key, null));
		if (toFind == null) {
			return null;
		} else return toFind.getValue(); 
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
		
		public V getValue() {
			return value; 
		}
		


		@Override
		public int compareTo(KVPair o) {
			return this.key.compareTo(o.key);
		}
		
		@Override
		public boolean equals(Object obj) {
			return this.key.equals(((KVPair)obj).key);
		}
		
		
	}
}
