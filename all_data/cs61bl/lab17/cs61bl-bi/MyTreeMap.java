
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no 	way to tell whether it should go on 
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
		 bst = new BinarySearchTree<>();
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
		return bst.contains(new KVPair(key, null));
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
		size ++;
		KVPair old = bst.get(new KVPair(key, null));
		if (key != null && value != null) {
			bst.add(new KVPair(key, value));
		}
		if (old != null) {
			return old.getValue();
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
		size --;
		if (!bst.contains(new KVPair(key, null))){
			return null;
		}
		KVPair KV = bst.get(new KVPair(key, null));
		bst.remove(new KVPair(key, null));
		return KV.getValue();
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		KVPair old = bst.get(new KVPair(key, null));
		if (old != null) {
			return old.getValue();
		}
		return null;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 * @param <T>
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

		public K getKey(){
			return key;
		}

		public V getValue() {
			return value;
		}
		
		public int compareKey(K k) {
			return key.compareTo(k);
		}

		@Override
		public int compareTo(MyTreeMap<K, V>.KVPair o) {
			// TODO Auto-generated method stub
			return key.compareTo(o.key);
	
		}


	}
}
