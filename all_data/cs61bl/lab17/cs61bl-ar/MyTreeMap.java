import java.util.ArrayList;

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {
    
	private int size; // the number of items that have been put into the map
    public BinarySearchTree<KVPair> myTreeMap ;
	// TODO You may declare new instance variables here
    
	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		// TODO Complete this!
		myTreeMap = new BinarySearchTree<KVPair>();
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		if(myTreeMap.myRoot==null){
			return 0;
		}
		size = myTreeMap.myRoot.getSize()+1;
		return size;
	}

	/**
	 * Returns whether the map contains the given key.
	 */
	public boolean containsKey(K key) {
		// TODO Complete this!
		if(myTreeMap == null){
			return false;
		}
		KVPair temp = new KVPair(key);
		return myTreeMap.contains(temp);
	}

	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		// TODO Complete this!
		if(containsKey(key)){
			V temp = get(key);
			KVPair toPut = new KVPair(key,value);
			REfind(toPut,toPut);
			return temp;
		}
		KVPair putting = new KVPair(key,value);

		myTreeMap.add(putting);
        return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		if(containsKey(key)){
			V temp = get(key);
			KVPair temp1  = new KVPair(key,temp);
			myTreeMap.removes(myTreeMap.myRoot,temp1);
			return temp;
		}
		KVPair temp1 = new KVPair(key);
		myTreeMap.delete(temp1);
		return null;
	}
	public void REfind(KVPair toFind,KVPair toRE){
		BinarySearchTree<KVPair> myTree = myTreeMap;
		if(myTree.myRoot.myItem.compareTo(toFind)>0){
			myTree.myRoot = myTree.myRoot.myLeft;
			myTree.REfind(toFind,toRE); 
		}else if(myTree.myRoot.myItem.compareTo(toFind)==0){
			myTree.myRoot.myItem = toRE;
		}else{
			myTree.myRoot = myTree.myRoot.myRight;
			myTree.REfind(toFind,toRE);
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		if(containsKey(key)){
			KVPair temp = new KVPair(key);
		   temp= myTreeMap.find(temp);
		   return temp.getValue();
		}
		return null;
	}
	
	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable<KVPair>{
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}
		public KVPair(K k){
			key = k;
		}
        public K getKey(){
        	return key;
        }
        public V getValue(){
        	return value;
        }
		public void setValue(V v) {
			value = v;
		}
		
		@Override
		public int compareTo(MyTreeMap<K,V>.KVPair pair) {
			return key.compareTo(pair.key);
		}
	}
	public static void main(String[] args){
		MyTreeMap temp = new MyTreeMap();
		temp.put(7,"H");
		temp.put(3,"D");
		temp.put(0,"A");
		temp.put(1,"B");
		temp.put(5,"F");
		temp.put(10,"k");
		temp.put(8,"I");
		temp.put(9,"J");
		temp.put(12,"M");
		temp.remove(12);
		
		System.out.println(temp.containsKey(12));
		
	}
}
