import java.util.HashMap;

//Citation: used code from https://gist.github.com/prettymuchbryce/3719435

public class Dictionary {

	/*
	 * Since a trie node can have so many myNextLetters, the myNextLetters it
	 * has are stored in a map.
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
		if (!hasWord(word)) {
			insert(word, definition);
		}
	}

	public boolean hasWord(String word) {
		if (myStartingLetters.containsKey(word.charAt(0))) {
			if (word.length() == 1) {
				return true;
			}
			return searchFor(word.substring(1),
					myStartingLetters.get(word.charAt(0)));
		} else {
			return false;
		}
	}

	// Recursive method that searches through the Trie Tree to find the value.
	private boolean searchFor(String string, TrieNode node) {
		if (string.length() == 0) {
			return true;
		}
		if (node.myNextLetters.containsKey(string.charAt(0))) {
			return searchFor(string.substring(1),
					node.myNextLetters.get(string.charAt(0)));
		} else {
			return false;
		}
	}

	public void insert(String string, String definition) {
		if (!myStartingLetters.containsKey(string.charAt(0))) {
			myStartingLetters.put(string.charAt(0), new TrieNode());
		}

		insertWord(string.substring(1), definition,
				myStartingLetters.get(string.charAt(0)));
	}

	// Recursive method that inserts a new word into the trie tree.
	private void insertWord(String string, String definition, TrieNode node) {
		TrieNode nextChild;
		if (node.myNextLetters.containsKey(string.charAt(0))) {
			nextChild = node.myNextLetters.get(string.charAt(0));
		} else {
			nextChild = new TrieNode();
			node.myNextLetters.put(string.charAt(0), nextChild);
		}
		if (string.length() == 1) {
			node.myDefinition = definition;
			return;
		} else {
			insertWord(string.substring(1), definition, nextChild);
		}
	}

	public String returnDefinition(String string, TrieNode node) {

		if (string.length() == 1) {
			return node.myDefinition;
		}
		if (node.myNextLetters.containsKey(string.charAt(0))) {
			return returnDefinition(string.substring(1),
					node.myNextLetters.get(string.charAt(0)));
		}else {
			return null;
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		String rtn = null;
		if (!hasWord(word)) {
			return rtn;
		} else {
			TrieNode toSearch = myStartingLetters.get(word.charAt(0));
			rtn = returnDefinition(word.substring(1), toSearch);
		}
		return rtn;
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