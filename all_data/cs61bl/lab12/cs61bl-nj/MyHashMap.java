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

	// TODO You may declare new instance variables here
	private LinkedList<KVPair>[] values;
	private double load_factor;
	private int bucketNum;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		size = 0;
		load_factor = DEFAULT_LOAD_FACTOR;
		values = (LinkedList<KVPair>[]) new LinkedList[capacity];
		for (int i = 0;i<values.length;i++) {
			values[i] = new LinkedList<KVPair>();
		}
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		size = 0;
		load_factor = DEFAULT_LOAD_FACTOR;
		values = (LinkedList<KVPair>[]) new LinkedList[capacity];
		for (int i = 0;i<values.length;i++) {
			values[i] = new LinkedList<KVPair>();
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
		load_factor = loadFactor;
		values = (LinkedList<KVPair>[]) new LinkedList[capacity];
		for (int i = 0;i<values.length;i++) {
			values[i] = new LinkedList<KVPair>();
		}
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
		int num = key.hashCode()%capacity;
		while (num<0) {
			num=num+capacity;
		}
		LinkedList<KVPair> check = values[num];
		Iterator iter = check.iterator();
		while (iter.hasNext()) {
			KVPair a = (KVPair)iter.next();
			if (a.key.equals(key)) {
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
		for (int i = 0; i<capacity; i++) {
				Iterator iter = values[i].iterator();
				while (iter.hasNext()) {
					KVPair a = (KVPair)iter.next();
					if (a.value==value) {
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
		// TODO Complete this!
		KVPair toAdd = new KVPair(key, value);
		int num = key.hashCode()%capacity;
		while (num<0) {
			num=num+capacity;
		}
		Iterator iter = values[num].iterator();
		if (values[num].isEmpty()) {
			values[num].add(toAdd);
			size++;
			bucketNum++;
			if (bucketNum/capacity>=load_factor) {
				expand(2*capacity);
			}
			return null;
		} else {
				KVPair a;
				if (containsKey(key)) {
					while (iter.hasNext()) {
						a = (KVPair)iter.next();
						if (a.key.equals(key)){
							V old = a.value;
							a.value = value;
							return old;
						}
					}
					return null;
				} else {
					values[num].add(toAdd);
					size++;
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
		// TODO Complete this!
		int num = key.hashCode()%capacity;
		while (num<0) {
			num=num+capacity;
		}
		if (values[num].isEmpty()) return null;
		Iterator iter = values[num].iterator();
		while (iter.hasNext()) {
			KVPair a = (KVPair)iter.next();
			if (a.key.equals(key)) {
				iter.remove();
				size--;
				if (values[num].isEmpty()) {
					bucketNum--;
				}
				return a.value;
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
		// TODO Complete this!
		int num = key.hashCode()%capacity;
		while (num<0) {
			num=num+capacity;
		}
		if (values[num].isEmpty()) return null;
		Iterator iter = values[num].iterator();
		while (iter.hasNext()) {
			KVPair a = (KVPair)iter.next();
			if (a.key.equals(key)) {
				return a.value;
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
		// TODO Complete this!
		LinkedList<KVPair>[] temp = (LinkedList<KVPair>[])new LinkedList[newCapacity];
		for (int i=0;i<newCapacity;i++) {
			temp[i] = new LinkedList<KVPair>();
		}
		Iterator iter = iterator();
		bucketNum = 0;
		while (iter.hasNext()) {
			K a = (K)iter.next();
			V b = get(a);
			KVPair toAdd = new KVPair(a, b);
			int num = a.hashCode()%newCapacity;
			while (num<0) {
				num=num+newCapacity;
			}
			if (temp[num].isEmpty()) {
				temp[num].add(toAdd);
				bucketNum++;
			} else temp[num].add(toAdd);
		}
		values = temp;
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
		int currIndex;
		Iterator listIter;

		public HashMapIterator() {
			// TODO Complete this!
			currIndex = 0;
			while (currIndex<values.length&&values[currIndex].isEmpty()) {
				currIndex++;
			}
			if (currIndex<=values.length-1) {
				listIter = values[currIndex].iterator();
			}
			
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if (listIter==null) {
				return false;
			}
			if (currIndex==values.length-1) {
				if (listIter.hasNext()) {
					return true;
				} else return false;
			} else {
				if (listIter.hasNext()) {
					return true;
				} else {
					currIndex++;
					while (currIndex<values.length&&values[currIndex].isEmpty()) {
						currIndex++;
					}
					if (currIndex==values.length) {
						return false;
					} else {
						listIter = values[currIndex].iterator();
						return true;
					}
				}
			}
		}

		@Override
		public K next() {
			// TODO Complete this!
			if (hasNext()) {
				KVPair result = (KVPair)listIter.next();
				return result.key;
			} else {
				return null;
			}
		}
		public void remove() {
			
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
		public KVPair(K theKey, V theValue) {
			key = theKey;
			value = theValue;
		}
	}
	
	/*public boolean isOK() {
		if (capacity!=values.length) {
			System.out.println("false capacity");
			return false;
		}
		int count = 0;
		Iterator iter = iterator();
		while (iter.hasNext()) {
			count++;
			iter.next();
		}
		if (count!=size) {
			System.out.println("false size");
			return false;
		}
		if (load_factor<=bucketNum/capacity) {
			System.out.println("no expansion");
			return false;
		}
		int num = 0;
		for (int i=0;i<values.length;i++) {
			if (!values[i].isEmpty()) {
				num++;
			}
		}
		if (num!=bucketNum) {
			System.out.println("false bucketNum");
			return false;
		}
		return true;
	}*/
}
