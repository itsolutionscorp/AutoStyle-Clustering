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
		TrieNode t;
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			myStartingLetters.put(word.charAt(0), new TrieNode());
		}
		t = myStartingLetters.get(word.charAt(0));

		int len = word.length();
		for (int i = 1; i < len - 1; i++) {
			Character c = word.charAt(i);
			if (!t.myNextLetters.containsKey(c)) {
				t.myNextLetters.put(c, new TrieNode());
			}
			t = t.myNextLetters.get(c);
		}

		if (!t.myNextLetters.containsKey(word.charAt(len - 1))) {
			t.myNextLetters.put(word.charAt(len - 1), new TrieNode());
		}
		TrieNode last = t.myNextLetters.get(word.charAt(len - 1));
		last.myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		int len = word.length();
		TrieNode t;

		if (!myStartingLetters.containsKey(word.charAt(0))) {
			return null;
		}
		t = myStartingLetters.get(word.charAt(0));

		for (int i = 1; i < len - 1; i++) {
			if (t.myNextLetters.containsKey(word.charAt(i))) {
				t = t.myNextLetters.get(word.charAt(i));
			} else {
				return null;
			}
		}

		if (t.myNextLetters.containsKey(word.charAt(len - 1))) {
			t = t.myNextLetters.get(word.charAt(len - 1));
			return t.myDefinition;
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