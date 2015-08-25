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
		TrieNode head;
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			head = new TrieNode();
			myStartingLetters.put(word.charAt(0), head);
		} else {
			head = myStartingLetters.get(word.charAt(0));
		}
		for (int k = 1; k < word.length(); k++) {

			TrieNode placeHolder = new TrieNode();
			head.myNextLetters.put(word.charAt(k), placeHolder);
			head = placeHolder;
		}
		head.myDefinition = definition;

	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			return null;
		}
		TrieNode myNext = myStartingLetters.get(word.charAt(0));
		for (int k = 1; k < word.length(); k++) {
			if (myNext == null) {
				return null;
			}
			myNext = myNext.myNextLetters.get(word.charAt(k));

		}

		return myNext.myDefinition;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}

	public static void main(String[] args) {
		Dictionary d = new Dictionary();

		d.addDefinition("b", "dont worry, we are fine!:)");
		d.addDefinition("briaaa", "dont worry, we are fine!:)");
		System.out.println(d.lookupDefinition("b"));

	}
}