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
	private double factor;
	private Object [] map;

	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		size = 0;
		capacity = DEFAULT_CAPACITY;
		map = new Object [capacity];
		for (int i=0; i<capacity; i++){
			map[i] = new ArrayList ();
		}
		factor = DEFAULT_LOAD_FACTOR;
		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		size = 0;
		capacity = initialCapacity;
		map = new Object [capacity];
		for (int i=0; i<capacity; i++){
			map[i] = new ArrayList ();
		}
		factor = DEFAULT_LOAD_FACTOR;
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
		size = 0;
		capacity = initialCapacity;
		map = new Object [capacity];
		for (int i=0; i<capacity; i++){
			map[i] = new ArrayList ();
		}
		factor = loadFactor;
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
		// TODO Complete this!
		Iterator iter = iterator();
		while (iter.hasNext()){
			K temp = (K) iter.next();
			if (temp.equals(key)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		Iterator iter = iterator();
		while (iter.hasNext()){
			K tempK = (K) iter.next();
			V tempV = get(tempK);
			if (tempV.equals(value)){
				return true;
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
		// TODO Complete this!
		int hashValue = (int)(key.hashCode())%capacity;
		if (((ArrayList)(map[hashValue])).size()==0){
			((ArrayList)(map[hashValue])).add(key);
			((ArrayList)(map[hashValue])).add(value);
			size+=2;
			if ((double)(size/capacity) > 0.7){
				expand(2*capacity);
			}
			return null;
		} else {
			if (((ArrayList)(map[hashValue])).lastIndexOf(key) != -1){
				V temp = (V) ((ArrayList)(map[hashValue])).get(((ArrayList)(map[hashValue])).lastIndexOf(key)+1); // unChecked
				((ArrayList)(map[hashValue])).set((((ArrayList)(map[hashValue])).lastIndexOf(key)+1), value);
				return temp;
			} else {
				((ArrayList)(map[hashValue])).add(key);
				((ArrayList)(map[hashValue])).add(value);
				size+=2;
				if ((double)(size/capacity) > 0.7){
					expand(2*capacity);
				}
				return null;
			}
		}
	}
	
	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		Iterator iter = iterator();
		int hashValue = 0;
		while (iter.hasNext()){
			K temp = (K) iter.next();
			if (temp.equals(key)){
				hashValue = (int)(key.hashCode())%capacity;
				V valueTemp = (V) ((ArrayList)(map[hashValue])).get(((ArrayList)(map[hashValue])).indexOf(temp)+1);// unChecked
				((ArrayList)(map[hashValue])).remove(((ArrayList)(map[hashValue])).get(((ArrayList)(map[hashValue])).indexOf(temp)+1));
				((ArrayList)(map[hashValue])).remove(temp);
				size-=2;
				return valueTemp;
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
		int hashValue = (int)(key.hashCode())%capacity;
		if (((ArrayList)(map[hashValue])).lastIndexOf(key) == -1){
			return null;
		} else {
			return (V) ((ArrayList)(map[hashValue])).get(((ArrayList)(map[hashValue])).lastIndexOf(key)+1);
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
		MyHashMap<K, V> temp = new MyHashMap(newCapacity);
		Iterator iter = iterator();
		K tempK = null;
		V tempV = null;
		int hashValue = 0;
		while (iter.hasNext()){
			tempK = (K) iter.next();
			tempV = get(tempK);
			hashValue = (int)((tempK.hashCode())%(temp.capacity));
			((ArrayList)(temp.map[hashValue])).add(tempK);
			((ArrayList)(temp.map[hashValue])).add(tempV);
		}
		capacity=newCapacity;
		map = new Object [capacity];
		for (int i=0; i<capacity; i++){
			map[i] = new ArrayList ();
		}
		iter = temp.iterator();
		while (iter.hasNext()){
			tempK = (K) iter.next();
			tempV = temp.get(tempK);
			hashValue = (int)((tempK.hashCode())%(capacity));
			((ArrayList)(map[hashValue])).add(tempK);
			((ArrayList)(map[hashValue])).add(tempV);
		}
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
		private int iterIndex;

		public HashMapIterator() {
			// TODO Complete this!
			iterIndex=0;
		}

		public boolean hasNext() {
			return iterIndex < size;
		}

		public K next() {
			int bulkIndex = 0;
			while (iterIndex >= ((ArrayList)(map[bulkIndex])).size()){
				iterIndex -= ((ArrayList)(map[bulkIndex])).size();
				bulkIndex++;
			}
			iterIndex+=2;
			return (K) ((ArrayList)(map[bulkIndex])).get(iterIndex-2);
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
	}

}
