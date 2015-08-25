import java.util.ArrayList;
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
	
	private double load_factor;
	private LinkedList<KVPair>[] bucket_array;
	

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		load_factor = DEFAULT_LOAD_FACTOR;
		bucket_array = new LinkedList[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		load_factor = DEFAULT_LOAD_FACTOR;
		bucket_array = new LinkedList[capacity];
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
		load_factor = loadFactor;
		bucket_array = new LinkedList[capacity];
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
		if (get(key) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		// TODO Complete this!
		for (LinkedList<KVPair> i : bucket_array) {
			if (i != null) {
				for (int j = 0; j < i.size(); j++) {
					if (i.get(j).getValue() == value) {
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
		// TODO Complete this!
		int index = hash(key.hashCode());
		if (get(key) != null) {
			LinkedList<KVPair> bucket = bucket_array[index];
			for (KVPair i : bucket) {
				if (i.getKey().equals(key)) {
					V old = i.getValue();
					i.setValue(value);
					return old;
				}
			}
		} else {
			if (size + 1 > capacity * load_factor) {
				expand(capacity + 10);
				return put(key, value);
			}
			else if (bucket_array[index] == null) {
				bucket_array[index] = new LinkedList<KVPair>();
			}
			bucket_array[index].add(new KVPair(key, value));
			size++;
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
		// TODO Complete this!
		int index = hash(key.hashCode());
		if (bucket_array[index] != null) {
			LinkedList<KVPair> bucket = bucket_array[index];
			for (KVPair i : bucket) {
				if (i.getKey().equals(key)) {
					V old = i.getValue();
					bucket.remove(i);
					size--;
					return old;
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
		// TODO Complete this!
		int index = hash(key.hashCode());
		if (bucket_array[index] != null) {
			LinkedList<KVPair> bucket = bucket_array[index];
			for (KVPair i : bucket) {
				if (i.getKey().equals(key)) {
					return i.getValue();
				}
			}
		}
		return null;
	}
	
	public int hash(int hcode) {
		if (hcode % 10 >= bucket_array.length) {
			return hash((hcode % 10) / 2);
		} 
		if (hcode < 0) {
			return hash(Math.abs(hcode));
		} else {
			return hcode % 10;
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
		capacity = newCapacity;
		size = 0;
		LinkedList<KVPair>[] bucket_array2 = bucket_array;
		bucket_array = new LinkedList[capacity];
		for (LinkedList<KVPair> link : bucket_array2) {
			if (link != null) {
				for (int i = 0; i < link.size(); i++) {
					put(link.get(i).getKey(), link.get(i).getValue());
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

		private int array_count;
		private int ll_count;
		private int item_count;
		private LinkedList<KVPair> curr_ll;
		public HashMapIterator() {
			// TODO Complete this!
			array_count = 0;
			ll_count = 0;
			item_count = 0;
			curr_ll = bucket_array[0];
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return item_count < size();
		}

		@Override
		public K next() {
			// TODO Complete this!
			while (curr_ll == null & array_count < capacity) {
				array_count++;
				curr_ll = bucket_array[array_count];
			}
			if (ll_count == curr_ll.size()) {
				ll_count = 0;
				array_count++;
				curr_ll = bucket_array[array_count];
				while (curr_ll == null & array_count < capacity) { //ensures the current linked list is not null
					array_count++;
					curr_ll = bucket_array[array_count];
				}

			}
			ll_count++;
			item_count++;
			return curr_ll.get(ll_count - 1).getKey();
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
		public KVPair(K k, V v) {
			key = k;
			value = v;
			
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setKey(K k) {
			key = k;
		}
		
		public void setValue(V v) {
			value = v;
		}
	}
	public static void main(String[] args) {
		MyHashMap a = new MyHashMap(2);
		a.put("Steven", "Eric");
		a.put("a", 2);
		System.out.println(a.get("a"));
		a.put("b", 3);
		a.put("a", 4);
		System.out.println(a.get("a"));
		a.remove("b");
		a.remove("b");
		System.out.println(a.get("Steven"));
		
		for (int i = 0; i <= 10; i++) {
			a.put(i, i);
		}
		System.out.println(a.get(10));
		
		/* ArrayList b = new ArrayList();
		b.add(1);
		b.add(3);
		b.add(10);
		System.out.println(b.get(0).hashCode());
		System.out.println(b.get(1).hashCode());
		System.out.println(b.hashCode()); */
		
		
	}
}
