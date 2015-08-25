
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	BinarySearchTree<KVPair> myBST;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		size = 0;
		myBST = new BinarySearchTree<KVPair>();
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
		return (helper(key, myBST.myRoot).getKey().equals(key));
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
		KVPair var = helper(key, myBST.myRoot);
		KVPair newvar;
		V tempval;
		if (var != null) {
			tempval = var.getValue();
			var.setValue(value);
			return tempval;
		} else {
			newvar = new KVPair(key, value);
			myBST.add(newvar);
			size++;
		}
		return null;
	}

	private KVPair helper(K key, BinaryTree.TreeNode t) {
		if (t == null) {
			return null;
		}
		int k = key.compareTo((K) t.myItem);
		if (k > 0) {
			helper(key, t.myRight);
		}
		if (k < 0) {
			helper(key, t.myLeft);
		}
		else {
			return (MyTreeMap<K, V>.KVPair) t.myItem;
		}
		return null;

	}
		
	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair toremove = helper(key, myBST.myRoot);
		if (toremove == null) {
			return null;
		} KVPair save = toremove;
		myBST.remove((MyTreeMap<K, V>.KVPair) key);
		return save.getValue();
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		return helper(key, myBST.myRoot).getValue();
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
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		@Override
		public int compareTo(MyTreeMap<K, V>.KVPair pair) {
			return pair.key.compareTo(this.key);
		}
	}
}
