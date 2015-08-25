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
		
		if (!myStartingLetters.containsKey(word.charAt(0))) {              //beginning of word
			TrieNode trienode = new TrieNode();
			myStartingLetters.put(word.charAt(0), trienode);
		}
		TrieNode node = myStartingLetters.get(word.charAt(0));
		for (int i = 1; i < word.length() - 1; i++) {                     //iterate through all the nodes but the last
			if (!node.myNextLetters.containsKey(word.charAt(i))) {
				TrieNode temp = new TrieNode();
				node.myNextLetters.put(word.charAt(i), temp);
			} 
			node = node.myNextLetters.get(word.charAt(i));
		}
		if (!node.myNextLetters.containsKey(word.charAt(word.length() - 1))) {      //special case for the end
			TrieNode end = new TrieNode();
			end.myDefinition = definition;
			node.myNextLetters.put(word.charAt(word.length() - 1), end);
		} else {
			TrieNode temp = node.myNextLetters.get(word.charAt(word.length() - 1));
			temp.myDefinition = definition;                                   //overwrite the definition if that word already exists
			node.myNextLetters.put(word.charAt(word.length() - 1), temp);
		}
	}
		
		
	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (!myStartingLetters.containsKey(word.charAt(0))) {              
			return null;
		}
		TrieNode node = myStartingLetters.get(word.charAt(0));
		
		for (int i = 1; i < word.length(); i++) {                    
			if (!node.myNextLetters.containsKey(word.charAt(i))) {
				return null;
			} 
			node = node.myNextLetters.get(word.charAt(i));
		}
		return node.myDefinition;
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