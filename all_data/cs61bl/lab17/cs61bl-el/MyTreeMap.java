/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here

	BinarySearchTree<KVPair> myTree;

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
		// TODO Complete this!
		KVPair tempKey = new KVPair(key, null);
		return myTree.contains(tempKey);
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
		if (containsKey(key)) {
			V rtn = get(key);
			remove(key); // CHANGED BY KELLY
			myTree.add(new KVPair(key, value));
			size++; // CHANGED BY KELLY
			return rtn;
		} else {
			size++;
			myTree.add(new KVPair(key, value));
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
		if (containsKey(key)) {
			V rtn = get(key);
			KVPair tempKey = new KVPair(key, null);
			myTree.remove(myTree.findEqual(tempKey));
			size--;
			return rtn;
		} else {
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (!containsKey(key)) {
			return null;
		}
		KVPair tempKey = new KVPair(key, null);
		V rtn = myTree.findEqual(tempKey).getValue();
		return rtn;
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
		public int compareTo(KVPair o) {
			// TODO Auto-generated method stub
			return key.compareTo((K) o.key);
		}

	}

	public static void main(String[] args) {
		MyTreeMap<Integer, String> test = new MyTreeMap<Integer, String>();
		// test.put(18, "eighteen");
		// System.out.println(test.get(18)); //eighteen
		//
		// test.put(1, "one");
		// test.put(18, "new eighteen");
		// System.out.println(test.get(18)); //new eighteen
		//
		// test.put(2, "two");
		// System.out.println(test.containsKey(1)); //true
		// System.out.println(test.containsKey(4)); //false
		//
		// System.out.println(test.size()); //3
		// test.remove(1);
		// System.out.println(test.size()); //2
		// System.out.println(test.get(2)); // two
		// System.out.println(test.get(1)); // null
		//
		// test.put(3, "three");
		//
		// System.out.println(test.get(3)); //three
		//

		test.put(4, "four");
		test.put(1, "one");
		test.put(3, "three");
		test.put(10, "ten");
		test.put(2, "two");
		test.put(7, "seven");
		test.put(6, "six");
		test.put(8, "eight");
		test.put(5, "five");
		test.put(9, "nine");

		System.out.println(test.containsKey(7));
		System.out.println(test.get(7));
		test.remove(7);
		System.out.println(test.containsKey(7));
		System.out.println(test.get(7));
		System.out.println(test.containsKey(6));
		System.out.println(test.get(6));
		System.out.println(test.containsKey(5));
		System.out.println(test.get(5));

	}

}
