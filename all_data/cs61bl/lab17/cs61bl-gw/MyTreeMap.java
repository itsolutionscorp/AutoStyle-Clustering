

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	BinarySearchTree<KVPair> bst;
	
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
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
		if (bst.myRoot == null){
			return false;
		} else {
			if (bst.myRoot.myItem.key == key){
				return true;
			} else {
				if (key < bst.myRoot.myItem.key){
					return bst.myRoot.myLeft.containsKey(key);
				}
			}
		}
		return containsKeyHelper(bst.myRoot, key);
	}
	
//	public boolean containsKeyHelper(BinarySearchTree<KVPair> tree, K key) {
//		if (tree.myRoot == null){
//			return false;
//		} else {
//			if (tree.myRoot.myItem.key == key) {
//				return true;
//			} else {
//				boolean keyRight = false;
//				boolean keyLeft = false;
//				if (tree.myRoot.myLeft.myItem.key.equals(key)){
//					return true;
//				} else {
//					keyLeft = 
//				if (tree.myRoot.myRight.myItem.key.equals(key)){
//					return true;
//				} else {
//					BinarySearchTree<KVPair> t = new BinarySearchTree<KVPair>(tree.myRoot.myRight);
//					keyRight = containsKeyHelper(tree.myRoot.myRight, key);
//				}
//
//				} else {
//					return false;
//				}
//				return keyRight||keyLeft;
//			}
//		}
//	}
	
	

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		// TODO Complete this!
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
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
		
		public int compareTo (KVPair a){
			return (key.compareTo(a.key));
		}
		
	}
}
