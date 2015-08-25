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
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			
			TrieNode t = null;
			
			if (myStartingLetters.containsKey(c)) {
				t = myStartingLetters.get(c);
			} else {
				t = new TrieNode();
				myStartingLetters.put(c, t);
			}
			myStartingLetters = t.myNextLetters;
			}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		return myStartingLetters.get(word);
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