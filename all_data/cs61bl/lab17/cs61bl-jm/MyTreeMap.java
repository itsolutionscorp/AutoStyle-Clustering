

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree<KVPair> myTree;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		myTree = new BinarySearchTree<KVPair>();
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
		return myTree.contains(key);
	}
		

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		KVPair target = new KVPair(key, value);
		if (containsKey(key)) {
			KVPair kvPair = myTree.find(target);
			V toReturn = kvPair.getValue();
			kvPair.setValue(value);
			return toReturn;
		} else {
			myTree.add(target);
			size += 1;
			return null;
		}
		
		
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		V toReturn = get(key);
		if (containsKey(key)) {
			myTree.remove(key);
			size -= 1;
		}
		return toReturn;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if (containsKey(key)){
			KVPair toReturn = myTree.find(key);
			return toReturn.getValue();
		} else {
		return null;
		}
	}

	public static void main(String[] args) { 
		MyTreeMap<String, Integer> myMap = new MyTreeMap<String, Integer>();
		myMap.myTree.printInorder();
		myMap.put("a", 1);
		myMap.put("b", 2);
		myMap.put("c", 3);
		myMap.put("d", 4);
		myMap.put("e", 5);
		myMap.put("f", 6);
		myMap.put("g", 7);
		myMap.myTree.printInorder();
		myMap.remove("d");
		myMap.myTree.printInorder();
		myMap.remove("a");
		myMap.myTree.printInorder();
		myMap.remove("g");
		myMap.myTree.printInorder();
		myMap.remove("c");
		myMap.myTree.printInorder();
		myMap.remove("g");
		myMap.myTree.printInorder();
		
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
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		public void setValue(V v) {
			value = v;
		}
		
		public int compareTo(Object key2) {
			try {
				return key.compareTo((K) key2);
			} catch (ClassCastException e) {
				return key.compareTo(((KVPair) key2).key);
			}
		}
		
		
		public String toString() {
			return "(" + key.toString() + " : " + value.toString() + ")";
		}
		
	}
}
