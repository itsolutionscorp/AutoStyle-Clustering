import java.util.Iterator;

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> map;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		map = new BinarySearchTree<KVPair>();
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
		Iterator<MyTreeMap<K, V>.KVPair> iter = map.iterator();
		while (iter.hasNext()) {
			if (iter.next().key == key) {
				return true;
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
		// TODO Complete this!
		V oldvalue = null;
		Iterator<MyTreeMap<K, V>.KVPair> iter = map.iterator();
		boolean found = false;
		while (iter.hasNext()) {
			KVPair old = iter.next();
			if (old.key == key) {
				found = true;
				oldvalue = old.value;
				old.setValue(value);
			}
		}
		KVPair newKV = new KVPair(key, value);
		if (!found) {
			map.add(newKV);
			size++;
		}
		return oldvalue;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		Iterator<MyTreeMap<K, V>.KVPair> iter = map.iterator();
		while (iter.hasNext()) {
			KVPair found = iter.next();
			if (found.key == key) {
				V old = found.value;
				map.remove(found);
				size--;
				return old;
			}
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		Iterator<MyTreeMap<K, V>.KVPair> iter = map.iterator();
		while (iter.hasNext()) {
			KVPair found = iter.next();
			if (found.key == key) {
				return found.value;
			}
		}
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

		@Override
		public int compareTo(KVPair o) {
			return key.compareTo(o.key);
		}

	}
	
	public static void main(String[] args) {
		MyTreeMap<Integer, String> test = new MyTreeMap<Integer, String>();
		test.put(6, "six");
		test.put(1, "one");
		test.put(2, "two");
		test.put(9, "nine");
		System.out.println(test.get(2));
		
	}
}
