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
		char[] toChar = word.toCharArray();
		TrieNode temp = new TrieNode();
		if (toChar.length != 0) {
			if (myStartingLetters.get(toChar[0]) == null) {
				myStartingLetters.put(toChar[0], new TrieNode());
			}
			temp = myStartingLetters.get(toChar[0]);
			for (int i = 1; i < toChar.length; i++) {
				if (temp != null) {
					if (temp.myNextLetters.get(toChar[i]) == null) {
						temp.myNextLetters.put(toChar[i], new TrieNode());
					}
					temp = temp.myNextLetters.get(toChar[i]);
				}

			}
			temp.myDefinition = definition;
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		char[] toChar = word.toCharArray();
		if (toChar.length != 0) {
			TrieNode temp = myStartingLetters.get(toChar[0]);
			if (temp != null) {
				for (int i = 1; i < toChar.length; i++) {
					temp = temp.myNextLetters.get(toChar[i]);
					if (temp == null) {
						return null;
					}
				}
				return temp.myDefinition;
			}
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