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

	// You may declare new instance variables here
	private double loadf;
	private KVPair[] line;
	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		capacity = DEFAULT_CAPACITY;
		loadf = DEFAULT_LOAD_FACTOR;
		line = (MyHashMap<K, V>.KVPair[]) new Object[capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		capacity = initialCapacity;
		loadf = DEFAULT_LOAD_FACTOR;
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
		loadf = loadFactor;
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
		int spot = (int) key.hashCode()%capacity;
		if(line[spot]!=null && line[spot].key==key){
			return true;
		}else{
			Iterator<K> iter = iterator(spot);
			while(iter.hasNext()){
				if (iter.next()==key){				
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
		
		for (int i= 0; i < line.length; i++){
			if (line[i] != null){
				if(line[i].value==value){
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
		
		int spot = (int) key.hashCode()%capacity;
		KVPair toAdd = new KVPair(key, value);
		if (line[spot]==null){	
			line[spot] = toAdd;
			size++;
			
		}else if(line[spot].key==key){
			V rtn = line[spot].value;
			line[spot].setv(value);
			return rtn;
		}else{			
				while(spot+1 < capacity ){
					spot++;
					if (line[spot]==null){
						line[spot]=toAdd;
						break;
					}if(spot == capacity-1){
						spot = -1;
					}
				}
				size++;		
		}	
		
		if (size/capacity > loadf){
			expand(capacity*2);
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
		int spot = (int) key.hashCode()%capacity;

		if(line[spot].key==key){
				V rtn = line[spot].value;
				line[spot]=null;
				return rtn;
		}else{
			Iterator<K> iter = iterator(spot);
			while(iter.hasNext()){
				if (iter.next()==key){
					V rtn = line[spot].value;
					line[spot]=null;
					return rtn;
				}
				spot++;
			}return null;				
		}
		
		
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		int spot = (int) key.hashCode()%capacity;
		if(line[spot]==null){
			return null;
			
		}else if (line[spot].key==key){
			return line[spot].value;
			
		}else{
			Iterator<K> iter = iterator(spot);
			while(iter.hasNext()){
				if (iter.next()==key){	
					return line[spot].value;
					}
				spot++;
			}
		}return null;
		
	}

	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		
		MyHashMap<K, V> resized = new MyHashMap<K, V>(newCapacity, loadf);
		for (int i=0; i< line.length; i++){
			if (line[i]!=null){
				resized.put(line[i].key, line[i].value);
			}
		}
		line = resized.line;
		
	}

	/**
	 * Returns an iterator over the keys of this map.
	 */
	public Iterator<K> iterator(int ind) {
		return new HashMapIterator(ind);
	}
	@Override
	public Iterator<K> iterator() {
		
		return new HashMapIterator();
	}

	/**
	 * An iterator for the keys of the enclosing map.
	 */
	private class HashMapIterator implements Iterator<K> {
		
		
		int index;
		
		
		public HashMapIterator(int ind) {////////has it start iterating at a good index
			index = ind;
		}
		public HashMapIterator(){
			index = 0;
		}

		@Override
		public boolean hasNext() {
			if (size/capacity < loadf && line[index]!=null){////////no next if next null
				return true;
			}
			return false;
		}

		@Override
		public K next() {

				if(index+1 == line.length){
					index=0;
					return line[line.length-1].key;
				}else{
					index++;
					return line[index-1].key;	
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
		 *  Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
		public KVPair(K newkey, V newvalue){
			key = newkey;
			value = newvalue;
		}
		
		public void setv(V newV){
			value = newV;
		}
		public int hashCode(){
			return key.hashCode();
		}
	}

	

}
