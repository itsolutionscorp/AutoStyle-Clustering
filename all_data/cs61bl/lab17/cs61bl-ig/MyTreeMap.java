

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	BinarySearchTree<KVPair> tree;
	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		tree = new BinarySearchTree<KVPair>();
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
		return tree.contains(new KVPair(key, null));
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		
		KVPair pair = new KVPair(key, value);
		V val = null;
		if(containsKey(key)) {
			val = get(key);
		} else {
			size++;
		}
		
		tree.add(pair);
		return val;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V result = null;
		if(containsKey(key)) {
			result = get(key);
			tree.remove(new KVPair(key, null));
			size--;
		}
		return result;
		
	}
	

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if(!containsKey(key)) return null;
		KVPair result = tree.find(new KVPair(key, null));
		return result.value;
	}
	
	public static void main(String[] args) {
		MyTreeMap<Integer, Integer> map = new MyTreeMap<Integer, Integer>();
//		map.put(3, 5);
		System.out.println(map.containsKey(3));
		map.put(4, 5);
		map.put(5, 5);
		map.put(6, 5);
		map.put(7, 5);
		map.put(2, 2);
		map.put(3, 3);
		map.put(1, 4);
		System.out.println(map.containsKey(5));
		map.remove(5);
		System.out.println(map.remove(7));
		System.out.println(map.remove(1));
		System.out.println(map.remove(2));
		map.tree.print();
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

		@Override
		public int compareTo(KVPair pair2) {
			return key.compareTo(pair2.key);
		}
		
	}
}
