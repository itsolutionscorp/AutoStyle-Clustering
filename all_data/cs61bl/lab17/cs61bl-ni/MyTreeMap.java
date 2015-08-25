/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */

public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private Node<K, V> root;
	private Node<K, V> preNode;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		root = null;
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

		if (findNode(key, root) == null) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	private V put(K k, V newV, Node<K,V> node, Node<K,V> nodesParent) {
        if (node == null) { 
            if (nodesParent == null) { // empty tree
                root = new Node<K,V>(k, newV);
                size = 1;
            }
            else {
                // we have gone past a leaf where insertion is done
                // make a new leaf
                Node<K,V> newLeaf = new Node<K,V>(k, newV);
                size++;
                // link it to its parent.  Is it the left child, or the right child?
                if (nodesParent.key.compareTo(k) < 0)
                    nodesParent.right = newLeaf;
                else
                    nodesParent.left = newLeaf;
            }
            return null;
        }
        

        if (node.key.compareTo(k) == 0) {
            // hit, so update existing node
        	V oldvalue = node.value;
            node.value = newV;
            return oldvalue;
        }

        if (node.key.compareTo(k) < 0)
            put(k, newV, node.right, node);
        else
            put(k, newV, node.left, node);
		return null;
    }

	public V put(K key, V value) {
		return put(key, value, root, null);
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V oldvalue;
		// check whether the key is exist
		if (!this.containsKey(key)) {
			System.out.println("specific key is not found");
			return null;
		}

		// combo operation for findNode
		this.preNode = null;
		Node<K, V> node = this.findNode(key, root);
		Node<K, V> tmp = this.preNode;
		if (node == null) {
			System.out.println("result is null");
		}
		// if the key is leaf with no children
		if (node.getleft() == null && node.getright() == null) {
			oldvalue = node.value;
			this.redirectNode(tmp, key, null);
			return oldvalue;

		}
		// the key has only one child
		else if (node.getleft() == null) {
			oldvalue = node.value;
			this.redirectNode(tmp, key, node.getright());
			return oldvalue;

		} else if (node.getright() == null) {
			oldvalue = node.value;
			this.redirectNode(tmp, key, node.getleft());
			return oldvalue;

		}

		// the key has two children
		else {
			// save the parent node
			Node<K, V> parent = tmp;

			// find out min node in the right subtree of the node which need to
			// be removed
			this.preNode = null;
			// the min must exist because the previous process already handle
			// none
			// exist situation
			Node<K, V> min = this.findMin(node.getright());
			// remove min node from the BST(min should be a leaf node, easy to
			// delete)
			oldvalue = min.value;
			this.remove(min.getkey());
			// use the min node to replace the removed node
			min.left = node.getleft();
			min.right = node.getright();
			this.redirectNode(parent, key, min);
			return oldvalue;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		return get(key, root);
	}

	private V get(K k, Node<K, V> node) {
		if (node == null)
			return null; // not found
		// System.out.println("comparing " + node.key + " to " + k);
		if (node.key.compareTo(k) == 0)
			return node.value; // found item
		if (node.key.compareTo(k) < 0) // go right
			return get(k, node.right);
		else
			return get(k, node.left);
	}

	/**
	 * A class that can store a key and a value together. You can modify this
	 * class however you want.
	 */
	// private class KVPair {
	// private K key;
	// private V value;
	//
	// public KVPair(K k, V v) {
	// key = k;
	// value = v;
	// }
	//
	// public void setValue(V v) {
	// value = v;
	// }
	// }
	// nested class for nodes
	private static class Node<K, V> {
		K key;
		V value;
		Node<K, V> left, right;

		public Node(K k, V v) {
			key = k;
			value = v;
			left = right = null;
		}

		public K getkey() {
			return this.key;
		}

		public Node<K, V> getleft() {
			return this.left;
		}

		public Node<K, V> getright() {
			return this.right;
		}
	}

	public static void main(String[] args) {
		MyTreeMap<String, Integer> tm = new MyTreeMap<>();
		tm.put("Owen", 5);
		tm.put("Kaser", 6);
		tm.put("Owen", 7);
		tm.put("CS2383", 8);
		tm.put("M", 9);
		tm.put("M", 10);

		System.out.println("map contents: " + tm.toString());
		System.out.println("value of M is " + tm.put("M", 2));
		System.out.println("Removed value: " + tm.remove("M"));
		 System.out.println("Value after modification: " + tm.toString());

	}

	// inorder traversal, helper for toString
	void traverse(Node<K, V> node, StringBuilder sb) {
		if (node == null)
			return;
		traverse(node.left, sb);
		sb.append(" " + node.key + " => " + node.value);
		traverse(node.right, sb);
	}

	// makes an in-order representation of the keys and their values
	public String toString() {
		StringBuilder sb = new StringBuilder();
		traverse(root, sb);
		return sb.toString();
	}

	private Node<K, V> findNode(K key, Node<K, V> curr) {
		// check the input validation
		if (curr == null) {
			return null;
		}

		// compare the specified node and current node
		int result = key.compareTo(curr.getkey());

		// found the node
		if (result == 0) {
			return curr;
		}
		// specified node > curr node
		else if (result > 0) {
			// to the end of the tree
			if (curr.getright() == null) {
				return null;
			}

			// recursively search
			this.preNode = curr;
			return findNode(key, curr.getright());
		} else if (result < 0) {
			// to the end of the tree
			if (curr.getleft() == null) {
				return null;
			}

			// recursively search
			this.preNode = curr;
			return findNode(key, curr.getleft());
		} else {
			System.out.println("Error in findNode");
			return null;
		}
	}

	private void redirectNode(Node<K, V> parent, K key, Node<K, V> replace) {
		// for the root
		if (parent == null) {
			root = replace;
			return;
		}

		// previous node's left side points to desired node
		if (parent.getleft() != null) {
			if (parent.getleft().getkey().equals(key)) {
				parent.left = replace;
				return;
			}
		}

		if (parent.getright() != null) {
			if (parent.getright().getkey().equals(key)) {
				parent.right = replace;
				return;
			}
		}
		System.out.println("Error at redirectNode");
	}

	public Node<K, V> findMin(Node<K, V> node) {
		// check the input
		if (node == null) {
			return null;
		}

		// check left side
		if (node.getleft() == null) {
			return node;
		} else {
			this.preNode = node;
			return findMin(node.getleft());
		}
	}
}
