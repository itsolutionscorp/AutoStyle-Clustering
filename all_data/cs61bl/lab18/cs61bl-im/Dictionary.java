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
		HashMap<Character, TrieNode> letters = myStartingLetters;
		for (int i = 0; i < word.length(); i ++) {
			if (!letters.containsKey(word.charAt(i))) {
				letters.put(word.charAt(i), new TrieNode());
			}
			
			if (i == word.length() -1) {
				letters.get(word.charAt(i)).myDefinition = definition;
			}
			letters = letters.get(word.charAt(i)).myNextLetters;
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		HashMap<Character, TrieNode> letters = myStartingLetters;
		for (int i = 0; i < word.length(); i++) {
			if (!letters.containsKey(word.charAt(i))) {
				return null;
			}
			if (i == word.length()-1) {
				return letters.get(word.charAt(i)).myDefinition;
			}
			letters = letters.get(word.charAt(i)).myNextLetters;
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
	}
}