/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V>{

	private int size; // the number of items that have been put into the map
	private BinarySearchTree<KVPair> myTree;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myTree = new BinarySearchTree<KVPair>();
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
		return containHelp(key, (BinaryTree.TreeNode) myTree.myRoot);
	}
	
	private boolean containHelp(K key, BinarySearchTree.TreeNode t) {
		if (t == null) {
			return false;
		}
		K itemKey = ((KVPair)t.myItem).key;
		if (itemKey.equals(key)) {
			return true;
		}
		if (key.compareTo(itemKey)>0) {
			return containHelp(key, t.myRight);
		}
		if (key.compareTo(itemKey)<0) {
			return containHelp(key, t.myLeft);
		}
		return false;
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
		V prev;
		KVPair existing = putHelp(key, ((BinarySearchTree.TreeNode)myTree.myRoot));
		if (existing!=null) {
			prev = existing.value;
			existing.setValue(value);
			return prev;
		}
		else {
			KVPair myPair = new KVPair(key, value);
			myTree.add(myPair);
			size++;
			return null;
		}
	}
	
	private KVPair putHelp(K key, BinarySearchTree.TreeNode t) {
		if (t == null) {
			return null;
		}
		K itemKey = ((KVPair)t.myItem).key;
		if (itemKey.equals(key)) {
			return ((KVPair)t.myItem);
		}
		if (key.compareTo(itemKey)>0) {
			return putHelp(key, t.myRight);
		}
		if (key.compareTo(itemKey)<0) {
			return putHelp(key, t.myLeft);
		}
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		KVPair temp = putHelp(key, (BinaryTree.TreeNode) myTree.myRoot);
		myTree.remove(temp);
		size--;
		return temp.value;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		KVPair rtn = putHelp(key, (BinaryTree.TreeNode) myTree.myRoot);
		if (rtn != null) {
			return rtn.value;
		}
		return null;
	}

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

		@Override
		public int compareTo(MyTreeMap<K, V>.KVPair o) {
			// TODO Auto-generated method stub
			return key.compareTo(o.key);
		}
		
		public String toString() {
			return "(" + key.toString() + value.toString() + ")";
		}

	}
	
	public static void main (String[] args) {
		MyTreeMap<Integer, String> test = new MyTreeMap<Integer, String>();
		test.put(3, "three");
		test.put(2, "two");
		test.put(1, "one");
		test.put(5, "five");
		test.put(4, "four");
		test.put(6, "six");
		test.remove(1);
		test.remove(3);
		System.out.println(test.get(2)); //two
		BinaryTree.print(test.myTree, "description");
		System.out.println(test.containsKey(1)); //false
		System.out.println(test);
	}
}
