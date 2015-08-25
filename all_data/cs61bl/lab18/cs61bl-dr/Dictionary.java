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
		if(myStartingLetters.containsKey(word.charAt(0)))
			myStartingLetters.get(word.charAt(0)).addDefinition(word.substring(1), definition);
		
		else{
			TrieNode toPut = new TrieNode();
			myStartingLetters.put(word.charAt(0), toPut);
			toPut.addDefinition(word.substring(1), definition);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if(myStartingLetters.containsKey(word.charAt(0))) 
			return myStartingLetters.get(word.charAt(0)).lookupDefinition(word.substring(1));
		return null;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		private void addDefinition(String word, String definition){
			if(word.isEmpty()) myDefinition = definition;
			else if(myNextLetters.containsKey(word.charAt(0))) myNextLetters.get(word.charAt(0)).addDefinition(word.substring(1), definition);
			else{
				TrieNode toPut = new TrieNode();
				myNextLetters.put(word.charAt(0), toPut);
				toPut.addDefinition(word.substring(1), definition);
			}	
		}
		private String lookupDefinition(String word){
			if(word.isEmpty()) return myDefinition;
			else if(myNextLetters.containsKey(word.charAt(0))) return myNextLetters.get(word.charAt(0)).lookupDefinition(word.substring(1));
			return null;
		}
	}
}
