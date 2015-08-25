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
		assert(word.length() > 1);
		if (myStartingLetters.containsKey(word.charAt(0))) {
			myStartingLetters.get(word.charAt(0)).addDefinition(word.substring(1, word.length()), definition);
		} else {
			TrieNode t = new TrieNode();
			t.addDefinition(word.substring(1,  word.length()), definition);
			myStartingLetters.put(word.charAt(0), t);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		assert(word.length() > 1);
		if (myStartingLetters.containsKey(word.charAt(0))) {
			return myStartingLetters.get(word.charAt(0)).lookupDefinition(word.substring(1, word.length()));
		} 
		return null;
	}
	
	public static void main(String[] args) {
		
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private void addDefinition(String str, String definition) {
			if (str.length() == 1) {
				if (!myNextLetters.containsKey(str.charAt(0))) {
					TrieNode t = new TrieNode();
					t.myDefinition = definition;
					myNextLetters.put(str.charAt(0), t);
				}	
				return;
			}
			
			if (myNextLetters.containsKey(str.charAt(0))) {
				myNextLetters.get(str.charAt(0)).addDefinition(str.substring(1, str.length()), definition);
			} else {
				TrieNode t = new TrieNode();
				t.addDefinition(str.substring(1,  str.length()), definition);
				myNextLetters.put(str.charAt(0), t);
			}		
		}
		
		private String lookupDefinition(String str) {
			if (str.length() == 1) {
				if (myNextLetters.containsKey(str.charAt(0))) {					
					return myNextLetters.get(str.charAt(0)).myDefinition;
				}
			} else {
				if (myNextLetters.containsKey(str.charAt(0))) {					
					return myNextLetters.get(str.charAt(0)).lookupDefinition(str.substring(1,  str.length()));
				} 
			}
			return null;
		}
	}
}