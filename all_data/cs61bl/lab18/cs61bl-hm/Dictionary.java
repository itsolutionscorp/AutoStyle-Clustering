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
		if (word == null)
			return;
		TrieNode pointer = null;
		HashMap<Character, TrieNode> hashPointer = myStartingLetters;
		for (char c : word.toCharArray()){
			pointer = hashPointer.get(c);
			if (pointer == null){
				hashPointer.put(c, new TrieNode());
				pointer = hashPointer.get(c);
			}
			hashPointer = pointer.myNextLetters;
		}
		pointer.myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (word == null)
			return null;
		TrieNode pointer = myStartingLetters.get('a');
		HashMap<Character, TrieNode> hashPointer = myStartingLetters;
		for (char c : word.toCharArray()){
			pointer = hashPointer.get(c);
			if (pointer == null)
				return null;
			hashPointer = pointer.myNextLetters;
		}
		return pointer.myDefinition;
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