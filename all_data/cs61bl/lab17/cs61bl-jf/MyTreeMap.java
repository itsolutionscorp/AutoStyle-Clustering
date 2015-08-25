/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> tree;
	
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
		
		return tree.contains(new KVPair(key, null));
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
		V temp;
		if(tree.contains(new KVPair(key, value))){
		temp = tree.get(new KVPair(key, value)).value;}
		else{
			 temp = null;
		}
		tree.add(new KVPair(key, value));
		return temp;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		V temp = tree.get(new KVPair(key,null)).value;
		tree.delete(new KVPair(key, null));
		return temp;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		return tree.get(new KVPair(key, null)).value;
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
		
		public int compareTo(KVPair O){
			return key.compareTo(O.key);
		}
	}
	
	public static void main(String[] args){
		MyTreeMap<String, Integer> a = new MyTreeMap();
		a.put("A", new Integer(1));
		a.put("B", new Integer(2));
		a.put("C", new Integer(3));
		a.put("D", new Integer(4));
		System.out.println(a.put("A", new Integer(2)));
		System.out.println(a.containsKey("A"));
		System.out.println(a.get("A"));
		System.out.println(a.remove("D"));
		
	}
}
