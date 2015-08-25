/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	public int size; // the number of items that have been put into the map
	public KVPair myRoot;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		size = 0;
		myRoot = null;
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
		return myRoot.contains(key);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		if (myRoot == null) {
			myRoot = new KVPair(key, value, null, null);
			size++;
			return value;
		} else if (!myRoot.contains(key)) {
			myRoot.add(myRoot, key, value);
			size++;
			return value;
		} else {
			KVPair pair = myRoot.getHelper(key);
			V result = pair.myValue;
			pair.myValue = value;
			return result;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		size--;
		KVPair toRemove = myRoot.getHelper(key);
		if (myRoot == null) {
			size++;
			return null;
		}
		V result = toRemove.myValue;
		if (toRemove.myLeft == null && toRemove.myRight == null) { //leaf
			if (toRemove.myParent == null) {
				myRoot = null;
			} else {
				if (toRemove == toRemove.myParent.myLeft) {
					toRemove.myParent.myLeft = null;
				} else {
					toRemove.myParent.myRight = null;
				}
			}
		} else if (toRemove.myLeft == null){ //one subtree in right
			if (toRemove == toRemove.myParent.myLeft) {
				toRemove.myParent.myLeft = toRemove.myRight;
			} else {
				toRemove.myParent.myRight = toRemove.myRight;
			}
			toRemove.myRight.myParent = toRemove.myParent;
		} else if (toRemove.myRight == null) { //one subtree in left
			if (toRemove == toRemove.myParent.myLeft) {
				toRemove.myParent.myLeft = toRemove.myLeft;
			} else {
				toRemove.myParent.myRight = toRemove.myLeft;
			}
			toRemove.myLeft.myParent = toRemove.myParent;
		} else { //3rd case, two subtrees
			KVPair succ = toRemove.inorderSuccessor();
			toRemove.myKey = succ.myKey;
			toRemove.myValue = succ.myValue;
			if (succ == succ.myParent.myLeft) {
				succ.myParent.myLeft = null;
			} else {
				succ.myParent.myRight = null;
			}
		}
		return result;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		return myRoot.getHelper(key).myValue;
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	public class KVPair {
		public K myKey;
		public V myValue;
		public KVPair myLeft;
		public KVPair myRight;
		public KVPair myParent;

		public KVPair(K k, V v, KVPair left, KVPair right) {
			myKey = k;
			myValue = v;
			this.myLeft = left;
			this.myRight = right;
		}
		
		public boolean contains(K key) {
			if (myKey.equals(key)) {
				return true;
			} else if (myLeft != null && myKey.compareTo(key) < 0) {
				return myLeft.contains(key);
			} else if (myRight != null && myKey.compareTo(key) > 0) {
				return myRight.contains(key);
			} else {
				return false;
			}
		}
		
		public KVPair getHelper(K key) {
			if (myKey.equals(key)) {
				return this;
			} else if (myLeft != null && myKey.compareTo(key) > 0) {
				return myLeft.getHelper(key);
			} else if (myRight != null && myKey.compareTo(key) < 0) {
				return myRight.getHelper(key);
			}
			return null;
		}
		
		public KVPair inorderSuccessor() {
			if (myRight != null) {
				KVPair temp = myRight;
				while (temp.myLeft != null) {
					temp = temp.myLeft;
				}
				return temp;
			} else {
				KVPair temp = this;
				while (temp != temp.myParent.myLeft) {
					temp = temp.myParent;
				}
				return temp.myParent;
			}
		}
		
		public void add(KVPair parent, K key, V value) {
			if (parent.myRight != null && parent.myKey.compareTo(key) < 0) {
				add(parent.myRight, key, value);
			} else if (parent.myLeft != null && parent.myKey.compareTo(key) > 0) {
				add(parent.myLeft, key, value);
			} else if (parent.myLeft == null && parent.myKey.compareTo(key) > 0) {
				KVPair result = new KVPair(key, value, null, null);
				result.myParent = parent;
				parent.myLeft = result;
			} else if (parent.myRight == null && parent.myKey.compareTo(key) < 0) {
				KVPair result = new KVPair(key, value, null, null);
				result.myParent = parent;
				parent.myRight = result;
			}
		}
	
		@Override
		public String toString() {
			return "key: " + myKey.toString() + " value: " + myValue.toString();
		}
	}
}

