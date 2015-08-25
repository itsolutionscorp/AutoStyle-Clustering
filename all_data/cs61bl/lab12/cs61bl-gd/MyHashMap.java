import java.util.ArrayList;
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
	private double loadfactor;
	public ArrayList[] keys;
	private int entry;

 	
	// TODO You may declare new instance variables here

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		size=0;
		loadfactor=DEFAULT_LOAD_FACTOR;
		keys=new ArrayList[capacity];
		
		
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity=initialCapacity;
		size=0;
		loadfactor=DEFAULT_LOAD_FACTOR;
		keys=new ArrayList[capacity];
		
		
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
		capacity=initialCapacity;
		size=0;
		loadfactor=loadFactor;
		keys=new ArrayList[capacity];
		
		
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
	public double loadfactor(){
		return (Math.floor(this.size())/capacity);
	}
	public boolean containsKey(K key) {
		// TODO Complete this!
		int pos = position(key);
			if(keys[pos]!=null){
				for(int n =0;n<keys[pos].size();n++){
					Object ref = keys[pos].get(n);
					if(((KVPair)ref).getKey().equals(key)){
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
		for(int k=0;k<keys.length;k++){
			if(keys[k]!=null){
				for(int n =0;n<keys[k].size();n++){
					Object ref = keys[k].get(n);
					if(((KVPair)ref).getValues().equals(value)){
						return true;
					}
				}
			}
		}return false;
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
		
		if(size<capacity&&this.loadfactor()<loadfactor){
			int pos = position(key);
		size++;
		if(keys[pos]==null){
			keys[pos]=new ArrayList<Object>();
			( keys[pos]).add((new KVPair(key,value)));
			entry++;
			System.out.println(entry);
		}else{if(containsKey(key)){
			ArrayList ref= keys[pos];
			for(int k =0;k<ref.size();k++){
				if(((KVPair)ref.get(k)).getKey().equals(key)){
					V copy = ((KVPair)ref.get(k)).getValues();
					((KVPair)ref.get(k)).changeValues(value);
					size--;
					return copy;}
				}
			}keys[pos].add(new KVPair(key,value));
		}
		return null;
	}else{
		this.expand(capacity*2);
		while(this.loadfactor()>loadfactor){
			this.expand(capacity*2);
		}

		size++;
		int pos = position(key);
		if(keys[pos]==null){
			keys[pos]=new ArrayList<Object>();
			( keys[pos]).add((new KVPair(key,value)));
			entry++;
			System.out.println(entry);
		}else{if(containsKey(key)){
			ArrayList ref= keys[pos];
			for(int k =0;k<ref.size();k++){
				if(((KVPair)ref.get(k)).getKey().equals(key)){
					V copy = ((KVPair)ref.get(k)).getValues();
					((KVPair)ref.get(k)).changeValues(value);
					size--;
					return copy;}
				}
			}keys[pos].add(new KVPair(key,value));
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
		// TODO Complete this!
		if(!this.containsKey(key)){return null;}
		ArrayList ref=keys[this.position(key)];
			if(ref!=null){
				for(int k =0;k<ref.size();k++)
				{
					if(((KVPair)ref.get(k)).getKey().equals(key)){
						V result = ((KVPair)ref.get(k)).getValues();
						ref.remove(k);
						size--;
						if(ref.size()==0){entry--;}
						return result;
					}
				}
			}return null;
		
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		// TODO Complete this!
		if(keys[position(key)]==null){return null;}
		if(keys[position(key)].size()==1){
			if(containsKey(key)){
			return ((KVPair)keys[position(key)].get(0)).getValues(key);}
			return null;
		}
		else{
			for(int k =0;k<keys[position(key)].size();k++){
				V result=((KVPair)keys[position(key)].get(k)).getValues(key);
				if( result!=null){
					return result;
				}
			}
			return null;
		}
		
	}
	public int position(K key){
		return Math.abs(key.hashCode()%this.capacity());
	}
	/**
	 * Expands the underlying array to the given capacity.
	 * 
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		
		// TODO Complete this!
		System.out.println("expanding");
		capacity=newCapacity;
		entry=0;
		ArrayList[] copy= new ArrayList[capacity];
		for(int n =0;n<keys.length;n++){
			if(keys[n]!=null){
			for(int k = 0; k<keys[n].size();k++){
				Object ref= keys[n].get(k);
				int location=Math.abs(((KVPair)ref).getKey().hashCode()%capacity);
			if(copy[location]==null){
				copy[location]=new ArrayList();
				entry++;
				System.out.println(entry);
			}
			copy[location].add(ref);
			}
			}
		}
		keys=copy;
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
		int count;
		int inner;
		public HashMapIterator() {
			// TODO Complete this!
			index=0;
			count=0;
			inner=0;
			
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return index<keys.length;
		}

		@Override
		public K next() {
			// TODO Complete this!
		if(hasNext()){
			K result;
			for(int k=index;k<keys.length;k++){
				
				if(keys[k]!=null){
					for(int n=inner;n<keys[k].size();k++ ){
					Object ref = keys[k].get(n);
					if(ref!=null){
					result=((KVPair)ref).getKey();
					inner=n;
					index=k;
					this.update();
					return result;}
					}
				}
				inner=0;
				
				
			}
			throw new IllegalStateException("out of bound");
			
		
		}throw new IllegalStateException("out of bound");
		}
		public void update(){
				inner++;
				
				if(inner>=keys[index].size()){
					inner=0;
					index++;
				}
				while(index<keys.length&&keys[index]==null){
					index++;
					inner=0;
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
		public KVPair(K k,V v){
		key=k;
		value = v;
			
		}
		public void changeValues(V newV ){
			value = newV;
		}
		public K getKey(){
			return key;
		}
		public V getValues(){
			return value;
		}
		public V getValues(K ke){
			if(ke.hashCode()==key.hashCode()){
				return value;
			}return null;
		}
	}

}
