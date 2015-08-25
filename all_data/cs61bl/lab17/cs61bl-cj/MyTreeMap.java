
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private TreeNode root;
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		size = 0;
		root = null;
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
		return containsKey(root, key);
	}
	
	private boolean containsKey(TreeNode t, K key) {
		if (t == null) {
			return false;
		}
		if (t.myItem.key.equals(key)) {
			return true;
		} else if (t.myItem.key.compareTo(key) < 0) {
			return containsKey(t.myRight, key);
		} else {
			return containsKey(t.myLeft, key);
		}
	}

	private class OldValueBox {
		V oldValue;
	}
	
	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		OldValueBox ov = new OldValueBox();
		ov.oldValue = null;
		root = put(root, key, value, ov);
		return ov.oldValue;
	}
	
	private TreeNode put(TreeNode t, K key, V value, OldValueBox ov) {
		KVPair kv = new KVPair(key, value);
		if (t == null) {
			return new TreeNode(kv);
		} else if (t.myItem.key.compareTo(key) < 0) {
			t.myRight = put(t.myRight, key, value, ov);
			t.subtreeSize++;
		} else if (t.myItem.key.compareTo(key) > 0) {
			t.myLeft = put(t.myLeft, key, value, ov);
			t.subtreeSize++;
		} else {
			ov.oldValue = t.myItem.value;
			t.myItem = kv;
		}
		return t;
	}
	
	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (containsKey(key)) {
			V temp = get(key);
			root = remove(root, key);
			return temp;
		}
		return null;
	}
	
	private TreeNode remove(TreeNode t, K key) {
		// found => remove
		if (t.myItem.key.equals(key))  {
			if (t.myLeft == null && t.myRight == null) {
				return null;
			} 
			if (t.myLeft == null && t.myRight != null) {
				return t.myRight;
			}
			if (t.myLeft != null && t.myRight == null) {
				return t.myLeft;
			}			
			t.myItem = findInorderSuccessor(t).myItem;
			t.myRight = remove(t.myRight, t.myItem.key);
			return t;
		}
		// not found yet => try to find
		if (key.compareTo(t.myItem.key) < 0) {
			t.myLeft = remove(t.myLeft, key);
			return t;
		} else {
			t.myRight = remove(t.myRight, key);
			return t;
		}
	}
	
	// precondition: 
	private TreeNode findInorderSuccessor(TreeNode t) {
		t = t.myRight;
		while (t.myLeft != null) {
			t = t.myLeft;
		}
		return t;
	}
	
	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		TreeNode temp = get(root, key);
		if (temp == null) {
			return null;
		}
		return temp.myItem.value;
	}
	
	private TreeNode get(TreeNode t, K key) {
		if (t == null) {
			return null;
		} else if (t.myItem.key.compareTo(key) < 0) {
			return get(t.myRight, key);
		} else if (t.myItem.key.compareTo(key) > 0) {
			return get(t.myLeft, key);
		} else {
			return t;
		}
		
	}
	
	public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

	private class TreeNode {
		private KVPair myItem;
		private TreeNode myLeft;
		private TreeNode myRight;
		private int subtreeSize;

		public TreeNode(KVPair item) {
			this(item, null, null);
		}

		public TreeNode(KVPair item, TreeNode left, TreeNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
			subtreeSize = 1;
			if (myLeft != null) {
				subtreeSize += myLeft.subtreeSize;
			}
			if (myRight != null) {
				subtreeSize += myRight.subtreeSize;
			}
		}
		
		private void printPreorder() {
            System.out.print(myItem.value + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }

        private void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem.value + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
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

		@Override
		public int compareTo(Object o) {
			return (key.compareTo(((KVPair)o).key));
		}
	}
	
	public static void main(String[] args) {
		MyTreeMap<String, String> tm = new MyTreeMap<String, String>();
		tm.put("C", "3");
		tm.put("A", "1");
		tm.put("B", "2");
		tm.put("E", "5");
		tm.put("D", "4");
		tm.put("O", "9");
		tm.put("W", "11");
		System.out.println(tm.put("B", "22"));
		System.out.println(tm.get("C"));
		System.out.println(tm.containsKey("B"));
		tm.printInorder();
//		tm.remove("E");
		System.out.println(tm.remove("W"));
		System.out.println(tm.remove("B"));
		System.out.println(tm.remove("Z"));
		
		tm.printInorder();
	}
}