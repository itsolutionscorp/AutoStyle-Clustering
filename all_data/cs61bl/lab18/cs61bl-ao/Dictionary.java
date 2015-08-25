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
		myStartingLetters.put(word.charAt(0), TNCreator(word.substring(1), definition));
	}
	
	private TrieNode TNCreator (String word, String definition) {
		if (word.equals("")) return new TrieNode();
		else {
			TrieNode x = new TrieNode();
			if (word.length() == 1) {
				x.myDefinition = definition;
				x.myNextLetters.put(word.charAt(0), new TrieNode());
			}
			else {
				x.myNextLetters.put(word.charAt(0), TNCreator(word.substring(1), definition));
			}
			return x;
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (myStartingLetters.containsKey((Character)word.charAt(0))) {
			return searchForString(word.substring(1), myStartingLetters.get(word.charAt(0)), word.length());
		} else return null;
	}
	
	private String searchForString(String word, TrieNode n, int count) {
		if (count == 2) return n.myDefinition;
		if(n.myNextLetters.containsKey((Character)word.charAt(0))) {
			return searchForString(word.substring(1), n.myNextLetters.get(word.charAt(0)), count-1);
		} else return null;
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
