import java.util.Iterator;




/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
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
		return containsHelper(myTree, key);
	}
	
	private boolean containsHelper(BinarySearchTree<KVPair> tree, K key) {
		if (tree.myRoot==null) {
			return false;
		} else {
			if (key.equals(tree.myRoot.myItem.key)) {
				return true;
			}
			boolean leftContains;
			boolean rightContains;
			BinarySearchTree<KVPair> left = new BinarySearchTree<KVPair>(tree.myRoot.myLeft);
			leftContains = containsHelper(left, key);
			BinarySearchTree<KVPair> right = new BinarySearchTree<KVPair>(tree.myRoot.myRight);
			rightContains = containsHelper(right, key);
			return leftContains||rightContains;
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
		KVPair temp = new KVPair(key, value);
		if (containsKey(key)) {
			V old = remove(key);
			myTree.add(temp);
			size++;
			return old;
		} else {
			myTree.add(temp);
			size++;
			return null;
		}
	}
	
	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		KVPair a = getHelper(myTree,key);
		if (a==null) {
			return null;
		}
		V result = a.value;
		myTree.delete(a);
		size--;
		return result;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		KVPair result = getHelper(myTree, key);
		if (result == null) {
			return null;
		}
		return getHelper(myTree, key).value;
	}
	
	public KVPair getHelper(BinarySearchTree<KVPair> a, K key){
		if (a.myRoot==null) {
			return null;
		}else if (a.myRoot.myItem.key.equals(key)) {
			return a.myRoot.myItem;
		} else if (a.myRoot.myItem.key.compareTo(key)<0){
			BinarySearchTree<KVPair> temp = new BinarySearchTree<KVPair>(a.myRoot.myRight);
			return getHelper(temp, key);
		} else {
			BinarySearchTree<KVPair> temp = new BinarySearchTree<KVPair>(a.myRoot.myLeft);
			return getHelper(temp, key);
		}
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
		
		public int compareTo(KVPair pair) {
			KVPair item = (KVPair) pair;
			return key.compareTo(item.key);
		}
	}
}
