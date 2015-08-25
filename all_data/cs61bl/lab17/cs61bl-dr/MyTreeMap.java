/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {
	
	// we made TreeNode public inside of BinaryTree

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> tree;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
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
		return tree.contains(new KVPair(key, null));		
	}
	

	public void print(){
		tree.print();
		System.out.println("\n\n");
	}
	public void printInorder(){
		tree.printInorder();
	}
	public void printPreorder(){
		tree.printPreorder();
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		V val = null;
		if(containsKey(key)){
			val = get(key);			
			KVPair orig = tree.get(new KVPair(key, null));
			orig.setValue(value);
		}
		else{
			tree.add(new KVPair(key, value));
		}
		return val;
	}
	

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V val = null;
		if(containsKey(key)){
			val =  get(key);
			tree.remove(new KVPair(key, null));
		}		 
		return val;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		return tree.get(new KVPair(key, null)).value();		
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

		public K key(){
			return key;
		}
		public V value(){
			return value;
		}

		@Override
		public int compareTo(MyTreeMap<K, V>.KVPair o) {
			return key.compareTo(o.key());			
		}
		
		public String toString(){
			return "[" + key.toString() + ", " + value.toString() + "]";
		}
		
	}
}
