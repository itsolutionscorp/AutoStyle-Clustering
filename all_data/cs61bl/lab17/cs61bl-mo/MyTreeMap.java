

/** The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> myTree;
	
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myTree = new BinarySearchTree<KVPair>();
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
		if (myTree.myRoot != null) {
			return containsHelper(myTree.myRoot, key);
		}
		return false;
	}
	
	private boolean containsHelper(BinaryTree<KVPair>.TreeNode t, K key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem.getKey()) == 0) {
			return true;
		} else if (key.compareTo(t.myItem.getKey()) < 0) {
			return containsHelper(t.myLeft, key);
		} else {
			return containsHelper(t.myRight, key);
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
		KVPair item = new KVPair(key, value);
		if (this.containsKey(key)) {
			BinaryTree<KVPair>.TreeNode t = myTree.getNode(item);
			V valueToReturn = t.myItem.getValue();
			t.myItem.setValue(value);
			return valueToReturn;	
		}
		myTree.add(item);	
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
//	public V remove(K key) {
//		// TODO Complete this!
//		if (this.containsKey(key)) {
//			
//		} else {
//			return null;
//		}
//	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (this.containsKey(key)) {
			KVPair item = new KVPair(key, null);
			BinaryTree<KVPair>.TreeNode t = myTree.getNode(item);
			V valueToReturn = t.myItem.getValue();
			return valueToReturn;	
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
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}

		@Override
		public int compareTo(MyTreeMap<K, V>.KVPair o) {
			if (this.key.compareTo(o.getKey()) < 0) {
				return -1;
			}
			if (this.key.compareTo(o.getKey()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
