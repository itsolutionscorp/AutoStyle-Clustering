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
	private double loadFactor;
	private int size; // the number of items that have been put into the map
	private double thres;
	// TODO You may declare new instance variables here
	LinkedList<KVPair>[] arr;

	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		// TODO Complete this!
		capacity = DEFAULT_CAPACITY;
		loadFactor = DEFAULT_LOAD_FACTOR; 
		thres =capacity*loadFactor;
		arr =  new LinkedList[DEFAULT_CAPACITY];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		// TODO Complete this!
		capacity = initialCapacity;
		loadFactor = DEFAULT_LOAD_FACTOR;
		thres =capacity*loadFactor;
		arr = new LinkedList[capacity];
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
	public MyHashMap(int initialCapacity, double initloadFactor) {
		capacity = initialCapacity;
		loadFactor = initloadFactor;
		thres =capacity*loadFactor;
		arr = new LinkedList[capacity];

	}
	public double getThre(){
		return thres;
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
		int hashcode = key.hashCode()%capacity;
		LinkedList<KVPair> tarList = arr[hashcode];
		if(tarList == null){
			return false;
		}
		for(int k =0;k < tarList.size(); k++){
			if(((K) tarList.get(k).getKey()).equals(key)){
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
		for(int i =0; i<arr.length;i++){
			if(arr[i]!=null){
				LinkedList<KVPair> tarList = arr[i];
				for(int j = 0; j <arr[i].size();j++ ){
					if(tarList.get(j).getValue().equals(value)){
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

			int hashcode = key.hashCode()%capacity;
			LinkedList<KVPair> tarList = arr[hashcode];
			if(tarList == null){
				KVPair toBeAdd = new KVPair(key,value);
				arr[hashcode] = new LinkedList();
				arr[hashcode].add(toBeAdd);	
				size++;
			if (size > thres){
					this.expand(capacity*2);
				}
				return null;
			}
			for(int k =0;k < tarList.size(); k++){
				if(tarList.get(k).getKey() == key){
					 V tmpValue =tarList.get(k).getValue(); 
					 tarList.get(k).setValue(value);
					if (size > thres){
						this.expand(capacity*2);
					}
					return tmpValue;
				}
			}
			
			arr[hashcode].add(new KVPair(key, value));
			size++;
			if (size > thres){
				this.expand(capacity*2);
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
			int hashcode = key.hashCode()%capacity;
			LinkedList<KVPair> tarList = arr[hashcode];
			if(tarList == null){
				return null;
			}
			for (int i = 0; i < tarList.size(); i++){
				if(tarList.get(i).getKey().equals(key)){
					V tmpValue = tarList.get(i).getValue();
					tarList.remove(i);
					size--;
					return tmpValue;
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
		int hashcode = key.hashCode()%capacity;
		LinkedList<KVPair> tarList = arr[hashcode];
		if(tarList == null){
			return null;
		}
		for (int i = 0; i < tarList.size(); i++){
			if(tarList.get(i).getKey().equals(key)){
				return tarList.get(i).getValue();
				
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
		LinkedList<KVPair>[] temp = new LinkedList[newCapacity];
		
		Iterator<K> arrIter = this.iterator();
		while (arrIter.hasNext()){
			K newKey = arrIter.next();
			V newValue = this.get(newKey);
			int hashcode = newKey.hashCode()%newCapacity;
			if(temp[hashcode] == null){
				KVPair newPair = new KVPair(newKey, newValue);
				 
				 temp[hashcode] = new LinkedList();
				temp[hashcode].add(newPair);
			}
			else{
				KVPair newPair = new KVPair(newKey, newValue);
				temp[hashcode].add(newPair);
			}
		}
		arr = temp;
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

        int arrIndex;
        int myCount;
        int linkedIndex;
		public HashMapIterator() {
			// TODO Complete this!
		  arrIndex =0;
		  myCount =0;
		  linkedIndex =0;
		}

		@Override
		public boolean hasNext() {
			// TODO Complete this!
			return  myCount < size;
		}

		@Override
		public K next() {
			// TODO Complete this!
			if(hasNext()){
			while(arr[arrIndex]==null){
				arrIndex ++;
			}
			K currentKey = arr[arrIndex].get(linkedIndex).getKey();
			if(linkedIndex == arr[arrIndex].size()-1){
				linkedIndex =0;
				arrIndex ++;
			}
			else{
				linkedIndex ++;
			}
			myCount ++;
			return currentKey;}
			else{
				return null;
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;
		public KVPair(K Key, V Value){
			this.key = Key;
			this.value = Value;
		}
		public K getKey(){
			return key;
		}
		public V getValue(){
			return value;
		}
		public void setValue(V Value){
			this.value = Value;
		}
		/*
		 * TODO Fill this out if you need it. Maybe not required depending on
		 * your implementation.
		 */
	}

}
