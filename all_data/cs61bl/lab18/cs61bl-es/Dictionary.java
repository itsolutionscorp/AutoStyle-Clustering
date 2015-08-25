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
		if(!myStartingLetters.containsKey(word.charAt(0))) {
			TrieNode newNode = new TrieNode();
			myStartingLetters.put(word.charAt(0), newNode);
		}
		TrieNode t = myStartingLetters.get(word.charAt(0));
		if(word.length() == 1) {
			t.myDefinition=definition;
			return;
		}
		t.addDefinitionHelper(word.substring(1, word.length()), definition);	
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		TrieNode t = myStartingLetters.get(word.charAt(0));
		if (t == null) {
			return null;
		}
		else if (word.length() == 1) {
			return t.myDefinition;
		}
		return t.lookupDefinitionHelper(word.substring(1, word.length()));
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private void addDefinitionHelper(String word, String definition) {
			
			if(!myNextLetters.containsKey(word.charAt(0))) {
				TrieNode newNode = new TrieNode();
				myNextLetters.put(word.charAt(0), newNode);
			}
			TrieNode t = myNextLetters.get(word.charAt(0));
			if(word.length() == 1) {
				t.myDefinition=definition;
				return;
			}
			t.addDefinitionHelper(word.substring(1, word.length()), definition);	
		}
		
		private String lookupDefinitionHelper(String word) {
			TrieNode t = myNextLetters.get(word.charAt(0));
			if (t == null) {
				return null;
			}
			else if (word.length() == 1) {
				return t.myDefinition;
			}
			return t.lookupDefinitionHelper(word.substring(1, word.length()));
		}
		
	}
}