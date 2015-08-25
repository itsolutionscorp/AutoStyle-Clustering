/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	BinarySearchTree groot;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		groot = new BinarySearchTree();
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
		return containsKeyHelper(groot.myRoot, key);
	}
	
	private boolean containsKeyHelper(BinaryTree.TreeNode t, K key){
		if(t==null)
			return false;
		else if(((KVPair)(t.myItem)).getKey().equals(key))
			return true;
		else if(((KVPair)(t.myItem)).getKey().compareTo(key)<1)
			return containsKeyHelper(t.myRight, key);
		else
			return containsKeyHelper(t.myLeft, key);
	}
	
	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		KVPair node = new KVPair(key, value);
		if(!containsKey(key)){
			size++;
			groot.add(node);
			return null;
		}
		else{
			V removed = remove(key);
			groot.add(node);
			return removed;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		KVPair toDelete = new KVPair(key, get(key));
		return ((KVPair) groot.remove(toDelete)).getValue();
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {//In this case, key is a string. Probably.
		if (this.containsKey(key)) {
			KVPair toGet = new KVPair(key, null);
			return ((KVPair) groot.get(toGet)).getValue();
		}
		return null;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	protected class KVPair implements Comparable{ 
		public K key;
		public V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
			
		public void setValue(V v) {
			value = v;
		}
		
		@Override
		public int compareTo(Object o) {
			KVPair k = (KVPair) o;
			if (this.equals(k)) {
				return 0;
			}
			else if (key.compareTo(k.getKey())<1) {
				return -1;
			}
			return 1;
		}
		
//		public boolean equals(K key) {
//			if (this.key.equals(key)) {
//				return true;
//			}
//			return false;
//		}
		
		public boolean equals(KVPair pair) {
			if (this.key.equals(pair.getKey())) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public String toString() {
			return "(" + key + ", " + value + ")";
		}
	}
}


