/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	BinarySearchTree t;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		size = 0;
		t = new BinarySearchTree();
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
		if (t != null) {
			return t.contains(new KVPair(key, null));
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
		// TODO Complete this!
		if (t != null) {
			Comparable replacedPair = t.add(new KVPair(key, value));
			if (replacedPair != null) {
				KVPair pair = (KVPair) replacedPair;
				return pair.getValue();
			}
			size++;
		}
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (t == null || !t.contains(new KVPair(key, null))) {
			return null;
		}
		size--;
		Comparable removed = t.remove(new KVPair(key, null));
		if (removed != null) {
			KVPair pair = (KVPair) removed;
			return pair.getValue();
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (t == null) {
			return null;
		} else if (!t.contains(new KVPair(key, null))) {
			return null;
		} else {
			KVPair pair = (KVPair) t.get(new KVPair(key, null));
			return pair.getValue();
		}

	}

	/**
	 * A class that can store a key and a value together. You can modify this
	 * class however you want.
	 */
	private class KVPair implements Comparable {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}

		public int compareTo(Object o) {
			KVPair p = (KVPair) o;
			return key.compareTo(p.getKey());
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
	}

	/*public static void main(String args[]) {
		MyTreeMap map = new MyTreeMap();
		map.put(new Integer(6), "a");
		map.put(new Integer(4), "b");
		map.put(new Integer(5), "c");
		map.put(new Integer(2), "d");
		map.put(new Integer(8), "b");
		map.put(new Integer(10), "f");
		map.put(new Integer(9), "w");
		System.out.println(map.containsKey(new Integer(9)));
		System.out.println(map.put(new Integer(10), "l"));
		System.out.println(map.put(new Integer(890), "p"));
		System.out.println(map.put(new Integer(5), "c"));
		System.out.print(map.get(new Integer(80)));
		System.out.println(map.get(new Integer(9)));
		System.out.println("remove " + map.remove(new Integer(90)));
		System.out.println(map.containsKey(new Integer(4)));
		System.out.println(map.get(new Integer(5)));
		System.out.println(map.size());
		System.out.println(map.get(new Integer(2)));
		System.out.println(map.get(new Integer(6)));
		System.out.println(map.get(new Integer(10)));

	}*/
}
