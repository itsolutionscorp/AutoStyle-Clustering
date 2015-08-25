/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> foundation;
		
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		foundation = new BinarySearchTree<KVPair>();
		foundation.myRoot = null;
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
		KVPair testPair = new KVPair(key, null);
		return foundation.contains(testPair);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		V prev = null;
		if (containsKey(key)) {
			prev = foundation.foundKV.value;
			foundation.foundKV.value = value;
			return prev;
		}
		KVPair toAdd = new KVPair(key, value);
		foundation.add(toAdd);
		size++;
		return prev;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V prev = null;
		if (containsKey(key)) {
			prev = foundation.foundKV.value;
			if (foundation.succNode == null) {
				if (foundation.parent.myLeft == foundation.foundNode) {
					foundation.parent.myLeft = null;
				} else {
					foundation.parent.myRight = null;
				}
			} else {
				if (foundation.parent == null) {
					foundation.myRoot.myItem = foundation.succNode.myItem;
					if (foundation.succParent.myLeft == foundation.succNode) {
						foundation.succParent.myLeft = null;
					} else {
						foundation.succParent.myRight = null;
					}
				} else {
					foundation.myRoot.myItem = foundation.succNode.myItem;
					if (foundation.succParent.myLeft == foundation.succNode) {
						foundation.succParent.myLeft = null;
					} else {
						foundation.succParent.myRight = null;
					}
				}
				
			}
			size--;
		}
		return prev;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (containsKey(key)) {
			return foundation.foundKV.value;
		}
		return null;
	}

	public static void main(String[] args) {
		MyTreeMap<Integer, String> bob = new MyTreeMap<Integer, String>();
		bob.put(4, "D");
		bob.put(2, "B");
		bob.put(8, "H");
		bob.put(1, "D");
		bob.put(3, "B");
		bob.put(6, "H");
		bob.remove(2);
		System.out.println(bob.containsKey(2));
	}
	
	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	public class KVPair implements Comparable<KVPair> {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
		
		public int compareTo(KVPair c) {
			if (key.compareTo(c.key) == 0) {
				return 0;
			} else if (key.compareTo(c.key) < 0) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
