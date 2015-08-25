/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	protected BinarySearchTree<KVPair> myTree;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		myTree = new BinarySearchTree<KVPair>();
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return myTree.myRoot.mySize;
	}

	/**
	 * Returns whether the map contains the given key.
	 */
	public boolean containsKey(K key) {
		return myTree.contains(new KVPair(key, null));
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		V toReturn = null;
		if (containsKey(key)) {
			toReturn = get(key);
			KVPair toModify = myTree.get(new KVPair(key, null));
			toModify.value = value;
		}
		else{
			myTree.add(new KVPair(key, value));
		}
		return toReturn;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		return myTree.remove(myTree.myRoot, new KVPair(key, null), null).value;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		return myTree.get(new KVPair(key, null)).value;
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
		public int compareTo(KVPair other) {
			return key.compareTo(((KVPair)other).key);
		}
		
		@Override
		public boolean equals(Object other){
			return key.equals(((KVPair)other).key); 
		}
		
		@Override
		public String toString() {
			return "<" + key.toString() + ", " + value.toString() + "> ";
		}
	}
}
