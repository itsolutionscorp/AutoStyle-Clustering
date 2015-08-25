import static org.junit.Assert.assertTrue;

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
	private LinkedList<KVPair>[] map;
	private double factor;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		map = new LinkedList[DEFAULT_CAPACITY];
		capacity = DEFAULT_CAPACITY;
		size = 0;
		factor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		map = new LinkedList[initialCapacity];
		capacity = initialCapacity;
		size = 0;
		factor = DEFAULT_LOAD_FACTOR;
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
		if(loadFactor<0 || loadFactor>1){
			throw new IllegalArgumentException("loadFactor invalid");
		}
		map = new LinkedList[initialCapacity];
		capacity = initialCapacity;
		size = 0;
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
		LinkedList<KVPair> list = map[Math.abs(key.hashCode()) % capacity] ;
		if (list != null){
			for(int i = 0;i<list.size();i++){
				KVPair p = (KVPair) list.get(i);
				if(p.getKey().equals(key)){
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
		// TODO Complete this!
		for(LinkedList<KVPair> s : map){
			if(s!=null){
				for(int i =0;i<s.size();i++){
					KVPair p = (KVPair) s.get(i);
					if(p.getValue()==null){
						if(value == null){
							return true;
						}
					}
					else if(p.getValue().equals(value)){
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
		size++;
		if(size>capacity*factor){
			expand(capacity*2);
		}
		LinkedList<KVPair> list = map[Math.abs(key.hashCode()) % capacity];
		if(containsKey(key)){
			for(int i = 0;i<list.size();i++){
				KVPair p = (KVPair) list.get(i);
				if(p.getKey().equals(key)){
					V val = p.getValue();
					p.setValue(value);
					return val;
				}
			}
		}
		if(list==null){
			map[Math.abs(key.hashCode()) % capacity] = new LinkedList<KVPair>();
		}
		map[Math.abs(key.hashCode()) % capacity].add(new KVPair(key, value));
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
		LinkedList<KVPair> list = map[Math.abs(key.hashCode()) % capacity];
		if(list!=null){
			for(int i = 0;i<list.size();i++){
				KVPair p = (KVPair) list.get(i);
				if(p.getKey().equals(key)){
					size--;
					V value = p.getValue();
					list.remove(i);
					return value;
				}
			}
			return null;
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
		LinkedList<KVPair> list = map[Math.abs(key.hashCode()) % capacity];
		if(list!=null){
			for(int i = 0;i<list.size();i++){
				KVPair p = (KVPair) list.get(i);
				if(p.getKey().equals(key)){
					return p.getValue();
				}
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
		capacity = newCapacity;
		LinkedList<KVPair>[] newMap = new LinkedList[capacity];
		for(LinkedList<KVPair> s: map){
			if(s!=null){
				for(int i =0;i<s.size();i++){
					KVPair p = (KVPair)s.get(i);
					int newCode = Math.abs(p.getKey().hashCode()) % capacity;
					if(newMap[newCode]==null){
						newMap[newCode] = new LinkedList<KVPair>();
					}
					newMap[newCode].add(new KVPair(p.getKey(), p.getValue()));
				}
			}
		}
		map = newMap;
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
		private int bucketIndex;
		private int arrIndex;

		public HashMapIterator() {
			// TODO Complete this!
			bucketIndex = 0;
			for(int i = 0;i<capacity;i++){
				if(map[i]!=null){
					arrIndex = i;
					break;
				}
			}
			if(map[arrIndex] == null){
				throw new IllegalStateException("empty collection");
			}
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			if(bucketIndex<map[arrIndex].size()){
				return true;
			}
			for(int i = arrIndex+1;i<capacity;i++){
				if(map[i]!=null){
					return true;
				}
			}
			return false;
		}

		@Override
		public K next() {
			// TODO Complete this!
			if(bucketIndex<map[arrIndex].size()){
				KVPair p = map[arrIndex].get(bucketIndex);
				bucketIndex++;
				return p.getKey();
			}
			else{
				for(int i = arrIndex+1;i<capacity;i++){
					if(map[i]!=null){
						arrIndex = i;
						KVPair p = map[arrIndex].get(0);
						bucketIndex = 1;
						return p.getKey();
					}
				}
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
		public KVPair(K key, V value){
			if(key==null){
				throw new IllegalArgumentException("key can't be null");
			}
			this.key = key;
			this.value = value;
		}
		
		public K getKey(){
			return key;
		}
		
		public V getValue(){
			return value;
		}
		
		public void setValue(V value){
			this.value = value;
		}
	}
	
	/*public static void main(String args[]){
		/*MyHashMap h = new MyHashMap(3, 0.7);
		h.put("a", new Integer(1));
		System.out.println(h.capacity());
		h.put("b", new Integer(2));
		System.out.println(h.capacity());
		h.put("c", new Integer(3));
		System.out.println(h.capacity());
		System.out.println(h.put("z", new Integer(8)));
		System.out.println(h.put("d", new Integer(4)));
		System.out.println(h.put("d", new Integer(5)));
		System.out.println(h.put("a", new Integer(6)));
		for(int i =0; i< h.capacity;i++){
			System.out.println(h.map[i]);
		}
		System.out.println(h.capacity());
		System.out.println(h.containsValue(4));
		System.out.println(h.remove("a"));
		System.out.println(h.get("a"));
		System.out.println(h.remove("d"));
		System.out.println(h.remove("c"));
		System.out.println(h.get("b"));
		System.out.println(h.put("b",new Integer(8)));
		System.out.println(h.get("b"));
		
		
		MyHashMap s = new MyHashMap(5, 0.8);
		s.put("a", new Integer(1));
		s.put("e", new Integer(2));
		s.put("t", new Integer(3));
		s.put("yu", new Integer(2));
		s.put("uuioiou", new Integer(1));
		System.out.println("a".hashCode());
		System.out.println("e".hashCode());
		System.out.println("t".hashCode());
		System.out.println("yu".hashCode());
		System.out.println("uuioiou".hashCode());
		for(int i =0;i<s.capacity;i++){
			System.out.println(s.map[i]);
		}
		Iterator i =  s.iterator();
		
		System.out.println(i.next());
		System.out.println(i.next());
		System.out.println(i.hasNext());
		System.out.println(i.hasNext());
		System.out.println(i.next());
		System.out.println(i.hasNext());
		System.out.println(i.next());
		System.out.println(i.hasNext());
		System.out.println(i.next());
		System.out.println(i.hasNext());
		h.put("a", new Integer(1));
		h.put("v", new Integer(6));
		
		h.put("g", new Integer(10));
		h.put("m", new Integer(11));
		for(int i =0; i< h.capacity;i++){
			System.out.println(h.map[i]);
		}
		System.out.println("a".hashCode());
		System.out.println("v".hashCode());
		System.out.println("g".hashCode());
		
	}*/

}
