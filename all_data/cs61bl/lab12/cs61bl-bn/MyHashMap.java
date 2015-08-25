import java.util.Iterator;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map
	private V value;
	private V valueToBeRemoved;
	private K key;
	private double loadfactor;
	private KVPair[] bucketArray;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
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
		if (initialCapacity <= 0) {
			throw new IllegalArgumentException("capacity should be more than 0");
		}
		capacity = initialCapacity;
		loadfactor = loadFactor;
		size = 0;
		bucketArray = (KVPair[]) new MyHashMap.KVPair[capacity];

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
		return capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		if (bucketArray[key.hashCode() % capacity] == null) {
			return false;
		}
		return bucketArray[key.hashCode() % capacity].getK().equals(key);

	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		for (int k = 0; k < capacity; k++) {
			if (bucketArray[k] != null && bucketArray[k].getV().equals(value)) {
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
		this.key = key;
		this.value = value;
		Iterator<K> iter = iterator();
		iter.next();
		if (size++ > capacity * loadfactor) {
			expand(capacity * 2);
		}
		return value;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		this.key = key;
		Iterator<K> iter = iterator();
		iter.remove();
		size--;
		return valueToBeRemoved;
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		this.key = key;
		Iterator<K> iter = iterator();
		int position = key.hashCode() % capacity;

		if (bucketArray[position] == null) {
			throw new IllegalArgumentException(
					"bucket array doesnt have element " + key);

		}
		if (!bucketArray[position].getK().equals(key)) {
			if (position == capacity - 1) {
				position = -1;
			}
			int index = position + 1;
			while (bucketArray[index] != null) {
				if (bucketArray[index].getK().equals(key)) {
					return bucketArray[index].getV();
				}
				index++;
			}
			throw new IllegalArgumentException(
					"bucket array doesnt have element " + key);
		}
		return bucketArray[position].getV();

	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		// TODO Complete this!
		K fakeK;
		int fakePosition;
		if (newCapacity < capacity) {
			throw new IllegalArgumentException(
					"newCapacity should be more than current capacity");
		}
		KVPair[] newBucketArray = (KVPair[]) new MyHashMap.KVPair[newCapacity];

		// rehash
		for (int s = 0; s < capacity; s++) {
			if (bucketArray[s] != null) {

				fakeK = bucketArray[s].getK();
				fakePosition = fakeK.hashCode() % newCapacity;

				while (newBucketArray[fakePosition] != null) {
					fakePosition++;
				}
				newBucketArray[fakePosition] = new KVPair(fakeK,
						bucketArray[s].getV());
			}
		}
		capacity = newCapacity;
		bucketArray = newBucketArray;
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
		int position;
		int count;

		public HashMapIterator() {
			position = key.hashCode() % capacity;
			count = 0;
		}

		@Override
		public boolean hasNext() {

			return (count != capacity);
		}

		@Override
		public K next() {
			if (hasNext()) {
				System.out.println("position: " + position + "   count: "
						+ count);

				if (bucketArray[position] != null) { // collision handling
					if (bucketArray[position].getK().equals(key)) { // true if
																	// both keys
																	// are same
						bucketArray[position].setV(value);
						return key;
					}

					if (position == capacity - 1) {
						position = -1;
					}
					position++;
					count++;
					next();
				}
				bucketArray[position] = new KVPair(key, value);
				return key;
			} else {
				throw new IllegalArgumentException(
						"bucket array is now out of capacity. please expand your bucket.");
			}

		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			while (!(bucketArray[position].getK().equals(key))) {
				if (position == capacity - 1) {
					position = -1;
				}
				position++;
			}
			valueToBeRemoved = bucketArray[position].getV();
			bucketArray[position] = null;

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

		public K getK() {
			return key;
		}

		public V getV() {
			return value;
		}

		public void setV(V value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		// MyHashMap<Integer, Integer> myHash = new MyHashMap<Integer,
		// Integer>(4);
		// myHash.put(0, 0);
		// System.out.println("0    " + myHash.capacity);
		//
		// myHash.put(1, 19);
		// System.out.println("1    " + myHash.capacity);
		//
		// myHash.put(2, 29);
		// System.out.println("2    " + myHash.capacity);
		//
		// myHash.put(3, 30);
		// System.out.println("3    " + myHash.capacity);
		//
		// myHash.put(4, 19);
		// System.out.println("4    " + myHash.capacity);
		//
		// myHash.put(5, 29);
		// System.out.println("5    " + myHash.capacity);
		//
		// myHash.put(6, 30);
		// System.out.println("6    " + myHash.capacity);
		//
		// myHash.put(7, 19);
		// System.out.println("7    " + myHash.capacity);
		//
		// myHash.put(8, 29);
		// System.out.println("8    " + myHash.capacity);
		//
		// myHash.put(9, 30);
		// System.out.println("9    " + myHash.capacity);
		// System.out.println(myHash.get(3));

		MyHashMap<String, String> myHashs = new MyHashMap<String, String>(1);
		myHashs.put("eifu", "19");

		myHashs.expand(2);

		myHashs.put("aaa", "29.1");

		System.out.println("    removed    " + myHashs.remove("aaa"));
		myHashs.put("aaa", "30");
		System.out.println(myHashs.get("aaa"));

		System.out.println(myHashs.size());
	}
}
