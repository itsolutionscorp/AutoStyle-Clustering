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
		if (myStartingLetters.containsKey(word.charAt(0))) {
				myStartingLetters.get(word.charAt(0))
						.addChild(word, definition);
		} else {
			TrieNode temp = new TrieNode();
			temp.addChild(word, definition);
			myStartingLetters.put(word.charAt(0), temp);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (myStartingLetters.containsKey(word.charAt(0))) {
			return myStartingLetters.get(word.charAt(0)).lookup(word);
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

		private void addChild(String word, String definition) {
			if (word.length() == 1) {
				myDefinition = definition;
			} else if (myNextLetters.containsKey(word.charAt(1))) {
				myNextLetters.get(word.charAt(1)).addChild(word.substring(1),
						definition);
			} else {
				TrieNode temp = new TrieNode();
				temp.addChild(word.substring(1), definition);
				myNextLetters.put(word.charAt(1), temp);
			}
		}

		private String lookup(String word) {
			if (word.length() == 1) {
				return myDefinition;
			} else if (myNextLetters.containsKey(word.charAt(1))) {
				return myNextLetters.get(word.charAt(1)).lookup(
						word.substring(1));
			} else
				return null;
		}
	}
}