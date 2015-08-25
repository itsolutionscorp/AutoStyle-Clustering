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
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			myStartingLetters.put(word.charAt(0), new TrieNode());
		}
		addHelper(word.substring(1), myStartingLetters.get(word.charAt(0)), definition);
	}
	
	private void addHelper(String word, TrieNode node, String definition) {
		TrieNode nextNode;
		if (node.myNextLetters.containsKey(word.charAt(0))) {
			nextNode = node.myNextLetters.get(word.charAt(0));
		} else {
			nextNode = new TrieNode();
			node.myNextLetters.put(word.charAt(0), nextNode);
		}
		if (word.length() == 1) {
			nextNode.myDefinition = definition;
			return;
		} else {
			addHelper(word.substring(1), nextNode, definition);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (myStartingLetters.containsKey(word.charAt(0))) {
			return lookupHelper(word.substring(1), myStartingLetters.get(word.charAt(0)));
		}
		return null;	
	}
	private String lookupHelper(String word, TrieNode node) {
		if (word.length() == 0) {
			return node.myDefinition;
		}
		if (node.myNextLetters.containsKey(word.charAt(0))) {
			return lookupHelper(word.substring(1), node.myNextLetters.get(word.charAt(0)));
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