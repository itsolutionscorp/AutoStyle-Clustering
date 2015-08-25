/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here

		private static final int DEFAULT_CAPACITY = 30;

		/* Default load factor if not set in constructor */
		private static final double DEFAULT_LOAD_FACTOR = 0.7;
		
		private BinarySearchTree<KVPair<K, V>> myBST;
		
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		size = 0;
		myBST = new BinarySearchTree<KVPair<K,V>>();
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
		if (myBST.myRoot ==null){
			return false;
		}
		return containsKeyHelper(myBST.myRoot, key);
	}
	
	public boolean containsKeyHelper(BinaryTree<KVPair<K, V>>.TreeNode t, K key) {
		if (t == null) return false;
		int compared = t.myItem.getKey().compareTo(key);
		if (compared == 0) {
			return true;
		}
		else if (compared == -1) {
			return containsKeyHelper(t.myRight, key);
		}
		else return containsKeyHelper(t.myLeft, key);
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
		V result = get(key);
		KVPair<K, V> pair = new KVPair<K, V>(key, value);
		if (result==null){
			size++;
			myBST.add(pair);
			return null;
		} else {
			KVPair<K, V> pairToRemove = new KVPair<K, V>(key, result);
			myBST.remove(pairToRemove);
			myBST.add(pair);
			return result;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		V result = null;
		if (containsKey(key)){
			size--;
			result = get(key);
			
			KVPair<K, V> temp = new KVPair<K, V>(key, result);
			
			myBST.remove(new KVPair<K, V>(key, result));
		}
		return result;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (myBST.myRoot == null) return null;
		else return getHelper(myBST.myRoot, key);
	}
	
	public V getHelper(BinaryTree<KVPair<K, V>>.TreeNode t, K key) {
		if (t == null) return null;
		int compared = t.myItem.getKey().compareTo(key);
		if (compared == 0) {
			return t.myItem.getValue();
		}
		else if (compared == -1) {
			return getHelper(t.myRight, key);
		}
		else return getHelper(t.myLeft, key);
	}
	
	

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair<L extends Comparable<L>,R> implements Comparable<KVPair<L, R>>{
		private L key;
		private R value;

		public KVPair(L k, R v) {
			key = k;
			value = v;
		}

		public void setValue(R v) {
			value = v;
		}
		
		public int compareTo(KVPair<L, R> t) {
			return key.compareTo(t.getKey());
		}
		
		public L getKey() {
			return key;
		}
		
		public R getValue() {
			return value;
		}
	}
}
