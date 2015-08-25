import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	private HashMap<Character, TrieNode> myStartingLetters;

	public Dictionary() {
		myStartingLetters = new HashMap<>();
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		// TODO your code here!
		if(word.length()==1){
			TrieNode next = new TrieNode();
			next.add(word.substring(1),definition);
			myStartingLetters.put(word.charAt(0),next);
			
		}
		else
		if(myStartingLetters.containsKey(word.charAt(0))){
			myStartingLetters.get(word.charAt(0)).add(word.substring(1), definition);
		}else{
			TrieNode next = new TrieNode();
			next.add(word.substring(1),definition);
			myStartingLetters.put(word.charAt(0), next);
		}
		
		
	
		
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode temp;
		if(myStartingLetters.containsKey(word.charAt(0))){
			System.out.println("Found first");
			temp = myStartingLetters.get(word.charAt(0));
		}else{
			return null;
		}
		for(int i =1;i<word.length()-1;i++){
			
			char cur = word.charAt(i);
			if(temp==null){
				return null;
			}
			temp=temp.get(cur);
			System.out.println("Found "+cur);
		}
		if(temp==null){
			return null;
		}
		
			return temp.myDefinition;
		
		
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		public String Definition(){
			return myDefinition;
		}
		public void add(String word, String Definition){
			if(word.length()==0){
				myDefinition = Definition;
				myNextLetters.put(null,null);
				return;
			}
		    if(word.length()==1){
		    	myDefinition =Definition;
		    	myNextLetters.put((char) word.indexOf(0),null);
		    }
		    else{
		    	
		    	if(myNextLetters.containsKey(word.charAt(0))){
		    		myNextLetters.get(word.charAt(0)).add(word.substring(1),Definition);
		    	}else{
		    	TrieNode  next= new TrieNode();
		    		next.add(word.substring(1),Definition);
		    		myNextLetters.put(word.charAt(0), next);}
		    		
		    		
		    }
			
		}
		public TrieNode get(char a){
			if(myNextLetters.containsKey(a)){
				return myNextLetters.get(a);
			}
			return null;
		}
	
	}
}