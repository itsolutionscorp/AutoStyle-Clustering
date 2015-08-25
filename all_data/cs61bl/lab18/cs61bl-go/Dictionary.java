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
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			myStartingLetters.put(word.charAt(0), new TrieNode());
		}
		TrieNode a = myStartingLetters.get(word.charAt(0));
		for (int i = 1; i < word.length(); i++) {
			a.myNextLetters.put(word.charAt(i), new TrieNode());
			a = a.myNextLetters.get(word.charAt(i));
		}
		a.myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		TrieNode a = myStartingLetters.get(word.charAt(0));
		for (int i = 1; i < word.length(); i++) {
			a = a.myNextLetters.get(word.charAt(i));
		}
		if (a.myDefinition != null) {
			return a.myDefinition;
		} else {
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
}