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
		Character firstLetter = word.charAt(0);
		if (word.length() == 1) {
			TrieNode startLetter = new TrieNode();
			startLetter.myDefinition = definition;
			myStartingLetters.put(word.charAt(0), startLetter);
		} else if (myStartingLetters.containsKey(firstLetter)) {
			myStartingLetters.get(firstLetter).addDefinition(word.substring(1), definition);
		} else {
			TrieNode startLetter = new TrieNode();
			startLetter.addDefinition(word.substring(1), definition);
			myStartingLetters.put(firstLetter, startLetter);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		Character firstLetter = word.charAt(0);
		if (word.length() == 1) {
			if (myStartingLetters.containsKey(firstLetter)) {
				return myStartingLetters.get(firstLetter).myDefinition;
			}
			return null;
		} else if (myStartingLetters.containsKey(firstLetter)) {
			return myStartingLetters.get(firstLetter).lookupDefinition(word.substring(1));
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
		
		private void addDefinition(String word, String definition) {
			Character firstLetter = word.charAt(0);
			if (word.length() == 1) {
				TrieNode startLetter = new TrieNode();
				startLetter.myDefinition = definition;
				myNextLetters.put(word.charAt(0), startLetter);
			} else if (myNextLetters.containsKey(firstLetter)) {
				myNextLetters.get(firstLetter).addDefinition(word.substring(1), definition);
			} else {
				TrieNode startLetter = new TrieNode();
				startLetter.addDefinition(word.substring(1), definition);
				myNextLetters.put(firstLetter, startLetter);
			}
		}
		
		private String lookupDefinition(String word) {
			Character firstLetter = word.charAt(0);
			if (word.length() == 1) {
				if (myNextLetters.containsKey(firstLetter)) {
					return myNextLetters.get(firstLetter).myDefinition;
				}
				return null;
			} else if (myNextLetters.containsKey(firstLetter)) {
				return myNextLetters.get(firstLetter).lookupDefinition(word.substring(1));
			}
			return null;
		}
	}
}