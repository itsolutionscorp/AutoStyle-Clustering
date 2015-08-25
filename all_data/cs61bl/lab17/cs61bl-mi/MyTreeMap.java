import java.util.*;

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map
	private BinarySearchTree<KVPair> tree;
	private ArrayList<KVPair> nodes;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		size = 0;
		tree = new BinarySearchTree<KVPair>();
		nodes = new ArrayList<KVPair>();
		
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
		KVPair toCompare = new KVPair(key, null);
		return tree.contains(tree.myRoot, toCompare);
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
			KVPair toPut = new KVPair(key, value);
			for (KVPair node : nodes) {
				if (toPut.compareTo(node) == 0) {
					nodes.remove(node);
					break;
				}
			}
			nodes.add(toPut);
			KVPair almostReturn = tree.replace(tree.myRoot, toPut);
			return almostReturn.getValue();
		}
		KVPair toPut = new KVPair(key, value);
		nodes.add(toPut);
		tree.put(tree.myRoot, new KVPair(key, value));
		size++;
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	
	public V remove(K key) {
		if (!containsKey(key)) {
			return null;
		}
		V toReturn = null;
		for (KVPair node : nodes) {
			if (node.getKey().equals(key)) {
				toReturn = node.getValue();
				nodes.remove(node);
				break;
			}
		}
		tree = new BinarySearchTree<KVPair>();
		for (KVPair node : nodes) {
			tree.add(node);
		}
		size--;
		return toReturn;
	}
//	public V remove(K key) {
//		// TODO Complete this!
//		KVPair toCompare = new KVPair(key, null);
//		if (containsKey(key)) {
//			KVPair almostReturn = tree.remove(tree.myRoot, toCompare);
//			
//			size--;
//			return
//		}
//		return null;
//	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		KVPair toCompare = new KVPair(key, null);
		if (containsKey(key)) {
			KVPair almostReturn = tree.get(tree.myRoot, toCompare);
			return almostReturn.getValue();
		}
		return null;
	}
	
	public void balance() {
		//stuff
	}
	
//	public void print() {
//		tree.print(tree.myRoot, "");
//	}

	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable<KVPair> {
		private K key;
		private V value;
		
		public int compareTo(MyTreeMap<K,V>.KVPair pair2) {
			K key2 = (K) pair2.key;
			return key.compareTo(key2);
			
		}

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
		
		public V getValue() {
			return value;
		}
		
		public String toString() {
			return "(" + key.toString() + ", " + value.toString() + ")";
		}
	}
	
//	public static void main(String[] args) {
//		MyTreeMap<String, String> myMap = new MyTreeMap<String, String>();
//		myMap.put("c", "cherry");
//		myMap.put("a", "apple");
//		myMap.put("b", "banana");
//		myMap.put("d", "dragonfruit");
//		myMap.put("e", "eggplant");
//		myMap.put("a", "artichoke");
//		myMap.remove("a");
//		myMap.remove("c");
//		myMap.put("f", "fig");
//		BinaryTree.print(myMap.tree, "tree");
//		MyTreeMap<Integer, String> myMap2 = new MyTreeMap<Integer, String>();
//		myMap2.put(1, "a");
//		myMap2.put(2, "b");
//		myMap2.put(1, "c");
//		System.out.println(myMap2.containsKey(2));
//		BinaryTree.print(myMap2.tree, "tree2");
//	}
}
