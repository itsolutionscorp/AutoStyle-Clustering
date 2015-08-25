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
//		int num = word.length();
//		for(int i = num-1; i > 0 ; i--){
//			char temp = word.charAt(i);
//			if(i == num-1){
//				myStartingLetters.put(word.charAt(i), definition);
//			} else {
//				
//			}
//		}
		TrieNode def = new TrieNode();
		if(word.length() == 1){
			def.myDefinition = definition;
			def.myNextLetters = null;
			myStartingLetters.put(word.charAt(0), def);
		}
		def.myDefinition = null;
		def.myNextLetters = new TrieNode();
		
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