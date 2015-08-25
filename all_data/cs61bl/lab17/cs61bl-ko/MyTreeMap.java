
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> bst;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		size = 0;
		bst = new BinarySearchTree<KVPair>();
		
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
		KVPair check = new KVPair(key, null);
		return bst.contains(check);
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
		if (key == null) {
			return null;
		}
		V temp = get(key);
		if (temp == null) {
			bst.add(new KVPair(key, value));
			size++;
		} else {
			KVPair check = new KVPair(key, null);
			bst.find(check).setValue(value);			
		}
		return temp;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (key == null) {
			return null;
		} else if (!containsKey(key)) {
			return null;
		} else {
			V ret = get(key);
			KVPair check = new KVPair(key, null);
			bst.remove(check);
			size--;
			return ret;
		}
	}
	
	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!		
		if (key == null) {
			return null;
		} else if (containsKey(key)) {
			KVPair check = new KVPair(key, null);
			return bst.find(check).getValue();
		}
		return null;
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
		
		public K getKey() {
			return key;
		}

		@Override
		public int compareTo(MyTreeMap<K, V>.KVPair o) {
			KVPair other = (KVPair) o;
			if (this.getKey().compareTo(other.getKey()) == 0) {
				return 0;
			} else if (this.getKey().compareTo(other.getKey()) == -1) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
