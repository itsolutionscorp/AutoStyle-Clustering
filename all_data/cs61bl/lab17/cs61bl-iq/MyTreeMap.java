import java.util.ArrayList;

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree<K> myTree;
	private ArrayList<Object> l; 

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		this.size = 0;
		this.myTree = new BinarySearchTree<K>();
		this.l = new ArrayList<Object>();
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
		return myTree.contains(key);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		if (myTree.contains(key)) {
			int index = l.indexOf(key) + 1;
			V val = (V) l.get(index);
			l.set(index, value);
			return val;
		} else {
			myTree.add(key);
			l.add(key);
			l.add(value);
			this.size++;
			return null;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (myTree.contains(key)) {
			myTree.remove(key);
			int index = l.indexOf(key);
			V val = (V) l.get(index + 1);
			l.remove(index);
			l.remove(index);
			this.size--;
			return val;
		} else {
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (myTree.contains(key)) {
			int index = l.indexOf(key) + 1;
			V val = (V) l.get(index);
			return val;
		} else {
			return null;
		}
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
	}
}
