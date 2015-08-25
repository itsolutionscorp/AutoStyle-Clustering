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
		// TODO your code here!'
		addDefinitionHelper(myStartingLetters,word,definition);
	}
	public void addDefinitionHelper(HashMap<Character, TrieNode> startingLetters, String word, String definition){
		if(!startingLetters.containsKey(word.charAt(0))){
			TrieNode tobeAdded = new TrieNode();
			if(word.length()==1){
				tobeAdded.myDefinition = definition;
				startingLetters.put(word.charAt(0), tobeAdded);
			}
			else{
				startingLetters.put(word.charAt(0), tobeAdded);
				addDefinitionHelper(startingLetters.get(word.charAt(0)).myNextLetters,word.substring(1),definition);
			}
			
		}
		else{
			addDefinitionHelper(startingLetters.get(word.charAt(0)).myNextLetters,word.substring(1),definition);
		}
	}
	

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		return lookupDefinitionHelper(myStartingLetters,word);
	}

	public String lookupDefinitionHelper(HashMap<Character, TrieNode> startingLetters, String word){
 		Character firstChar = word.charAt(0);
	    if(startingLetters.containsKey(firstChar)){
	    	if(word.length() == 1){
	    		return startingLetters.get(firstChar).myDefinition;
	    	}
	    	else{
	    		return lookupDefinitionHelper(startingLetters.get(firstChar).myNextLetters,word.substring(1));
	    	}
	    }
	    else{
			return null;
		}
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}
	public static void main(String[] args){
		Dictionary a = new Dictionary();
		a.addDefinition("Hi", "Greeting Word");
		a.addDefinition("Dude", "brother");
		
	}
}