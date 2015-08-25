/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */

public class MyTreeMap<K extends Comparable<K>, V> extends BinarySearchTree {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		super();
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
		if (myRoot == null) {
			return false;
		}
		return doesContain(myRoot, key);
	}

	public boolean doesContain(TreeNode t, K key) {
		if (t != null) {
			if (key.compareTo(((MyTreeMap<K, V>.KVPair) t.myItem).getKey()) == 0) {
				return true;
			} else if (key.compareTo(((MyTreeMap<K, V>.KVPair) t.myItem).getKey()) < 0) {
				return doesContain(t.myLeft, key);
			} else {
				return doesContain(t.myRight, key);
			}
		}
		return false;
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {

		KVPair newKVPair = new KVPair(key, value);
		if (myRoot == null) {
			myRoot = new TreeNode(newKVPair);
			size++;
			return null;
		}
		TreeNode insertNode = findPosition(key);

		if (containsKey(key)) {
			V temp = ((MyTreeMap<K, V>.KVPair) insertNode.myItem).getValue();
			insertNode.myItem = newKVPair;
			return temp;
		} else { // if no existing key
			size++;
			add(newKVPair);
			return null;
		}

	}

	public TreeNode findPosition(K key) {
		if (this != null) {
			return posHelper(myRoot, key);
		}
		return null;
	}

	public TreeNode posHelper(TreeNode t, K key) {
		if (t == null) { // if you get to an empty node return the node.
			return t;
		}
		if (((MyTreeMap<K, V>.KVPair) t.myItem).getKey().compareTo(key) < 0) {
			return posHelper(t.myRight, key);
		} else if (((MyTreeMap<K, V>.KVPair) t.myItem).getKey().compareTo(key) > 0) {
			return posHelper(t.myLeft, key);
		} else { // if myItem == key
			return t;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (!containsKey(key)) {
			return null;
		}
		TreeNode getPosition = findPosition(key);
		V temp = get(key);

		if (getPosition.myLeft != null) {
			if (getPosition.myRight != null) {
				TreeNode temp2 = getPosition.myRight;
				getPosition = getPosition.myLeft;
				getPosition.myRight = temp2;
			} else {
				getPosition = getPosition.myLeft;
			}

			// set the right node of
		} else if (getPosition.myRight != null) {

			if (getPosition.myLeft != null) {
				TreeNode temp2 = getPosition.myRight;
				getPosition = getPosition.myLeft;
				getPosition.myRight = temp2;
			} else {
				getPosition = getPosition.myRight;
			}

		} else {
			setNull(key);

		}
		size--;
		return temp;

	}

	public void setNull(K key) {
		TreeNode temp = myRoot;
		while (((MyTreeMap<K, V>.KVPair) myRoot.myItem).getKey().compareTo(key) != 0) {
			if (((MyTreeMap<K, V>.KVPair) myRoot.myItem).getKey().compareTo(key) > 0) {
				myRoot = myRoot.myLeft;
			} else {
				myRoot = myRoot.myRight;
			}

		}
		myRoot = null;
		myRoot = temp;

	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		TreeNode getPosition = findPosition(key);
		return ((MyTreeMap<K, V>.KVPair) getPosition.myItem).getValue();
	}

	/**
	 * A class that can store a key and a value together. You can modify this
	 * class however you want.
	 */
	private class KVPair implements Comparable {
		private K key;
		private V value;

		public int compareTo(KVPair other) {
			if (key.equals(other.getKey())) {
				return 0;

			} else if (key.compareTo(other.getKey()) < 0) {
				return -1;
			} else {
				return 1;

			}
		}

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
		public int compareTo(Object o) { // should compare to another KVPair
			if (key.equals(((MyTreeMap<K, V>.KVPair) o).getKey())) {
				return 0;

			} else if (key.compareTo(((MyTreeMap<K, V>.KVPair) o).getKey()) < 0) {
				return -1;
			} else {
				return 1;

			}
		}
	}
}
