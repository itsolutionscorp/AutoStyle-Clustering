import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	private HashMap<Character, TrieNode> myStartingLetters;

	public Dictionary() {
		myStartingLetters = new HashMap<Character,TrieNode>();
	}
	//Add our dictionary
	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		
		!// We will map our words to their definitions; word is the key, definition is the value
		HashMap<String, String> dictionary = new HashMap<String, String>();
		dictionary.put("hello"," world");
		dictionary.put("We need to study hard","");

	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		// Add a word
		map.put("word", null);

		// Remove a word
		map.remove("word");

		// Check for the presence of a word
		map.containsKey("word");
		return null;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<Character,TrieNode>();
		}
	}
}