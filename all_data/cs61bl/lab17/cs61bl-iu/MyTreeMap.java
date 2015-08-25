/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree<KVPair<K, V>> tree; 
	
	
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		tree = new BinarySearchTree<KVPair<K, V>>();
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
		return tree.contains((KVPair<K, V>) key);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		if (containsKey(key)){
			V rtn = tree.get((KVPair<K, V>) key).getValue();
			tree.get((KVPair<K, V>) key).setValue(value);
			return rtn;
		}
		tree.add(new KVPair<K, V>(key, value));
		size++;
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (!tree.contains((KVPair<K, V>) key)){
			return null;
		}
		V rtn = tree.get((KVPair<K, V>) key).getValue();
		tree.delete((KVPair<K, V>) key);
		size--;
		return rtn;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (!tree.contains((KVPair<K, V>) key)){
			return null;
		}
		V rtn = tree.get((KVPair<K, V>) key).getValue();
		return rtn;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair<K extends Comparable<K>, V> implements Comparable<KVPair<K, V>>{
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
		
		public V getValue(){
			return value;
		}
		
		@Override
		public int compareTo(KVPair<K, V> o) {
			// TODO Auto-generated method stub
			return key.compareTo(((K) o.getKey()));
		}
	}
}
