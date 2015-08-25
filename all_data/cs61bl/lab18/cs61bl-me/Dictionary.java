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
		TrieNode currNode = new TrieNode();
		char currChar = word.charAt(0);
		if (myStartingLetters.get(currChar) == null) {
			myStartingLetters.put(currChar, currNode);
		} else {
			currNode = myStartingLetters.get(currChar);
		}
		for (char c: word.substring(1).toCharArray()) {
			if (!currNode.myNextLetters.containsKey(c)) {
				currNode.myNextLetters.put(c, new TrieNode());
				
			}
			currNode = currNode.myNextLetters.get(c);
		}
		currNode.myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode currNode = myStartingLetters.get(word.charAt(0));
		for (char c: word.substring(1).toCharArray()) {
			if (currNode == null) {
				return null;
			}
			currNode = currNode.myNextLetters.get(c);
		}
		return currNode.myDefinition;
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