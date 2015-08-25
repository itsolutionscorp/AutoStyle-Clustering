/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> extends BinarySearchTree {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
//	private KVPair myRoot;
//	private KVPair myLeft;
//	private KVPair myRight;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myRoot = null;
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
		return containsKeyHelper(myRoot, key);
	}
	
	private boolean containsKeyHelper(TreeNode t, K key) {
		if (key.compareTo(((KVPair) t.myItem).getKey()) == 0) {
			return true;
		}
		else if (key.compareTo(((KVPair) t.myItem).getKey()) < 0) {
			return containsKeyHelper(t.myLeft, key);
		}
		else {
			return containsKeyHelper(t.myRight, key);
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
		return putHelper(myRoot, key, value);
	}
	
	private V putHelper(TreeNode t, K key, V value) {
		V toReturn = null;
		if (this.containsKey(key)) {
			toReturn = this.get(key);
		}
		KVPair nodeToPut = new KVPair(key, value);
		if (t == null) {
			t.myItem = nodeToPut;
		}
		else if (key.compareTo(((KVPair) t.myItem).getKey()) < 0) {
			putHelper(t.myLeft, key, value);
		}
		else {
			putHelper(t.myRight, key, value);
		}
		return toReturn;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		return removeHelper(myRoot, key);
	}
	
	private V removeHelper(TreeNode t, K key) {
		TreeNode inorderSuccessor = null;
		if (t == myRoot) {
			TreeNode pointer = t.myLeft;
			while (pointer != null) {
				pointer = pointer.myLeft;
			}
			inorderSuccessor = pointer;
		}
		if (t != myRoot) {
			inorderSuccessor = t.myRight;
		}
		V toReturn = null;
		if (this.containsKey(key)) {
			toReturn = this.get(key);
			t.myItem = inorderSuccessor.myItem;
		}
		
		inorderSuccessor = null;
		return toReturn;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		return getHelper(myRoot, key);
	}
	
	public V getHelper(TreeNode t, K key) {
		if (!this.containsKey(key)) {
			return null;
		}
		else if (((KVPair) t.myItem).getKey().compareTo(key) == 0) {
			return ((KVPair) t.myItem).getValue();
		}
		else if (((KVPair) t.myItem).getKey().compareTo(key) < 0) {
			return getHelper(t.myLeft, key);
		}
		else {
			return getHelper(t.myRight, key);
		}
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		
	}
}
