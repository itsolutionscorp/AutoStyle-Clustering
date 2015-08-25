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
		
		TrieNode node = new TrieNode ();
		node = myStartingLetters.get(word.charAt(0));
		
		myStartingLetters.put(word.charAt(0), node);
		int i = 0; 
		while(i < word.length()){
			if (node.myDefinition != null){
				node.myDefinition = definition;
				node.myNextLetters.put(word.charAt(i), node);
			}
			i ++ ; 
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode node = myStartingLetters.get(word.charAt(0));
		if (word.length() == 1 && word != null){
			return node.myDefinition;
		}
		return lookupDefinition(word.substring(1));
		
		
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