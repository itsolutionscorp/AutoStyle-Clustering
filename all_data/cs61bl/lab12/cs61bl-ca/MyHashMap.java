import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Pair;

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
	ArrayList[] PairKV;
	// TODO You may declare new instance variables here
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
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
		 if (initialCapacity < 0)
	            throw new IllegalArgumentException("Illegal initial capacity: " +
	                                               initialCapacity);
		capacity = initialCapacity;
		this.loadFactor = loadFactor;
		size = 0;
		PairKV = new ArrayList[capacity];
		int i = 0;
		while (i < capacity) {
			PairKV[i] = new ArrayList(); 
			i++;
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
	
	public double loadFactor() {
		return this.loadFactor;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		// TODO Complete this!
		int hash = hash(key.hashCode());
		int i = indexFor(hash, capacity);
		if (PairKV[i].isEmpty()) {
			return false;
		} else {
			int idx = 0;
			while (idx < PairKV[i].size()) {
				if (((Pair) PairKV[i].get(idx)).getKey().equals(key)) {
					return true;
				}
				idx++;
			}
			return false;
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		int i = 0;
		while (i < capacity) {
			int idx = 0;
			while (idx < PairKV[i].size()) {
				if (((Pair) PairKV[i].get(idx)).getValue().equals(value)) {
					return true;
				}
				idx++;
			}
			i++;
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
	public int hash(int h) {
		h = h % capacity;
		return h;
	}

	public int indexFor(int h, int length) {
		return h & (length - 1);
	}
	
	public V put(K key, V value) {
		// TODO Complete this!
		int hash = hash(key.hashCode());
		int i = indexFor(hash, capacity);
//		System.out.println("put " + key + " at position : " + i);
//		System.out.println(i);
//		System.out.println("might be null : " + PairKV[i]);
		if (size+1 >= capacity) {
			expand((int) (capacity/loadFactor));
		}
		if (!PairKV[i].isEmpty()) {
			if (containsKey(key)) {
				int idx = 0;
				while (idx < PairKV[i].size()) {
					if (((Pair) PairKV[i].get(idx)).getKey().equals(key)) {
						V prev = (V) ((Pair) PairKV[i].get(idx)).getValue();
						PairKV[i].set(idx, new Pair(key, value));
						return prev;
					}
					idx++;
				}
			} else {
				PairKV[i].add(new Pair(key, value));
				size++;
				return null;
			}
		}
		Pair toBeAdded = new Pair(key, value);
		PairKV[i].add(toBeAdded);
		size ++;
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		if (!containsKey(key)) {
			System.out.println("Do not have this key");
			return null;
		}
		int hash = hash(key.hashCode());
		int i = indexFor(hash, capacity);
		int idx = 0;
		while (idx < PairKV[i].size()) {
			if (((Pair) PairKV[i].get(idx)).getKey().equals(key)) {
				Pair pre = (Pair) PairKV[i].get(idx);
				PairKV[i].remove(idx);
				size -= 1;
				return (V) pre.getValue();
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
		if (!containsKey(key)) {
			System.out.println("Do not have this key");
			return null;
		}
		int hash = hash(key.hashCode());
		int i = indexFor(hash, capacity);
		int idx = 0;
		while (idx < PairKV[i].size()) {
			if ( (((Pair) PairKV[i].get(idx)).getKey().equals(key))) {
				V value = (V) ((Pair) PairKV[i].get(idx)).getValue();
				return value;
			}
			idx++;
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
		ArrayList[] temp = PairKV;
		this.PairKV = new ArrayList[newCapacity];
		this.capacity = newCapacity;
		this.size = 0;
		int iii = 0;
		while (iii < capacity) {
			PairKV[iii] = new ArrayList(); 
			iii++;
		}
		for (ArrayList a : temp) {
			if (!a.isEmpty()) {
				int i = 0;
				while (i < a.size()) {
					K key = (K) ((Pair) a.get(i)).getKey();
					V value = (V) ((Pair) a.get(i)).getValue();
					this.put(key, value);
					i++;
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
		int nextidx;
		int idxinsidearray;
		public HashMapIterator() {
			// TODO Complete this!
			nextidx = 0;
			idxinsidearray = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			int lastsize = PairKV[size-1].size();
//			System.out.println("lastsize : " + lastsize);
//			System.out.println("nextidx : " + nextidx);
//			System.out.println("capacity : " + capacity);
//			System.out.println("idxinsidearray : " + idxinsidearray);
			if (nextidx > capacity-1 && idxinsidearray > lastsize-1) {
				return false;
			}else {
				return true;
			}
		}

		@Override
		public K next() {
			// TODO Complete this!
			ArrayList[] temp = PairKV;
			K keytoreturn = null;
			if (hasNext()) {
				if (!PairKV[nextidx].isEmpty()) {
					keytoreturn = (K) ((Pair) PairKV[nextidx].get(idxinsidearray)).getKey();
					idxinsidearray++;
					if (idxinsidearray == PairKV[nextidx].size()) {
						idxinsidearray = 0;
						nextidx++;
					}
					return keytoreturn;
				} else {
					nextidx++;
					return next();
				}
			} else {
				System.out.println("There is no more elements");
				return null;
			}
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
	
	public static void main(String[] args) {
//		MyHashMap test = new MyHashMap();
//		test.put("monkey", 100);
//		test.put("duck", 60);
//		test.put("chicken", 30);
//		test.put("horse", 800);
//		test.put("dude", 400);
//		test.put("yo", 30);
//		test.put("hjh", 200);
//		test.put("cvs", 70);
//		test.put("lsy", 20);
//		test.put("tangxin", 120);
//		test.put("bali", 690);
//		test.put("cvs1", 90);
//		test.put("cvs2", 90);
//		test.put("cvs3", 90);
//		test.put("cvs4", 90);
//		test.put("cvs5", 90);
//		test.put("cvs6", 90);
//		test.put("cvs7", 90);
//		test.put("cvs8", 90);
//		test.put("cvs9", 90);
//		test.put("cvs346", 90);
//		test.put("cvs3456", 90);
//		test.put("cvs3465636", 90);
//		test.put("cvs222", 90);
//		test.put("cvs22222", 90);	
//		test.put("cvs1111", 90);
//		test.put("cvs4444", 90);
//		test.put("cvs3434", 90);	
//		test.put("cvs213131313", 90);
//		test.put("cvadas", 90);
//		test.put("cvsaaddds", 90);
//		test.put("cvwadaws", 90);
//		System.out.println(test.size());
		
		
//		
//		Integer b = 15;
//		System.out.println(b.hashCode());
//		System.out.println(b.hashCode()%8);
//		
	}

}
