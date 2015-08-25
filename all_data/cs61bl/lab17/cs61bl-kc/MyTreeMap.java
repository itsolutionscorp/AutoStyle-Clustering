/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V>{

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair<K, V>> tree = new BinarySearchTree<KVPair<K, V>>();
	
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		tree.myRoot = null;
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
		KVPair<K,V> temp = new KVPair<K,V>((K)key, null);
		KVPair<K, V> searched = tree.find(temp);
		if (searched != null) {
			return true;
		} else {
			return false;
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
		KVPair<K,V> temp = new KVPair<K,V>((K)key, null);
		KVPair<K, V> searched = tree.find(temp);
		if (searched != null) {
			return searched.setValue(value);
		} else {
			tree.add(new KVPair<K,V>((K)key, value));
			size++;
			return null;
		}
	}
		
//			tree.myRoot = new KVPair(key, value);
//			return null;
//		return tree.myRoot.put(key, value);

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		KVPair<K,V> temp = new KVPair<K,V>((K)key, null);
		KVPair<K, V> searched = tree.find(temp);
		if (searched != null) {
			tree.remove(new KVPair<K,V>((K)key, null));
			return searched.value;
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
		KVPair<K,V> temp = new KVPair<K,V>((K)key, null);
		KVPair<K, V> searched = tree.find(temp);
		if (searched != null) {
			return searched.getValue();
		} else {
			return null;
		}
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	public class KVPair<K extends Comparable<K>, V> implements Comparable<KVPair<K, V>>{
		K key;
		V value;
		KVPair<K,V> myLeft, myRight;

		public KVPair(K k, V v) {
			key = k;
			value = v;
			myLeft = myRight = null;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}

		public V setValue(V v) {
			V curr = value;
			value = v;
			return curr;
		}

		@Override
		public int compareTo(KVPair<K, V> kvpair) {
			return key.compareTo(kvpair.getKey());
		}
	}
}
