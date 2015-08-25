import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a tree node can have so many children, the children it has are
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
		for (int n = 0; n < word.length(); n++) {
			myStartingLetters.put(word.charAt(n), new TrieNode());
		}
		myStartingLetters.put(word.charAt(word.length() -1), new TrieNode(definition));
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if ( myStartingLetters.get(word.charAt(word.length()-1)) != null ) {
			return myStartingLetters.get(word.charAt(word.length() -1)).myDefinition;		
		}
		return null;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private TrieNode(String definition) {
			myNextLetters = new HashMap<>();
			myDefinition = definition;
		}
		
		
		
		

	}
}