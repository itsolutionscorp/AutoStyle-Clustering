import java.util.Iterator;




/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	BinarySearchTree<KVPair >myTree;
	BinaryTree<KVPair>.TreeNode bstRoot;
	MyTreeMap<K,V> left, right;
	KVPair curItem;
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myTree = new BinarySearchTree<KVPair>();
		bstRoot = null;
		curItem = null;
		left = null;
		right = null;
		size = 0;
	}
	
	public MyTreeMap(KVPair myItem, MyTreeMap<K,V> left, MyTreeMap<K,V> right){ //Recursive Constructor
		myTree = new BinarySearchTree<KVPair>();
		curItem = myItem;
		this.left = left;
		this.right = right;
		size = this.size();
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		if(left != null && right != null)
			return 1 + left.size() + right.size();
		else if(left == null && right != null)
			return 1 + right.size();
		else if(left != null && right == null)
			return 1 + left.size();
		return 1;
		
	}

	/**
	 * Returns whether the map contains the given key.
	 */
	public boolean containsKey(K key) {
		// TODO Complete this!
		/*Iterator<KVPair> myIterator = myTree.iterator();
		while(myIterator.hasNext()){
			KVPair temp = myIterator.next();
			if(temp.getKey().equals(key))
				return true;
		}
		return false;*/
		if(curItem == null)
			return false;
		else if(curItem.compareTo(key) == 0)
			return true;
		else if(curItem.compareTo(key) > 0){
			if(left != null)
				return left.containsKey(key);
		}else{
			if(right != null)
				return right.containsKey(key);
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
		if(curItem == null){//Empty
			this.curItem = new KVPair(key, value); 
			return null;
		}else if(this.curItem.compareTo(key) == 0){
			V temp = curItem.getValue();
			curItem.setValue(value);
			return temp;
		}else if(left == null || right == null){
			if(curItem.compareTo(key) < 0){
				if(right == null)
					this.right = new MyTreeMap<K, V>(new KVPair(key, value), null, null);
				else
					return right.put(key, value);
			}else if(curItem.compareTo(key) > 0){
				if(left == null)
					this.left = new MyTreeMap<K, V>(new KVPair(key, value), null, null);
				else
					return left.put(key, value);
			}
			return null;
			
		}else if(this.curItem.compareTo(key) > 0){
			return left.put(key, value);
		}else
			return right.put(key, value);
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if(!containsKey(key)){
			return null;
		}else if(curItem.compareTo(key) == 0){
			V temp = curItem.getValue();
			if(this.size() == 1){//Means its a leaf
				curItem = null;
			}else if(this.right == null){
				this.curItem = this.left.curItem;
				this.right = this.left.right;
				this.left = this.left.left;
			}else{
				this.curItem = this.right.curItem;
				this.left = this.right.left;
				this.right = this.right.right;
			}
			return temp;
				
		}else if(this.curItem.compareTo(key) > 0){
			return this.left.remove(key);
		}return this.right.remove(key);
		
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if(curItem == null || !containsKey(key)){//empty
			return null;
		}else if(left == null && right == null ){//leaf case
			if(!curItem.equals(key))
				return null;
			return curItem.getValue();
			
		}else{
			if(this.curItem.equals(key))//If it is equal to the key, then return the associated
			//value
				return curItem.getValue();
			else if(curItem.compareTo(key) < 0)
				return this.right.get(key);
			else
				return this.left.get(key);
		} //return curItem.getValue();
			
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
		
		public K getKey(){
			return key;
		}
		
		public V getValue(){
			return value;
		}
		
		public boolean equals(KVPair other){
			if(key.equals(other.getKey()) && value.equals(other.getValue()))
				return true;
			return false;
		}
		
		public boolean equals(K other){
			if(key.equals(other))
				return true;
			return false;
		}
		public int compareTo(KVPair other){
			if(key.compareTo(other.getKey()) == 0)
				return 0;
			else if(key.compareTo(other.getKey()) < 0)
				return -1;
			return 1;
		}
		
		public int compareTo(K other){
			if(key.compareTo(other) == 0)
				return 0;
			else if(key.compareTo(other) < 0)
				return -1;
			return 1;
		}
		
	}
}
