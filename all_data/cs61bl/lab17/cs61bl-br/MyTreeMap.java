/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> extends BinarySearchTree {

	private int size; // the number of items that have been put into the map/

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		myRoot=null;
		size = 0;
	}

	public MyTreeMap(TreeNode t) {
		myRoot = t;
		size = 1;
	}

	public MyTreeMap(KVPair kv) {
		myRoot = new TreeNode(kv);
		size = 1;
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
		if(myRoot!=null) {
			return contains(myRoot, key);
		}
		return false;
	}
	
    private boolean contains (TreeNode t, K key) {
    	if(t==null) {
    		return false;
    	}
    	if(key.compareTo(((KVPair)t.myItem).key)== 0) {
    		return true;
    	}
    	else if (key.compareTo(((KVPair)t.myItem).key) < 0) {
    		return contains(t.myLeft, key);
    	}
    	else {
    		return contains(t.myRight, key);
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
		size++;
		V temp = this.get(key);
		if(myRoot!=null) {
			if(temp!=null) {
				myRoot = putExisting(myRoot, key, value);
			}
			else {
				myRoot = putNew(myRoot, key, value);
			}
		}
		else {
			myRoot = new TreeNode(new KVPair(key, value));
		}
		
		return temp;
	}

	private TreeNode putNew(TreeNode t, K key, V value) {
        if (t == null) {
            return new TreeNode(new KVPair(key, value));
        } else if (key.compareTo(((KVPair)t.myItem).key) < 0) {
            t.myLeft = putNew(t.myLeft, key, value);
            t.myLeft.size++;
            return t;
        } else {
            t.myRight = putNew(t.myRight, key, value);
            t.myRight.size++;
            return t;
        }
    }

	private TreeNode putExisting(TreeNode t, K key, V value) {
        if (((KVPair)t.myItem).key.equals(key)) {
            ((KVPair)t.myItem).value = value;
            return t;
        } else if (key.compareTo(((KVPair)t.myItem).key) < 0) {
            t.myLeft = putExisting(t.myLeft, key, value);
            t.myLeft.size++;
            return t;
        } else {
            t.myRight = putExisting(t.myRight, key, value);
            t.myRight.size++;
            return t;
        }
    }

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if(myRoot==null) {
			return null;
		}
		else if(this.containsKey(key)) {
			V rtn = this.get(key);
			myRoot = remove(myRoot, key);
			return rtn;
		}
		return null;
	}

	public TreeNode remove(TreeNode curr, K key) {
		if(((KVPair)curr.myItem).key.equals(key)) {
			if(curr.myLeft == null && curr.myRight == null) {
				return null;
			}
			else if (curr.myLeft !=null && curr.myRight != null) {
				curr.myItem = min(curr.myRight);
				return curr;
			}
			else {
				if(curr.myLeft == null) {
					return curr.myRight;
				}
				else {
					return curr.myLeft;
				}
			}
		}
		else if(key.compareTo(((KVPair)curr.myItem).key) < 0) {
			curr.myLeft = remove(curr.myLeft, key);
		}
		else {
			curr.myRight = remove(curr.myRight, key);
		}
	}

	private KVPair min(TreeNode t) {
		if(t.myLeft == null) {
			KVPair rtn = (KVPair)t.myItem;
			remove(((KVPair)t.myItem).key);
			return rtn;
		}
		else {
			return min(t.myLeft);
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if(myRoot!=null) {
			return getHelper(myRoot, key);
		}
		return null;
	}
	
    private V getHelper(TreeNode t, K key) {
    	if(t==null) {
    		return null;
    	}
    	if(key.compareTo(((KVPair)t.myItem).key) == 0) {
    		return ((KVPair)t.myItem).value;
    	}
    	else if (key.compareTo(((KVPair)t.myItem).key) < 0) {
    		return getHelper(t.myLeft, key);
    	}
    	else {
    		return getHelper(t.myRight, key);
    	}
    }

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	public class KVPair {
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
