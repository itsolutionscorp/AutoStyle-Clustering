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
	 * NEED TO CHECK IF THE LETTERS ARE ALREADY THERE!
	 */
	public void addDefinition(String word, String definition) {
		if (word.length() > 0) {
			char s = word.charAt(0);
			TrieNode myWord;
			if (myStartingLetters.containsKey(s)) {
				myWord = myStartingLetters.get(s);
			} else {
				myWord = new TrieNode();
			}
			TrieNode currentLetter = myWord;
			for (int charLocation = 1; charLocation < word.length(); charLocation++) {
				char c = word.charAt(charLocation);
				TrieNode nextLetter = new TrieNode();
				if (charLocation == word.length() - 1) {
					if (currentLetter.myNextLetters.containsKey(c)) {
						currentLetter.myNextLetters.get(c).myDefinition = definition;
					} else {
						nextLetter.myDefinition = definition;
						currentLetter.myNextLetters.put(c, nextLetter);
					}
				} else {
					if (currentLetter.myNextLetters.containsKey(c)) {
						currentLetter = currentLetter.myNextLetters.get(c);
					} else {
						currentLetter.myNextLetters.put(c, nextLetter);
						currentLetter = currentLetter.myNextLetters.get(c);
					}
				}
			}
			if (word.length() == 1) {
				myWord.myDefinition = definition;
			}
			myStartingLetters.put(word.charAt(0), myWord);
		} else {
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (word.length() > 0) {
			int wordCount = 1;
			if (!myStartingLetters.containsKey(word.charAt(0))) {
				return null;
			}
			TrieNode searchNode = myStartingLetters.get(word.charAt(0));
			while (wordCount < word.length()) {
				if (searchNode.myNextLetters != null) {
					if (searchNode.myNextLetters.containsKey(word.charAt(wordCount))) {
					searchNode = searchNode.myNextLetters.get(word
							.charAt(wordCount));
					wordCount++;
					} else {
						return null;
					}
				} else {
					return null;
				}

			}
			return searchNode.myDefinition;
		} else {
			System.err.println("Please enter a valid word!");
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