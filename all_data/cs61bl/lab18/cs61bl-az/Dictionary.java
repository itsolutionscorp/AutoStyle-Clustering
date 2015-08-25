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
		HashMap<Character, TrieNode> tempLetters = myStartingLetters;
		for (int k = 0; k < word.length(); k++) {
			if (word.length() - 1 == k) {
				if (tempLetters.containsKey(word.charAt(k))) {
					tempLetters.get(word.charAt(k)).myDefinition = definition;
				} else {
					tempLetters.put(word.charAt(k), new TrieNode());
					tempLetters.get(word.charAt(k)).myDefinition = definition;
				}
			} else if (tempLetters.containsKey(word.charAt(k))) {
				tempLetters = tempLetters.get(word.charAt(k)).myNextLetters;
			} else {
				tempLetters.put(word.charAt(k), new TrieNode());
				tempLetters = tempLetters.get(word.charAt(k)).myNextLetters;
			}
		}		
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		HashMap<Character, TrieNode> tempLetters = myStartingLetters;
		for (int k = 0; k < word.length(); k++) {
			if (k == word.length() - 1) {
				if (tempLetters.containsKey(word.charAt(k))) {
					return tempLetters.get(word.charAt(k)).myDefinition;
				} else {
					return null;
				}
			}  else if (tempLetters.containsKey(word.charAt(k))) {
				tempLetters = tempLetters.get(word.charAt(k)).myNextLetters;
			} else {
				return null;
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
	
	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.addDefinition("hello", "a greeting");
		dict.addDefinition("bears", "go bears");
		System.out.println(dict.lookupDefinition("hello"));
	}
}