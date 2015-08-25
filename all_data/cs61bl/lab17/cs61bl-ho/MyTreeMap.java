import java.util.Iterator;


/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> extends BinarySearchTree<K> {

	private int size; // the number of items that have been put into the map
	private V[] map;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		size = 0;
		map = (V[]) new Object [1];
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
		return super.contains(key);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		if (!containsKey(key)){
			add(key);
			size++;
			if ((myRoot.height()*myRoot.height()-1) > map.length){
				V[] temp = (V[]) new Object [2*map.length+1];
				for (int i = 0; i < map.length; i++){
					temp[i] = map [i];
				}
				map = temp;
			}
			int pos = 1;
			TreeNode tempNode = myRoot;
			while (tempNode != null){
				if (key.compareTo(tempNode.myItem) == 0){
					break;
				} else if (key.compareTo(tempNode.myItem) > 0){
					pos = pos*2 + 1;
					tempNode = tempNode.myRight;
				} else {
					pos *= 2;
					tempNode = tempNode.myLeft;
				}
			}
			map [pos-1] = value;
			return null;
		} else {
			int pos = 1;
			TreeNode tempNode = myRoot;
			while (tempNode != null){
				if (key.compareTo(tempNode.myItem) == 0){
					break;
				} else if (key.compareTo(tempNode.myItem) > 0){
					pos = pos*2 + 1;
					tempNode = tempNode.myRight;
				} else {
					pos *= 2;
					tempNode = tempNode.myLeft;
				}
			}
			V tempValue = map [pos-1];
			map [pos-1] = value;
			return tempValue;
		}
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		int pos = 1;
		TreeNode tempNode = myRoot;
		while (tempNode != null){
			if (key.compareTo(tempNode.myItem) == 0){
				break;
			} else if (key.compareTo(tempNode.myItem) > 0){
				pos = pos*2 + 1;
				tempNode = tempNode.myRight;
				if (tempNode == null){
					return null;
				}
			} else {
				pos *= 2;
				tempNode = tempNode.myLeft;
				if (tempNode == null){
					return null;
				}
			}
		}
		return map[pos-1];
	}

	/**
	 * A class that can store a key and a value together. You can modify this
	 * class however you want.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public void setValue(V v) {
			value = v;
		}
	}
}
