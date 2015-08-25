import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> extends BinarySearchTree {

	private int size; // the number of items that have been put into the map
	private TreeNode myRoot;
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		super();
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
		return containsKeyHelper(myRoot, key);
	}
	
	public boolean contains(V value) {
		//TODO
		return containsHelper(myRoot, value);
	}
	
	public boolean containsHelper(TreeNode t, V value) {
		if (t == null) {
			return false;
		} else if (value.equals(t.myItem.value)) {
			return true;
		} else {
			return containsHelper(t.myRight, value) || containsHelper(t.myLeft, value);
		}
	}
	
	public boolean containsKeyHelper (TreeNode t, K key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem.key) == 0) {
			return true;
		} else if (key.compareTo(t.myItem.key) < 0) {
			return containsKeyHelper(t.myLeft, key);
		} else {
			return containsKeyHelper(t.myRight, key);
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
		if (containsKey(key)) {
			TreeNode t = getHelper(myRoot, key);
			V old = t.myItem.value;
			t.myItem.setValue(value);
			return old;
		} else {
			size++;
			KVPair key_value = new KVPair(key, value);
			add(key_value);
			return null;
		}
	}
	
	public void add(KVPair key_value) {
		myRoot = addHelper(myRoot, key_value);
	}

	private TreeNode addHelper(TreeNode t, KVPair key_value) {
	    if (t == null) {
	        return new TreeNode(key_value);
	    } else if (key_value.compareTo(t.myItem) < 0) {
	        t.myLeft = addHelper(t.myLeft, key_value);
	        t.myLeft.myParent = t;
	        return t;
	    } else {
	        t.myRight = addHelper(t.myRight, key_value);
	        t.myRight.myParent = t;
	        return t;
	    }
	}
	
	public TreeNode getHelper(TreeNode t, K key) {
		if (t.myItem.key.equals(key)) {
			return t;
		} else if (containsKeyHelper(t.myLeft, key)) {
			return getHelper(t.myLeft, key);
		} else if (containsKeyHelper(t.myRight, key)) {
			return getHelper(t.myRight, key);
		} else {
			return null;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (!containsKey(key)) {
			return null;
		} else if (size() == 1 && myRoot.myItem.key.equals(key)) {
			V value = get(key);
			size--;
			myRoot = null;
			return value;
		} else {
			V value = get(key);
			removeHelper(key);
			size--;
			return value;
		}
	}
	
	public TreeNode removeHelper(K key) {
		TreeNode t = getHelper(myRoot, key);
		if (t.myRight == null && t.myLeft == null) {
			TreeNode parent = t.myParent;
			if (parent.myLeft == t) {
				parent.myLeft = null;
			} else {
				parent.myRight = null;
			}
		} else if (t.myRight == null) {
			t.myItem = t.myLeft.myItem;
			if (t.myLeft.myLeft != null) {
				t.myLeft = t.myLeft.myLeft;
			} if (t.myLeft.myRight != null) {
				t.myRight = t.myLeft.myRight;
			}
		} else if (t.myLeft == null) {
			t.myItem = t.myRight.myItem;
			if (t.myRight.myLeft != null) {
				t.myLeft = t.myRight.myLeft;
			} if (t.myRight.myRight != null) {
				t.myRight = t.myRight.myRight;
			}
		} else {
			Iterator<TreeNode> i = iterator();
			TreeNode n = i.next();
			TreeNode previous = null;
			while (n != t) {
				previous = n;
				n = i.next();
			}
			previous = n;
			n = i.next();
			previous.myItem = n.myItem;
			TreeNode parent = n.myParent;
			if (parent.myLeft == t) {
				parent.myLeft = null;
			} else {
				parent.myRight = null;
			}
		}
		return t;
	}
	
	public Iterator<TreeNode> iterator() {
		return new InorderIterator();
	}
	
	private class InorderIterator implements Iterator<TreeNode> {
        private Stack<TreeNode> nodeStack;
        private TreeNode currentNode;

        public InorderIterator() {
            nodeStack = new Stack<TreeNode>();
            currentNode = myRoot;
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        public TreeNode next() {
            TreeNode nextNode = null;

            // find leftmost node with no left child
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.myLeft;
            }

            // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                assert nextNode != null;    // since nodeStack was not empty before the pop
                currentNode = nextNode.myRight;
            } else {
                throw new NoSuchElementException();
            }

            return nextNode; 
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
		
	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if (containsKey(key)) {
			return getHelper(myRoot, key).myItem.value;
		} else {
			return null;
		}
	}
	
    public class TreeNode {

        public KVPair myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        public int size;
        public TreeNode myParent;

        public TreeNode(KVPair item) {
            myItem = item;
            myLeft = myRight = null;
            size = 1;
        }
        
        public TreeNode(KVPair item, TreeNode left, TreeNode right) {
        	myItem = item;
        	myLeft = left;
        	myRight = right;
        	myLeft.myParent = this;
        	myRight.myParent = this;
        	this.size = 1 + left.size + right.size;
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
		
		public int compareTo(KVPair k){
			if (key.compareTo(k.key) == 0) {
				return 0;
			} else if (key.compareTo(k.key) < 0) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
