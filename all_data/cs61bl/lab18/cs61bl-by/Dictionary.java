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
//		if (myStartingLetters.containsKey(word.charAt(0))){
//			myStartingLetters.get(word.charAt(0)).addDefinition(word.substring(1), definition);
//		} else {
//			
//		}
		if (! myStartingLetters.containsKey(word.charAt(0))){
			myStartingLetters.put(word.charAt(0), new TrieNode());
		}
		TrieNode trie = myStartingLetters.get(word.charAt(0)); 
		for (int i = 1; i < word.length(); i++){
			if (! trie.myNextLetters.containsKey(word.charAt(i))){
				trie.myNextLetters.put(word.charAt(i), new TrieNode());
			}
			trie = trie.myNextLetters.get(word.charAt(i));
		}
		trie.myDefinition = definition; 
		//recursively create or go to trie node
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode trie = myStartingLetters.get(word.charAt(0));
		if (trie == null){
			return null;
		}
		for (int i = 1; i< word.length(); i++){
			trie = trie.myNextLetters.get(word.charAt(i));
			if (trie == null){
				return null;
			}
		}
		return trie.myDefinition;
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