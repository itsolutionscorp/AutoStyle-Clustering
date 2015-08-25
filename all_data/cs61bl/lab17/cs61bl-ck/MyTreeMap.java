
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> /* extends BinaryTree */{

	private int size; // the number of items that have been put into the map
	
	// TODO You may declare new instance variables here
	private TreeNode rootNode;
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		rootNode = null;
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
		return containsKey(rootNode, key) != null;
	}
	
	public V containsKey(TreeNode t, K key) {
		if (t == null) {
			return null;
		}
		if (t.myItem.getKey().compareTo(key) == 0) {
			return t.myItem.getValue();
		} else if (t.myItem.getKey().compareTo(key) < 0) {
			return containsKey(t.myRight, key);
		} else {
			return containsKey(t.myLeft, key);
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
		// TODO Complete this!
		V valueToReturn = null;
		if (containsKey(key)) {
			valueToReturn = get(key);
			size --;
		}
		rootNode = addNode(rootNode, key, value);
		size ++;
		return valueToReturn;
	}
	
	public TreeNode addNode(TreeNode t, K key, V value) {
		KVPair p = new KVPair(key, value);
		if (t == null) {
			return new TreeNode(p);
		} else if (t.myItem.getKey().compareTo(key) > 0) {
			t.myLeft = addNode(t.myLeft, key, value);
		} else if (t.myItem.getKey().compareTo(key) < 0) {
			t.myRight = addNode(t.myRight, key, value);
		} else {
			t.myItem = p;
		}
		return t;
	}
	
	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (containsKey(key)) {
			V ValueToReturn = get(key);
			rootNode = remove(rootNode, key);
			size --;
			return ValueToReturn;
		} else {
			System.out.println("Dose not contain this key.");
			return null;
		}
	}
	
	public TreeNode remove(TreeNode t, K key) {
		if (t.myItem.getKey().compareTo(key) == 0)  {
			if (t.myLeft == null && t.myRight == null) {
				return null;
			} else if (t.myLeft != null && t.myRight == null) {
				return t.myLeft;
			} else if (t.myLeft == null && t.myRight != null) {
				return t.myRight;
			}	
			TreeNode temp = t;
			temp = temp.myRight;
			while (temp.myLeft != null) {
				temp = temp.myLeft;
			}
			t.myItem = temp.myItem;
			t.myRight = remove(t.myRight, t.myItem.getKey());
			return t;
		} else if (key.compareTo(t.myItem.getKey()) < 0) {
			t.myLeft = remove(t.myLeft, key);
			return t;
		} else if (key.compareTo(t.myItem.getKey()) > 0) {
			t.myRight = remove(t.myRight, key);
			return t;
		}
		return null;
	}
	
	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (!containsKey(key)) {
			System.out.println("Dose not contain this key.");
			return null;
		}
		TreeNode target = get(rootNode, key);
		return target.myItem.value;
	}
	
	public TreeNode get(TreeNode t, K key) {
		if (t == null) {
			return null;
		} else if (t.myItem.getKey().compareTo(key) == 0) {
			return t;
		} else if (t.myItem.getKey().compareTo(key) < 0) {
			return get(t.myRight, key);
		} else if (t.myItem.getKey().compareTo(key) > 0) {
			return get(t.myLeft, key);
		}
		return null;
	}

	public class TreeNode {
		KVPair myItem;
		TreeNode myLeft;
		TreeNode myRight;

		public TreeNode(KVPair p) {
			myItem = p;
			myLeft = null;
			myRight = null;
		}

		public TreeNode(KVPair p, TreeNode left, TreeNode right) {
			myItem = p;
			myLeft = left;
			myRight = right;
		}

		public int size() {
			if (myLeft == null && myRight == null) {
				return 1;
			}
			if (myLeft != null && myRight == null) {
				return 1 + myLeft.size();
			}
			if (myRight != null && myLeft == null) {
				return 1 + myRight.size();
			}
			return 1 + myRight.size() + myLeft.size();
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
		
		public V getValue() {
			return value;
		}
		
		public K getKey() {
			return key;
		}
	}
	
	public static void main(String[] args) {
//		 MyTreeMap test = new  MyTreeMap();
//		 test.put("Jiahao", 1);
//		 test.put("suyang", 2);
//		 test.put("yuqiao", 3);
//		 test.put("tianhe", 4);
//		 test.put("ningyuan", 5);
//		 test.put("ningyuan", 55);
//		 System.out.println(test.get("ningyuan"));
//		 System.out.println(test.get("Jiahao"));
//		 System.out.println(test.get("suyang"));
//		 System.out.println(test.get("yuqiao"));
//		 System.out.println("size : " + test.size);
//		 System.out.println("remove Jiahao : " + test.remove("Jiahao"));
//		 System.out.println(test.remove("suyang"));
//		 System.out.println("size : " + test.size);
//		 System.out.println("remove yuqiao : " + test.remove("yuqiao"));
//		 System.out.println("size : " + test.size);
//		 System.out.println(test.rootNode.size());
//		 System.out.println(test.containsKey("ningyuan"));
//		 System.out.println("remove tianhe : " + test.remove("tianhe"));
//		 System.out.println("size : " + test.size);
//		 System.out.println(test.containsKey("ningyuan"));
//		 System.out.println(test.containsKey("Tianhe"));
//		 System.out.println(test.containsKey("Jiahao"));
//		 System.out.println(test.containsKey("suyang"));
//		 System.out.println(test.containsKey("yuqiao"));
//		 System.out.println("remove ningyuan : " + test.remove("ningyuan"));
//		 System.out.println("size : " + test.size);

	}
}
