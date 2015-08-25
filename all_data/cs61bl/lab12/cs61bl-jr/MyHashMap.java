import java.util.Iterator;
import java.util.ArrayList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	
	private ArrayList<KVPair> list;
	private double myLoadFactor;
	
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		size = 0;
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		list = new ArrayList<KVPair>(capacity);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		size = 0;
		myLoadFactor = DEFAULT_LOAD_FACTOR;
		list = new ArrayList<KVPair>(capacity);
	}

	/**
	 * Constructs an empty map with the given initial capacity and the given load
	 * factor.
	 * 
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		capacity = initialCapacity;
		size = 0;
		myLoadFactor = loadFactor;
		list = new ArrayList<KVPair>(capacity);
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the capacity of the underlying array of the map.
	 */
	public int capacity() {
		return this.capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {	
		for (int i = 0; i < size; i++) {
			KVPair temp = list.get(i);
			if (temp != null) {
				if (temp.getK().hashCode() == key.hashCode()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < size; i++) {
			KVPair temp = list.get(i);
			if (list.get(i) != null) {
				if (temp.getV().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 * 
	 * Note: If this method causes size / capacity to be greater than the load
	 * factor, then this method should also expand the map.
	 */
	public V put(K key, V value) {
		V returnValue = null;
		if (containsKey(key)) {
			for (int i = 0; i < size; i++) {
				KVPair temp = list.get(i);
				if (temp.getK().hashCode() == key.hashCode()) {
					returnValue = list.get(i).getV();
					list.get(i).setvalue(value);
				}
			}
			if (((double)size/capacity) >= myLoadFactor){
				expand (capacity + 3);
			}
			return returnValue;
		} else {
			for (int i = key.hashCode() % capacity; i < size; i++) {
				if (i == capacity - 1) {
					if (list.get(i) == null) {
						for (int j = 0; j < key.hashCode()%capacity - 1; j++) {
							if (list.get(j) == null) {
								KVPair toAdd = new KVPair (key,value);
								list.add(j,toAdd);
								size++;
							}
						}
					}
				}
			}
			expand(capacity+3);
			KVPair toAdd = new KVPair (key,value);
			list.add(toAdd);
		}
		return returnValue;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		for (int i = key.hashCode() % capacity; i < size; i = (i + 1) % capacity) {
			KVPair temp = list.get(i);
			if (temp.getK().hashCode() == key.hashCode()) {
				V value = temp.getV();
				list.remove(i);
				return value;
			}
		}
		return null;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		for (int i = 0; i < list.size(); i++) {
			KVPair temp = list.get(i);
			if (temp.getK().hashCode() == key.hashCode()) {
				return temp.getV();
			}
		}
		return null;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		ArrayList <KVPair> temp_list = new ArrayList<KVPair>(capacity);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				KVPair temp = list.get(i);
				for (int j = temp.getK().hashCode() % newCapacity; j < list.size(); j++) {
					if (temp_list.get(i) == null) {
						temp_list.add(i,temp);
					}
				}	
			}
		}
		capacity = newCapacity;
	}
	
	
	/**
	 * Returns an iterator over the keys of this map.
	 */
	public Iterator<K> iterator() {
		return new HashMapIterator();
	}

	/**
	 * An iterator for the keys of the enclosing map.
	 */
	private class HashMapIterator implements Iterator<K> {

		private int index = 0;	
		
		public HashMapIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return (index < size);
		}

		@Override
		public K next() { 
			KVPair temp = null;
			for (int i = index; i < list.size(); i++) {
				if (list.get(i) != null){
					temp = list.get(i);
					index = i+1;
					break;
				}
			}
			return temp.getK();
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		
		public KVPair (K k1, V v1) {
			key = k1;
			value = v1;
		}
		
		public K getK (){
			return key;
		}
		
		public V getV() {
			return value;
		}
		
		public void setvalue(V v2) {
			value = v2;
		}
	}

}
