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
		if (!myStartingLetters.containsKey(word.charAt(0)) && word.length() == 1) {
			TrieNode t = new TrieNode(); 
			t.myDefinition = definition;
			myStartingLetters.put(word.charAt(0), t);
		}
		else if(!myStartingLetters.containsKey(word.charAt(0)) && word.length() > 1) {
			TrieNode t = new TrieNode();
			myStartingLetters.put(word.charAt(0), t);
			t.addDefinitionHelper(word.substring(1, word.length()), definition);
		}
		else if (myStartingLetters.containsKey(word.charAt(0)) && word.length() == 1) {
			myStartingLetters.get(word.charAt(0)).myDefinition = definition;
		}
		else if (myStartingLetters.containsKey(word.charAt(0)) && word.length() > 1) {
			myStartingLetters.get(word.charAt(0)).addDefinitionHelper(word.substring(1, word.length()), definition);
		}
	}
	
	

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (myStartingLetters.containsKey(word.charAt(0)) && word.length() == 1){
			return myStartingLetters.get(word.charAt(0)).myDefinition;
		}
		else if (myStartingLetters.containsKey(word.charAt(0)) && word.length() > 1) {
			return myStartingLetters.get(word.charAt(0)).lookupHelper(word.substring(1, word.length()));
		}
		else {
			return "No definition for that word.";
		}
		
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private String lookupHelper(String word) {
			if (myNextLetters.containsKey(word.charAt(0)) && word.length() == 1) {
				return myNextLetters.get(word.charAt(0)).myDefinition;
			}
			if (myNextLetters.containsKey(word.charAt(0)) && word.length() > 1) {
				return myNextLetters.get(word.charAt(0)).lookupHelper(word.substring(1, word.length()));
			}
			else {
				return "No definition for that word.";
			}
		}
		
		private void addDefinitionHelper(String word, String def){
			if (!myNextLetters.containsKey(word.charAt(0)) && word.length() == 1) {				
				TrieNode t = new TrieNode(); 				
				myNextLetters.put(word.charAt(0), t);
				t.myDefinition = def;
			}
			else if(!myNextLetters.containsKey(word.charAt(0)) && word.length() > 1) {
				TrieNode t = new TrieNode();
				myNextLetters.put(word.charAt(0), t);
				t.addDefinitionHelper(word.substring(1, word.length()), def);
			}
			else if (myNextLetters.containsKey(word.charAt(0)) && word.length() == 1) {
				myNextLetters.get(word.charAt(0)).myDefinition = def;
			}
			else if (myNextLetters.containsKey(word.charAt(0)) && word.length() > 1) {
				myNextLetters.get(word.charAt(0)).addDefinitionHelper(word.substring(1, word.length()), def);
			}
		}
	}
}