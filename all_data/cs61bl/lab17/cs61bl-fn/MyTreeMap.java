/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree<KVPair> myTree;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		size = 0;
		myTree = new BinarySearchTree<KVPair>();
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
		KVPair a = new KVPair(key, null);
		return myTree.contains(a);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		KVPair a = new KVPair(key, value);
		if (containsKey(key)) {
			KVPair toReturn = myTree.get(a);
			V valueReturn = toReturn.getValue();
			toReturn.setValue(value);
			return valueReturn;
		} else {
			size++;
			myTree.add(a);
			return null;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair toRemove = myTree.remove(new KVPair(key, null));
		if (toRemove != null) {
			size--;
			return toRemove.getValue();
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		KVPair toReturn = myTree.get(new KVPair(key, null));
		return (toReturn == null)? null: toReturn.getValue();
	}
	public void print() {
		if (size != 0) {
			myTree.print();
		} else {
			System.out.println("empty map");
		}
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
		public int compareTo(KVPair t){
			return key.compareTo(t.getKey());
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public String toString() {
			return "("+key.toString()+", "+value.toString()+")";
		}
	}
}
