

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> mytree;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		mytree = null;
	}
	
	public MyTreeMap(BinarySearchTree<KVPair> Tree) {
		mytree = Tree;
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
		for (KVPair pair: mytree) {
			if (pair.getKey() == key) {
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
		if (containsKey(key)) {
			for (KVPair pair : mytree) {
				if (pair.getKey() == key) {
					V temp = pair.getValue();
					pair.setValue(value);
					return temp;
				}
			}
		} else {
			mytree.add(new KVPair(key, value));
			size++;
			return null;
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
		if (containsKey(key)) {
			size--;
			return mytree.delete(mytree.myRoot, new KVPair(key, null)).myItem.getValue();
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		for (KVPair pair: mytree) {
			if (pair.getKey() == key) {
				return pair.getValue();
			}
		}
		return null;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable<KVPair>{
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

		public int compareTo(KVPair Pair) {
			// TODO Auto-generated method stub
			return key.compareTo(Pair.getKey());
		}
		
	}
}
