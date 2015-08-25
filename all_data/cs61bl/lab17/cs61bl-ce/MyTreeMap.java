/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	K key;
	V value;
	int height;
	MyTreeMap<K,V> left,right;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		this.size = 0;
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
		return (this.get(key)!= null);
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
		if(this.containsKey(key)) {
	        if(this.key.compareTo(key) == 0) {
	            V temp = this.value;
	            this.value = value;
	            return temp;
	        }
		}
		this.size++;
		this.height++;
		this.key = key;
		this.value = value;
		return value;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (this.containsKey(key)) {
			V temp = this.value;
			this.value = null;
			this.key = null;
			size--;
			return temp;
		} else
			return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
	    if(this.key.compareTo(key) == 0)
	        return this.value;
	    else if(this.key.compareTo(key) > 0)
	        return this.left.get(key);
	    else
	        return this.right.get(key);
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
		
		public boolean contains(V val) {
			return (value == val);
		}
	}
}
