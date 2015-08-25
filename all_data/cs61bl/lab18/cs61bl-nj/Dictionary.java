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
		HashMap letters = myStartingLetters;
		for (int i = 0;i<word.length()-1;i++) {
			if (letters.containsKey(word.charAt(i))) {
				letters = ((TrieNode)letters.get(word.charAt(i))).myNextLetters;
			} else {
				TrieNode newNode = new TrieNode();
				letters.put(word.charAt(i), newNode);
				letters = ((TrieNode)letters.get(word.charAt(i))).myNextLetters;
			}
		}
		TrieNode newNode = new TrieNode();
		newNode.myDefinition = definition;
		if (letters.containsKey(word.charAt(word.length()-1))) {
			((TrieNode)letters.get(word.charAt(word.length()-1))).myDefinition = definition;
			return;
		}
		letters.put(word.charAt(word.length()-1), newNode);
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		HashMap letters = myStartingLetters;
		for (int i=0;i<word.length()-1;i++){
			if (letters.containsKey(word.charAt(i))) {
				letters = ((TrieNode)letters.get(word.charAt(i))).myNextLetters;
			} else {
				return null;
			}
		}
		if (letters.containsKey(word.charAt(word.length()-1))) {
			String result = ((TrieNode)letters.get(word.charAt(word.length()-1))).myDefinition;
			return result;
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