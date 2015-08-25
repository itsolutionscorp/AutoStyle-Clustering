/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable.
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
    private KVPair root;
    private V prevVal;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
        root = null;
        prevVal = null;
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
        return get(key) != null;
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
        root = put(root, key, value);
		return prevVal;
	}

    public KVPair put(KVPair x, K key, V value) {
        if (x == null) {
            size += 1;
            prevVal = null;
            return new KVPair(key, value);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            prevVal = x.value;
            x.value = value;
        }

        return x;
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
        return get(root, key);
	}

    public V get(KVPair x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

	/**
	 * A class that can store a key and a value together. You can modify this
     * class however you want.
	 */
	private class KVPair {
		private K key;
		private V value;
        private KVPair left, right;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
	}
}
