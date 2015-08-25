import java.util.Iterator;

import BinaryTree.TreeNode;




/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {
	BinarySearchTree<KVPair> tree;
	
	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		tree = new BinarySearchTree<KVPair>();
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
		KVPair temp = new KVPair(key, null);
		return tree.contains(temp);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
//	public V put(K key, V value) {
//		put(key, value, head, null);
//		return head.value;
//	}
	public KVPair getPair(K key) {
		return getPairHelper(tree.myRoot, key);
	}
	public KVPair getPairHelper(BinaryTree.TreeNode t, K key) {
		if (t == null) {
			return null;
		} else if (key.compareTo(((MyTreeMap<K, V>.KVPair) t.myItem).getKey()) == 0) {
			return ((MyTreeMap<K, V>.KVPair) t.myItem);
		} else if (key.compareTo(((MyTreeMap<K, V>.KVPair) t.myItem).getKey()) < 0) {
			return getPairHelper(t.myLeft, key);
		} else {
			return getPairHelper(t.myRight, key);
		}
	}
	public KVPair getSuccessor(BinaryTree.TreeNode node) {
		if (tree == null) {
			return null;
		} else {
			Iterator iter = tree.iterator();
			while (iter.hasNext()) {
				if (node.myItem.equals(iter.next())) {
					return (MyTreeMap<K, V>.KVPair) iter.next();
				}
			}		
		}
	}
	public BinaryTree.TreeNode findNode(K key) {
		if (tree.myRoot == null) {
			return null;
		}return findNodeHelper(tree.myRoot, key);
	}
	public BinaryTree.TreeNode findNodeHelper(BinaryTree.TreeNode t ,K key) {
		if (key.compareTo(((MyTreeMap<K, V>.KVPair) t.myItem).getKey()) == 0) {
			return t;	
		} else if (key.compareTo(((MyTreeMap<K, V>.KVPair) t.myItem).getKey()) < 0) {
			return findNodeHelper(t.myLeft, key);
		} else {
			return findNodeHelper(t.myRight, key);
		}
	}
	public BinaryTree.TreeNode removeHelper(BinaryTree.TreeNode target, BinaryTree.TreeNode parent, int side) { //0 is left, 1 is right
		if (target.myLeft == null && target.myRight == null) {
			if (side == 0) {
				parent.myLeft =null;
			} else {
				parent.myRight = null;
			}
		}
		else if (target.myLeft != null && target.myRight == null) {
			if (side == 0) {
				parent.myLeft = target.myLeft;
			} else {
				parent.myRight = target.myLeft;
			}
			
		} else if (target.myRight != null && target.myLeft == null ) {
			if (side == 0) {
				parent.myLeft = target.myRight;
			} else {
				parent.myRight = target.myRight;
			}
		}
		else {

			KVPair temp = getSuccessor(target);
			remove(temp.getKey());
			target.myItem = temp;
						
		}
		
	}
//	public V removeHelper(BinaryTree.TreeNode t, K key) {
//		
//		else if (t.myLeft == null && t.myRight == null) {
//			return null;
//		} 
//		else if (t.myLeft != null && t.myRight == null) {
//			t.myLeft = 
//		}
//		else if (((MyTreeMap<K, V>.KVPair) t.myLeft.myItem).getKey() == key) {
//			t.myLeft = t.myLeft.my;
//		} else if (key.compareTo(((MyTreeMap<K, V>.KVPair) t.myItem).getKey()) < 0) {
//			t.myLeft = putHelper(t.myLeft, kv, key);
//		} else {
//			t.myRight = putHelper(t.myRight, kv, key);
//		}
//	}
//	public BinaryTree.TreeNode putHelper(BinaryTree.TreeNode t, KVPair kv, K key) {
//	if (t == null) {
//		 return new BinaryTree.TreeNode(kv);
//	} else if (key.compareTo(((MyTreeMap<K, V>.KVPair) t.myItem).getKey()) < 0) {
//		t.myLeft = putHelper(t.myLeft, kv, key);
//	} else {
//		t.myRight = putHelper(t.myRight, kv, key);
//	}
//}
	public V put(K key, V value) {
		V temp = remove(key);	
		tree.add(new KVPair(key, value));
		return temp;
	}

	
//	private void put(K key, V value, KVPair pair, KVPair parent){
		
//	}
//		if (pair == null){
//			if (parent == null){
//				head = new KVPair(key, value);
//				size = 1;
//			} else {
//				KVPair newkv = new KVPair(key, value);
//				size++;
//				if (parent.key.compareTo(key) < 0) {
//					parent.right = newkv; 
//				} else {
//					parent.left = newkv; 
//				} return; 
//			} 
//		} if (pair.key.compareTo(key) == 0) {
//			pair.value = value; 
//			return; 
//		} if (pair.key.compareTo(key) < 0){
//			put(key, value, pair.right, pair);
//		} else {
//			put(key, value, pair.left, pair);
//		}
//	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		BinaryTree.TreeNode target = findNode(key);
		if (key.compareTo(tree.myRoot.myItem.getKey()) < 0) {
			return removeHelper(target, tree.myRoot, 0);
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
//	public V get(K key) {
//		return get(key, head);
//	}
	
//	private V get(K key, KVPair pair){
//		if (pair == null){
//			return null;
//		} if (pair.key.compareTo(key) == 0){
//			return pair.value;
//		} if (pair.key.compareTo(key) < 0){
//			return get(key, pair.right);
//		} else {
//			return get(key, pair.right);
//		}
//	}

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
		public V getVal() {
			return value;
		}
		public int compareTo(KVPair pair) {
			return key.compareTo(pair.key);
		}
	}
}



 