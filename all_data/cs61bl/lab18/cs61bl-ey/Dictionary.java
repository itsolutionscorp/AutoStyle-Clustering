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
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (word.length() == 1) {
			return myStartingLetters.get(word.charAt(0)).myDefinition;
		}

		else if (myStartingLetters.containsKey(word.charAt(0))) {

			return myStartingLetters.get(word.charAt(0)).lookupHelper(word.substring(1));
		}
		return null;
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		myStartingLetters.put(word.charAt(0), new TrieNode());
		myStartingLetters.put(word.charAt(0),
				myStartingLetters.get(word.charAt(0)).AddHelper(word.substring(1), definition));
		// System.out.println(myStartingLetters.get(word.charAt(0)));
		// System.out.println(myStartingLetters.get(word.charAt(0)).myNextLetters.get(word.charAt(1)));
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}

		public TrieNode AddHelper(String word, String definition) {
			if (word.length() == 1) {
				myNextLetters.put(word.charAt(0), null);
				myDefinition = definition;
				return this;
			}
			TrieNode t;
			if (!myNextLetters.containsKey(word.charAt(0))) {
				t = new TrieNode();
				myNextLetters.put(word.charAt(0), t);
			} else {
				t = myNextLetters.get(word.charAt(0));
			}
			myNextLetters.put(word.charAt(0), t.AddHelper(word.substring(1), definition));
			return this;

		}

		public String lookupHelper(String word) {
			if (word.length() == 1) {
				return myDefinition;
			}
			return myNextLetters.get(word.charAt(0)).lookupHelper(word.substring(1));
		}

	}
}