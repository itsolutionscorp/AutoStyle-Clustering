

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private TreeNode myRoot;
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		myRoot = null;
		size=0;
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
		if (myRoot ==null){
		return false;}
		else{
			return containsH(myRoot,key);
		}

	}

	public boolean containsH(TreeNode t, K key) {
		if (t == null) {
			return false;
		} else if (key.compareTo(t.myItem.getKey()) == 0) {
			return true;
		} else if (key.compareTo(t.myItem.getKey()) < 0) {
			return containsH(t.myLeft, key);
		} else {
			return containsH(t.myRight, key);
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
		if (myRoot==null){
			myRoot = new TreeNode(new KVPair(key, value));
			size++;
		}
		if(containsKey(key)){
			return putH(myRoot, key, value);
		}
		myRoot = addH(myRoot, key, value);
		size++;
		return null;
	}
	public V putH(TreeNode t, K key, V val) {
		if (t == null) {
			return null;
		} else if (key.compareTo(t.myItem.getKey()) == 0) {
			V temp=t.myItem.getVal();
			t.myItem.setValue(val);
			return temp;
		} else if (key.compareTo(t.myItem.getKey()) < 0) {
			return putH(t.myLeft, key, val);
		} else {
			return putH(t.myRight, key, val);
		}
	}
	public TreeNode addH(TreeNode t, K key, V value) {
		if (t == null) {
			return new TreeNode(new KVPair(key, value));
		} else if (key.compareTo(t.myItem.getKey()) < 0) {
			t.myLeft = addH(t.myLeft, key, value);
			t.mySize++;
			return t;
		} else {
			t.myRight = addH(t.myRight, key, value);
			t.mySize++;
			return t;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (!containsKey(key)){
			return null;
		}
		V temp= get(key);
		
		return temp;
	}
	public void removeH(TreeNode t, K key) {
		if (t == null) {
			return;
		} else if (key.compareTo(t.myItem.getKey()) == 0) {
			if (t.myRight==null &&t.myLeft==null){
				t=null;
			}
			else if(t.myRight==null){
				t=t.myLeft;
			}
			else if (t.myLeft==null){
				t=t.myRight;
			}
			else{
				TreeNode temp= inOrderSuccessorH(t);
				KVPair tmp=temp.myItem;
				t.myItem=tmp;
			}
		} else if (key.compareTo(t.myItem.getKey()) < 0) {
			removeH(t.myLeft, key);
		} else {
			removeH(t.myRight, key);
		}
	}
	public TreeNode inOrderSuccessorH(TreeNode t){
		if(t.myRight.myLeft==null){
			return t.myRight;
		}
		else{
			return IOSH(t.myRight);
		}
	}
	public TreeNode IOSH(TreeNode t){
		if(t.myLeft==null){
			return t;
		}
		return IOSH(t.myLeft);
	}
	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (myRoot ==null){
			return null;}
			else{
				return getH(myRoot,key);
			}
	}
	public V getH(TreeNode t, K key) {
		if (t == null) {
			return null;
		} else if (key.compareTo(t.myItem.getKey()) == 0) {
			return t.myItem.getVal();
		} else if (key.compareTo(t.myItem.getKey()) < 0) {
			return getH(t.myLeft, key);
		} else {
			return getH(t.myRight, key);
		}
	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable{
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
		public K getKey(){
			return key;
		}
		public V getVal(){
			return value;
		}
		public int compareTo(Object obj){
			return key.compareTo(((KVPair)obj).key);
		}
	}
	protected class TreeNode {

        public KVPair myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        public int mySize;

        public TreeNode(KVPair item) {
            myItem = item;
            myLeft = myRight = null;
            mySize=1;
        }

        public TreeNode(KVPair item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
            if(myLeft==null){
            	if(myRight==null){
            		mySize=1;
            		return;
            	}
            	mySize=myRight.mySize+1;
            	return;
            }
            if(myRight==null){
            	mySize=myLeft.mySize+1;
            	return;
            }
            mySize=myRight.mySize+myLeft.mySize+1;
        }
	
	}
}
