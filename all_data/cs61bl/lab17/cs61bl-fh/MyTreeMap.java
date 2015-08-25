/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree tree;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		tree = new BinarySearchTree();
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
		if (tree.myRoot == null) {
			return false;
		}
		return contains(tree.myRoot, key);
	}

	private boolean contains(BinaryTree.TreeNode t, K key) {
		if (t == null || t.myItem == null)
			return false;
		if (((KVPair) t.myItem).k().compareTo(key) == 0)
			return true;
		else if (((KVPair) t.myItem).k().compareTo(key) < 0)
			return contains(t.myRight, key);
		else
			return contains(t.myLeft, key);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		if (tree.myRoot == null) {
			tree.myRoot = tree.new TreeNode(new KVPair(key, value));
			size++;
			return null;
		}
		return put(tree.myRoot, new KVPair(key, value));
	}

	private V put(BinaryTree.TreeNode t, KVPair newElement) {
		KVPair castedT = ((KVPair) t.myItem);
		if (newElement.k().compareTo(castedT.k()) == 0) {
			V temp = castedT.v();
			((KVPair) t.myItem).setValue(newElement.v());
			return temp;
		} else if (newElement.k().compareTo(castedT.k()) < 0) {
			if (t.myLeft == null) {
				t.myLeft = tree.new TreeNode((Object) newElement);
				size++;
				return null;
			}
			return put(t.myLeft, newElement);

		} else {
			if (t.myRight == null) {
				t.myRight = tree.new TreeNode((Object) newElement);
				size++;
				return null;
			}
			return put(t.myRight, newElement);
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (tree.myRoot == null) {
			return null;
		}
		return remove(tree.myRoot, key);
	}

	private V remove(BinaryTree.TreeNode t, K key) {
		if (t == null) {
			return null;
		}
		KVPair castedT = ((KVPair) t.myItem);

		if (key.compareTo(castedT.k()) == 0) {
			V temp = castedT.v();
			if (t.myLeft == null) {
				if (t.myRight == null) {
					t.myItem = null;
					size--;
					return temp;
				} else {
					t = t.myRight;
					size--;
					return temp;
				}
			} else if (t.myRight == null) {
				t = t.myLeft;
				size--;
				return temp;
			} else {
				BinaryTree.TreeNode nextNode = getSuc(t);
				t.myItem = nextNode.myItem;
				remove(nextNode, ((KVPair) nextNode.myItem).k());
				return temp;
			}
		} else if (key.compareTo(castedT.k()) < 0) {
			return remove(t.myLeft, key);
		} else {
			return remove(t.myRight, key);
		}
	}

	public BinaryTree.TreeNode getSuc(BinaryTree.TreeNode t) {
		if (t.myRight == null)
			return null;
		t = t.myRight;
		while (t.myLeft != null) {
			t = t.myLeft;
		}
		return t;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (tree.myRoot == null) {
			return null;
		}
		return get(tree.myRoot, key);
	}

	private V get(BinaryTree.TreeNode t, K key) {
		if (t.myItem == null)
			return null;
		KVPair castedT = ((KVPair) t.myItem);
		if (key.compareTo(castedT.k()) == 0) {
			return castedT.v();
		} else if (key.compareTo(castedT.k()) < 0) {
			if (t.myLeft == null) {
				return null;
			}
			return get(t.myLeft, key);

		} else {
			if (t.myRight == null) {
				return null;
			}
			return get(t.myRight, key);
		}
	}

	/**
	 * A class that can store a key and a value together. You can modify this
	 * class however you want.
	 */
	public class KVPair {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}

		public K k() {
			return key;
		}

		public V v() {
			return value;
		}
	}
}