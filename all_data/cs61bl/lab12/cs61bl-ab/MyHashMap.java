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
	private LinkedList<KVPair>[] buckets;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		size = 0;
		capacity = DEFAULT_CAPACITY;
		buckets = new LinkedList[capacity];
		for(int i = 0; i<capacity; i++) {
			buckets[i]= new LinkedList<KVPair>();
		}
		load_factor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		size = 0;
		capacity = initialCapacity;
		buckets = new LinkedList[capacity];
		for(int i = 0; i<capacity; i++) {
			buckets[i]= new LinkedList<KVPair>();
		}
		load_factor = DEFAULT_LOAD_FACTOR;
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
         size = 0;
         capacity = initialCapacity;
         buckets = new LinkedList[capacity];
         for(int i = 0; i<capacity; i++) {
 			buckets[i]= new LinkedList<KVPair>();
 		}
         load_factor = loadFactor;
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
		for (KVPair p: buckets[key.hashCode()%capacity]) {
			if(p.getKey()==key) {
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
		for(int j=0; j<capacity; j++){
		    for (KVPair p: buckets[j]) {
			    if(p.getValue()==value) {
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
		if(containsKey(key)) {
			V preValue=null;
			for (KVPair p: buckets[key.hashCode()%capacity]) {
				if(p.getKey()==key) {
					preValue = p.getValue();
					p.setValue(value);
					break;
				}
			}
			return preValue;
		}else {
			if(size()+1>=load_factor*capacity) {
				expand(capacity*2);
			}
			buckets[key.hashCode()%capacity].add(new KVPair(key, value));
			size++;
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
		// TODO Complete this!
        for (KVPair p: buckets[key.hashCode()%capacity]) {
			if(p.getKey()==key) {
				//V preValue = p.getValue();
				buckets[key.hashCode()%capacity].remove(p);
				size--;
				return p.getValue();
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
		for (KVPair p: buckets[key.hashCode()%capacity]) {
			if(p.getKey()==key) {
				return p.getValue();
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
		LinkedList<KVPair>[] newBuckets = new LinkedList[newCapacity];
		for(int i = 0; i<newCapacity; i++){
			newBuckets[i]= new LinkedList<KVPair>();
		}
		for (LinkedList<KVPair> p: buckets) {
			for (KVPair pair: p) {
				int newIndex = pair.getKey().hashCode()%newCapacity;
				newBuckets[newIndex].add(pair);
			}
		}
		buckets = newBuckets;
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
		private int currentIndex;
		private Iterator<KVPair> iter;
		public HashMapIterator() {
			// TODO Complete this!
			for (int i = 0; i<capacity; i++) {
				if (buckets[i].size()!=0) {
					currentIndex = i;
					iter = buckets[i].iterator();
					break;
				}
			} 
		}

		
		public boolean hasNext() {
			// TODO Complete this!
			if (iter==null) {
				return false;
			} else {
				return iter.hasNext();
			}
		}

		
		public K next() {
			// TODO Complete this!
			K result = ((KVPair)iter.next()).getKey();
			if (!iter.hasNext()) {
				for(int i= currentIndex+1; i<capacity; i++) {
					if (buckets[i].size()!=0) {
						iter = buckets[i].iterator();
						currentIndex=i;
						return result;
					}
				}
				iter = null;
			}
			return result;
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
		public KVPair(K myKey, V myValue) {
			key = myKey;
			value = myValue;
		}
		public V setValue(V myValue) {
			V temp = value;
			value = myValue;
			return temp;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
	}
	
	private void printMap() {
		for(int i = 0; i<capacity; i++){
			if(buckets[i].size()!=0){
			    for(KVPair p:buckets[i]){
			        System.out.println(i+" "+p.getKey().toString()+" "+p.getValue().toString());
			    }
			}else {
				System.out.println(i+" null");
			}
		}
	}
	public static void main(String [] args) {
		MyHashMap<String, Integer> myMap = new MyHashMap<String, Integer>(5);
		myMap.put("a", 1);
		System.out.println(myMap.containsKey("a"));
		myMap.put("b", 2);
		//System.out.println(myMap.remove("a"));
		myMap.put("c", 3);
		myMap.printMap();
		myMap.put("j", 4);
		myMap.put("k", 5);
		myMap.printMap();
		myMap.put("a", 100);
		myMap.printMap();
		int check = myMap.remove("a");
		myMap.printMap();
		System.out.println(check==100);
		
		Iterator<String> iter = myMap.iterator();
		for(int i = 0; i <6; i++) {
		    System.out.println(iter.hasNext());
		    if(iter.hasNext()) {
		    	System.out.println(iter.next());
		    }else {
		    	break;
		    }
		    
		}
		System.out.println("a".hashCode());
		System.out.println("a".hashCode());		
	}

}
