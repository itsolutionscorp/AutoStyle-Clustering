import java.util.Iterator;
import java.util.LinkedList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map

	private LinkedList<KVPair>[] myBucketArray;
	private double myLoadFactor = DEFAULT_LOAD_FACTOR;
	/**
	 * Constructs an empty map.
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap() {
		myBucketArray = new LinkedList[DEFAULT_CAPACITY];
		// make a map with default capacity
		capacity = DEFAULT_CAPACITY;
		size = 0;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity) {
		myBucketArray = new LinkedList[DEFAULT_CAPACITY];
//		myBucketArray = new ArrayList<LinkedList<KVPair>>(initialCapacity);
		capacity = initialCapacity;
		size = 0;
		
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
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, double loadFactor) {
		myBucketArray = new LinkedList[DEFAULT_CAPACITY];
//		myBucketArray = new ArrayList<LinkedList<KVPair>>(initialCapacity);
		myLoadFactor = loadFactor;
		capacity = initialCapacity;
		size=0;
		
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
		if (myBucketArray[index(key)]!=null){
			LinkedList<KVPair> list = myBucketArray[index(key)];
			if (list != null){
				Iterator<KVPair> listIter = list.iterator();
				while(listIter.hasNext()){
					KVPair pair = listIter.next();
					if (pair.key.equals(key)){
						return true;
					}
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
		for(int i=0; i<myBucketArray.length; i++){
			if (myBucketArray[i]!= null){
				LinkedList<KVPair> list = myBucketArray[i];
				Iterator<KVPair> listIter = list.iterator();
				while(listIter.hasNext()){
					KVPair pair = listIter.next();
					if (pair.value.equals(value)){
						return true;
					}
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
		KVPair pair = new KVPair(key, value);
		if (myBucketArray[index(key)] == null){
			LinkedList<KVPair> list = new LinkedList<KVPair>();
			size++;
			list.add(pair);
			myBucketArray[index(key)] =list;			
			return pair.value;
		} else if (!containsKey(key)){
			// don't have they key, but there is a list...
			LinkedList<KVPair> list = myBucketArray[index(key)];
			size++;
			// make a load check here this is the only place where the list grows right 
			list.add(pair);
			if ((double) size/capacity > myLoadFactor){
				expand(capacity*2);
			}
			//add it to the entry
			return null;
		} else {
			LinkedList<KVPair> list = myBucketArray[index(key)];
			Iterator<KVPair> listIter = list.iterator();
			while(listIter.hasNext()){
				KVPair kvPair = listIter.next();
				if (kvPair.key.equals(key)){
					kvPair.value = pair.value;
					return value;
				}
			}			
		}
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if (containsKey(key)){
			LinkedList<KVPair> list = myBucketArray[index(key)];
			Iterator<KVPair> listIter = list.iterator();
			while(listIter.hasNext()){
				KVPair kvPair = listIter.next();
				if (kvPair.key.equals(key)){
					list.remove(kvPair);
					size--;
					return kvPair.value;
				}
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
		LinkedList<KVPair> list = myBucketArray[index(key)];
		if (list == null){
			return null;
		} else {
			Iterator<KVPair> listIter = list.iterator();
			while(listIter.hasNext()){
				KVPair pair = listIter.next();
				if (pair.key.equals(key)){
					return pair.value;
				}
			}
		}
		return null;
	}
	
	private int index(K key){
		return key.hashCode()%capacity;
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	@SuppressWarnings("unchecked")
	private void expand(int newCapacity) {
		int oldSize = size;
		size = 0;
		capacity = newCapacity;
		LinkedList<KVPair>[] old = myBucketArray;
		myBucketArray = new LinkedList[capacity];
		for (int i=0; i< oldSize;i++){
			if (old[i]!=null){
				LinkedList<KVPair> list = old[i];
				Iterator<KVPair> listIter = list.iterator();
				while (listIter.hasNext()){
					KVPair currentPair = listIter.next();
					put(currentPair.key, currentPair.value);
					// i can say my current one is 
//					maybe use some sort of helper function very similar to put here, either that or simply reset size to 0 so when im adding shit in 
					// stuff dont get cray
					
				}
			}
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

		private int count;
		private int arrayIndex;
		private int linkedListIndex;
		// 1 i should probably know which index of the ARRAYLIST i last stopped at
		// 
		
		public HashMapIterator() {
			count = 0;
			arrayIndex = 0;
			linkedListIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return count<size;
		}

		@Override
		public K next() {
			while (myBucketArray[arrayIndex]==null){				
				arrayIndex++;
			}				
			LinkedList<KVPair> list = myBucketArray[arrayIndex];
			if (linkedListIndex<list.size()){
				KVPair pair = list.get(linkedListIndex);
				linkedListIndex++;				
				count++;
				return pair.key;
			} else {
				arrayIndex++;
				linkedListIndex = 0;
				return next();
			}
		}
		
		public void remove() {
			//unsupported
			return;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		public KVPair(K key, V value){
			this.key = key;
			this.value = value;
		}
	}

}
