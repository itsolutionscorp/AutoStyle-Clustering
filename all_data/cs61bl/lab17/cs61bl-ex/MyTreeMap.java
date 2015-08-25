/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	KVPair myItem;
	MyTreeMap myLeft;
	MyTreeMap myRight;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myItem = null;
		myLeft = null;
		myRight = null;
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
		if (key.equals(myItem.key)) {
			return true;
		} else {
			boolean b = false, b2 = false;
			if (myLeft != null)
				b = myLeft.containsKey(key);
			if (myRight != null)
				b2 = myRight.containsKey(key);
			return (b2 || b);
		}
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		V temp = null;
		size++;
		if (myItem == null) {
			this.myItem = new KVPair(key, value);
		} else if (key.compareTo(this.myItem.key) == 0) {
			temp = this.myItem.value;
			this.myItem = new KVPair(key, value);
		} else if (key.compareTo(this.myItem.key) < 0) {
			if (myLeft == null) {
				myLeft = new MyTreeMap();
			}
			this.myLeft.put(key, value);
		} else {
			if (myRight == null) {
				myRight = new MyTreeMap();
			}
			this.myRight.put(key, value);
		}
		return temp;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (!containsKey(key))
			return null;
		V val = null;
		MyTreeMap temp = this;
		MyTreeMap parent = null;
		while (temp.myItem.key != key) {
			if (key.compareTo((K) temp.myItem.key) < 0) {
				parent = temp;
				temp = temp.myLeft;
			} else if (key.compareTo((K) temp.myItem.key) > 0) {
				parent = temp;
				temp = temp.myRight;
			}
		}
		val = (V) temp.myItem.value;
		if (temp.myLeft == null && temp.myRight == null){
			parent.myRight = null;
			size--;
			return val;
		}
		MyTreeMap inOrderNext = temp;
		MyTreeMap inOrderNextParent = null;
		if (temp.myRight != null) {
			inOrderNextParent = inOrderNext;
			inOrderNext = inOrderNext.myRight;
			while (inOrderNext.myLeft != null) {
				inOrderNextParent = inOrderNext;
				inOrderNext = inOrderNext.myLeft;
			}
		} else {
			parent.myRight = temp.myLeft;
			size--;
			return val;
		}
		temp.myItem = inOrderNext.myItem;
		if (inOrderNextParent.myRight != null) {
			inOrderNextParent.myRight = inOrderNext.myRight;
		} else {
			inOrderNextParent.myLeft = inOrderNext.myLeft;
		}
		size--;
		return val;
	}

	private MyTreeMap removeHelper(MyTreeMap mtm) {
		if (mtm.myLeft == null)
			return mtm;
		if (mtm.myLeft != null && mtm.myLeft.myLeft == null) {
			MyTreeMap temp = mtm.myLeft;
			mtm.myLeft = null;
			return temp;
		}
		return removeHelper(mtm.myLeft);
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (!containsKey(key)) {
			return null;
		} else {
			if (myItem.key.compareTo(key) == 0) {
				return myItem.value;
			} else if (myItem.key.compareTo(key) > 0) {
				return (V) myLeft.get(key);
			} else {
				return (V) myRight.get(key);
			}
		}
	}

	/**
	 * A class that can store a key and a value together. You can modify this
	 * class however you want.
	 */
	public class KVPair {
		public K key;
		public V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
	}
}
