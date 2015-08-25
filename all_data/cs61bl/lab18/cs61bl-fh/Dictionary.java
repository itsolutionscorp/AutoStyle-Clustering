import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	public HashMap<Character, TrieNode> myStartingLetters; // made this public
															// for testing
															// purposes

	public Dictionary() {
		myStartingLetters = new HashMap<>();
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		if (word.equals("")) { // prevents it from crashing if we give it a null
								// input string. Won't add anything if we do
								// this.
			return;
		}
		if (myStartingLetters.containsKey(word.charAt(0))) {
			TrieNode currentNode = myStartingLetters.get(word.charAt(0));
			String currentWord = word.substring(1);
			while (currentWord.length() > 0
					&& currentNode.myNextLetters.containsKey(currentWord
							.charAt(0))) {
				currentNode = currentNode.myNextLetters.get(currentWord
						.charAt(0));
				currentWord = currentWord.substring(1);
			}
			if (currentWord.length() == 0) {
				currentNode.myDefinition = definition;
				return;
			} else {
				TrieNode temp = makeBranch(currentWord.substring(1),
						definition, new TrieNode());
				currentNode.myNextLetters.put(currentWord.charAt(0), temp);
			}
		} else {
			TrieNode temp = makeBranch(word.substring(1), definition,
					new TrieNode());
			myStartingLetters.put(word.charAt(0), temp);

			// for (int index = 0; index < word.length() - 1; index++) {
			// myStartingLetters.get(word.charAt(index)).myNextLetters.put(
			// word.charAt(index + 1), new TrieNode());
			// }
			// myStartingLetters.get(word.charAt(word.length() -
			// 1)).myDefinition = definition;
		}

	}

	public TrieNode makeBranch(String word, String definition, TrieNode t) {
		if (word.length() == 0) {
			t.myDefinition = definition;
			return t;
		} else {
			TrieNode temp = makeBranch(word.substring(1), definition,
					new TrieNode());
			t.myNextLetters.put(word.charAt(0), temp);
			return t;
		}
	}

	// public void addRemainingLetters(String word, String definition) {
	//
	// }

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			return null;
		} else {
			TrieNode currentNode = myStartingLetters.get(word.charAt(0));
			String currentWord = word.substring(1);
			while (currentWord.length() > 0) {
				if (!currentNode.myNextLetters.containsKey(currentWord
						.charAt(0))) {
					return null;
				} else {
					currentNode = currentNode.myNextLetters.get(currentWord
							.charAt(0));
					currentWord = currentWord.substring(1);
				}
			}
			return currentNode.myDefinition;
		}

	}

	public class TrieNode { // made public for testing
		public HashMap<Character, TrieNode> myNextLetters; // made public for
															// testing

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}
}