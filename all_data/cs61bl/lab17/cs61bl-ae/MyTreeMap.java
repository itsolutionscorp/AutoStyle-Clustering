/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	BinarySearchTree<KVPair> myTree;
	

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
		// TODO Complete this!
		KVPair keyPair = new KVPair(key, null);
		return myTree.contains(keyPair);
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
		KVPair pair = new KVPair(key, null);
		if(myTree.contains(pair)){
			KVPair prev = myTree.find(pair);
			V result =prev.getValue();
			prev.setValue(value);
			return result;
		}else {
			myTree.add(new KVPair(key, value));
			size++;
			return null;
		}
	}
	
	
	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (! myTree.contains(new KVPair(key, null))){
			return null;
		}else {
			size--;
			KVPair target = myTree.find(new KVPair(key, null));
			V result = target.getValue();
			myTree = myTree.remove(new KVPair(key, null));
			return result;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		if(!myTree.contains(new KVPair(key, null))){
			return null;
		}
		return myTree.find(new KVPair(key, null)).getValue();
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
		
		public V getValue(){
			return value;
		}
		public int compareTo(MyTreeMap<K, V>.KVPair pair) {
			return key.compareTo(pair.key);
		}
	}
	

}
