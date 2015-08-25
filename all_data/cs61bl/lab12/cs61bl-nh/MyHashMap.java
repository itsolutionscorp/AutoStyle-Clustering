import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.lang.Math; // Needed because apparently the hashcode of some strings appear to be negative
						// and modulo in java doesn't always return positive values

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private double loadFactor;

	// TODO You may declare new instance variables here
	
//	private LinkedList<KVPair> keyValue;
	private ArrayList<LinkedList<KVPair>> map;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		map = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i=0;i<capacity;i++) {
			map.add(new LinkedList());
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		size = 0;
		map = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i=0;i<capacity;i++) {
			map.add(new LinkedList());
		}
	}

	/**
	 * Constructs an empty map with the given intial capacity and the given load
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
		map = new ArrayList<LinkedList<KVPair>>(capacity);
		for (int i=0;i<capacity;i++) {
			map.add(new LinkedList());
		}
		this.loadFactor = loadFactor;
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
	
	public ArrayList getMap() {
		return map;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		
		LinkedList<KVPair> link = map.get(Math.abs(key.hashCode())%capacity);
		
		if (link==null){
			return false;
		} else {
			for (int index=0;index<link.size();index++) {
				if (link.get(index).key.equals(key)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int mapIndex = 0;mapIndex<map.size();mapIndex++) {
			LinkedList<KVPair> link = map.get(mapIndex);
			for (int linkIndex=0;linkIndex<link.size();linkIndex++) {
				if (link.get(linkIndex).value.equals(value)) {
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
		LinkedList<KVPair> link1 = map.get(Math.abs(key.hashCode())%capacity);
		double currentLoad;
		if (containsKey(key)) {
			V oldValue;
			oldValue = link1.get(keyLocator(key)).value;
			link1.get(keyLocator(key)).value = value;
			return oldValue;
		} else {			
			KVPair newKV = new KVPair(key, value);
			map.get(Math.abs(key.hashCode())%capacity).add(newKV);
			size++;
			currentLoad = (double)size/ (double)capacity;
			if (currentLoad>=loadFactor) {
				expand(capacity*2);
			}
			return null;
		}
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		
		LinkedList<KVPair> link1 = map.get(Math.abs(key.hashCode())%capacity);
		
		if (containsKey(key)==false) {
			return null;
		} else {
			V removedValue;
			removedValue = link1.get(keyLocator(key)).value;
			link1.remove(keyLocator(key));
			size--;
			return removedValue;			
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		LinkedList<KVPair> link1 = map.get(Math.abs(key.hashCode())%capacity);
		if (containsKey(key)==false) {
			return null;
		} else {
			return link1.get(keyLocator(key)).value;	
		}
	}
	
	public int keyLocator(K key) { 			//Returns the index location of key in LinkedList
		LinkedList<KVPair> link1 = map.get(Math.abs(key.hashCode())%capacity);
		if (containsKey(key)==false) {
			return (Integer) null;
		} else {
			KVPair currentKV = link1.getFirst();
			Iterator iter = link1.iterator();
			int count=0;
			K current = currentKV.key;
			while (!current.equals(key) && iter.hasNext()) {
				current = (K) iter.next();
				count++;
			}
			return count;
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		// TODO Complete this!
		int[] oldCapacity = new int[capacity];
		KVPair temp;
		int newHashIndex;
		for (int i=0;i<(newCapacity-capacity);i++) { //expand the arrayList
			map.add(new LinkedList<KVPair>());
		}
		for (int i=0;i<capacity;i++) {	//gets the length of every LinkedList in the old hashtable
			oldCapacity[i] = map.get(i).size();
		}
		for (int i=0;i<capacity;i++) {  //cycles through the arraylist to access every LinkedList within the old capacity
			for (int j=0;j<oldCapacity[i];j++) {	//cycles through all the old JVPair elements
				temp = map.get(i).getFirst();		//retrieves and removes old JVPair
				map.get(i).removeFirst();
				newHashIndex = Math.abs(temp.key.hashCode())%newCapacity;	
				map.get(newHashIndex).add(temp);	//redistribute old JVPair to newHashTables
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
		
		int index;
		LinkedList<KVPair> l1;
		K current;

		public HashMapIterator() {
			// TODO Complete this!
			current = l1.getFirst().key;
			index = 0;
		}

		public boolean hasNext() {
			return l1.get(index+1)!=null;
		}

		public K next() {
			index++;
			return current = l1.get(index).key;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

}
