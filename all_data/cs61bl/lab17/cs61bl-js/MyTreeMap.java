/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> extends BinarySearchTree {

	private int size; // the number of items that have been put into the map
	private BinaryTree <KVPair> myTreeMap;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		myTreeMap = new BinaryTree<KVPair>();
	}

	/**
	 * Returns the number of g put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return myTreeMap.myRoot.mySize;
	}

	/**
	 * Returns whether the map contains the given key.
	 */
	public boolean containsKey(K key) {
		// TODO Complete this!
	    if (myRoot == null) {
	        return false;
	    } else {
	    	return containsHelper(myRoot, key);
	    }
	}
	
    public boolean containsHelper (TreeNode t, K key) {
    	if (t == null) {
    		return false;
    	} else if (((KVPair)t.myItem).key.compareTo(key) == 0) {
	    	return true;
        } else if (((KVPair)t.myItem).key.compareTo(key) < 0) {
	        return containsHelper(t.myLeft, key);
	    } else {
	        return containsHelper(t.myRight, key);
        }
    }
    
	public V put(K key, V value) {
		// TODO Complete this!
		size++;
		KVPair kvp = new KVPair(key, value);
		return putHelper(myRoot, kvp);
	}
			
	private V putHelper(TreeNode t, KVPair kv) {
	    if (t == null) {
	        t = new TreeNode(kv);
	        return null;
	    } else if (kv.key.compareTo(((KVPair) t.myItem).key) == 0) {
	        t.myItem = kv;
	    	return ((KVPair) t.myItem).value;
	    } else if (kv.key.compareTo(((KVPair) t.myItem).key) < 0) {
	        putHelper(t.myLeft, kv);
	        t.mySize += 1;
	        return null;
	    } else {
	        putHelper(t.myRight, kv);
	        t.mySize += 1;
	        return null;
	    }
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (key == null) {
			
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (key == null) {
			return null;
		} else {
			return getHelper(myRoot, kv);
		}
	}
	

	public V getHelper(TreeNode t, KVPair kv) {
		if (t == null) {
			return null;
		} else if (kv.key.compareTo(((KVPair) t.myItem).key) == 0) {
			return ((KVPair) t.myItem).value;
		} else if (kv.key.compareTo(((KVPair) t.myItem).key) < 0) {
			return getHelper(t.myLeft, kv);
		} else {
			return getHelper(t.myRight, kv);
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
