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
		char firstChar = word.charAt(0);
		if (!myStartingLetters.containsKey(firstChar)) {
			myStartingLetters.put(firstChar, new TrieNode());
		}
		TrieNode currChar = myStartingLetters.get(firstChar);
		for (int i = 1; i < word.length(); i++) {
			char current = word.charAt(i);
			if (!currChar.myNextLetters.containsKey(current)) {
				currChar.myNextLetters.put(current, new TrieNode());
			}
			currChar = currChar.myNextLetters.get(current);
		}
		currChar.myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode currLetter = myStartingLetters.get(word.charAt(0));
		if (currLetter == null) {
			return null;
		}
		for (int i = 1; i < word.length(); i++) {
			currLetter = currLetter.myNextLetters.get(word.charAt(i));
			if (currLetter == null) {
				return null;
			}
		}
		return currLetter.myDefinition;
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