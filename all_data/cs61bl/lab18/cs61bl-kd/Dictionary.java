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
		addHelp(myStartingLetters.get(word.charAt(0)), word.substring(1, word.length()), definition);
	}
	
	public void addHelp(TrieNode n, String word, String definition) {
		TrieNode myNode = new TrieNode();
		if (n.myNextLetters.containsKey(word.charAt(0))) {
			myNode = n.myNextLetters.get(word.charAt(0));
		}
		else {
			n.myNextLetters.put(word.charAt(0), myNode);
		}
		
		if (word.length() == 1) {
			myNode.myDefinition = definition;
			return;
		}
		else {
			addHelp(myNode, word.substring(0, word.length()), definition);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
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