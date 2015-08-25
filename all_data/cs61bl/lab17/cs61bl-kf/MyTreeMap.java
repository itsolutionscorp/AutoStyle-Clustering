/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<KVPair> extends BinarySearchTree {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	BinarySearchTree<KVPair> myTree;

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
		return myTree.myRoot.mySize;
	}

	/**
	 * Returns whether the map contains the given key.
	 */
	public boolean containsKey(K key){
		return myTree.contains(new KVPair(key, null));
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
		size ++;
		KVPair toPut = new KVPair(key, value);
		myTree.add(toPut);
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (myTree.myRoot != null){
			return removeHelper(myTree.myRoot, key);
		}
		return null;
	}

	public V removeHelper(TreeNode myRoot, K key) {
		if (myRoot == null) {
			return null;
		}
		if (myRoot.myItem.equals(key)) {
			if (myRoot.myLeft == null && myRoot.myRight == null) {
				myRoot.myItem = null;
			}
			else if (myRoot.myLeft == null && myRoot.myRight != null) {
				myRoot.myItem = myRoot.myRight;
			}
			else if (myRoot.myRight == null && myRoot.myLeft != null) {
				myRoot.myItem = myRoot.myLeft;
			}
			return ((KVPair) myRoot.myItem).getValue();
		}
		else if (key.compareTo(myRoot.myItem) < 1){
			return removeHelper(myRoot.myLeft, key);

		}
		else {
			return removeHelper(myRoot.myRight, key);

		}
	}


	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key){
		if (myTree.myRoot == null) {
			return null;
		}
		else if (myTree.myRoot.myItem.equals(key)) {
    		return myTree.myRoot.myItem.getValue();
    	}
    	else if (key.compareTo(myTree.myRoot.myItem.getKey()) < 0) {
    		return this.getHelper(myTree.myRoot.myLeft, key);
    	}
    	else {
    		return this.getHelper(myTree.myRoot.myRight, key);
    	}      	
	}
	
	private V getHelper (TreeNode myLeft, K key){
		if (myLeft == null) {
			return null;
		}
		else if (key.equals(myLeft.myItem)){
			return ((KVPair) myLeft.myItem).getValue();
		}
		else if (key.compareTo(myLeft.myItem) < 0) {
			return getHelper(myLeft.myRight, key);
		}
		else {
			return getHelper(myLeft.myLeft, key);
		}
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	public class KVPair implements Comparable<KVPair>{
		private K key;
		private V value;
		
		
		
		public KVPair(K key2, V value2) {
			key = key2;
			value = value2;
		}

		public void setValue(V v) {
			value = v;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}

		@Override
		public int compareTo(KVPair o) {
			// TODO Auto-generated method stub
			if (this.getKey().compareTo(o.getKey()) > 0) {
				return 1;
			}
			else if (this.getKey().compareTo(o.getKey()) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	public class K implements Comparable<Object>{
		public Object myItem;
		
		public K(Object item){
			myItem = item;
		}
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			K other = (K) o;
			return this.toString().compareTo(other.toString());
		}
	}
	public class V  implements Comparable<Object>{
		public Object myItem;

		public V(Object item){
			myItem = item;
		}
		
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			V other = (V) o;
			return this.toString().compareTo(other.toString());
		}			
	}
	
}

