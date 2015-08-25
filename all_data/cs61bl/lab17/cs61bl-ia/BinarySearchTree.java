





public class BinarySearchTree<K extends Comparable <K>> extends BinaryTree<K> {
//
//	public class KVPair {
//		private K key;
//		private V value;
//
//		public KVPair(K k, V v) {
//			key = k;
//			value = v;
//		}
//
//		public void setValue(V v) {
//			value = v;
//		}
//		public K getKey() {
//			return key;
//		}
//		public V getVal() {
//			return value;
//		}
//		public int compareTo(KVPair pair) {
//			return key.compareTo(pair.key);
//		}
//	}
//	public TreeNode putHelper(TreeNode t, K o) {
//		if (t == null) {
//			 return new TreeNode(o);
//		} else if (key.compareTo(t.myItem.getKey()) < 0) {
//			t.myLeft = putHelper(t.myLeft, key, value);
//		} else {
//			t.myRight = putHelper(t.myRight, key, value);
//		}
//	}
	public BinarySearchTree() {
		super();
//		head = null;
	}
	public BinarySearchTree(TreeNode t) {
		super(t);
//		head = null;
	}
	public boolean contains(K key) {
		return contains(myRoot, key);
	}
	public boolean contains(TreeNode t, K key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem) == 0) {
			return true;
		} else if (key.compareTo(t.myItem) < 0) {
			return contains(t.myLeft, key);
		} else {
			return contains(t.myRight, key);
		}
	}
    public void add(K key) {
        myRoot = add(myRoot, key);
    }
    


    private TreeNode add(TreeNode t, K key) {
        if (t == null) {
            return new TreeNode(key);
        } else if (key.compareTo(t.myItem) < 0) {
        
            t.myLeft = add(t.myLeft, key);
            return t;
        } else {
        	
            t.myRight = add(t.myRight, key);
            return t;
        }
    }
	
	public static void main (String args[]) {
		BinarySearchTree<String> b = new BinarySearchTree<String>();
		b.add("a");
		b.add("b");
		b.add("c");
		b.add("d");
		b.add("e");
		System.out.println(b.contains("g"));
		System.out.println(b.contains("c"));
		BinarySearchTree<String> b2 = new BinarySearchTree<String>();
		b.add("c");
		b.add("a");
		b.add("e");
		b.add("b");
		b.add("d");
		System.out.println(b.contains("g"));
		System.out.println(b.contains("d"));
		System.out.println(b.contains("a"));
		System.out.println(b.contains("e"));
		
	}

}
