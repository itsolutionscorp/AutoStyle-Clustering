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
		char first = word.charAt(0);
		TrieNode rest = new TrieNode();
		rest.add(word.substring(1), definition);
		myStartingLetters.put(first, rest);
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		TrieNode rest = myStartingLetters.get(word.charAt(0));
		return rest.lookup(word.substring(1));
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition = null;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private void add(String word, String definition) {
			if (word.length() == 1) {
				myDefinition = definition;
			} else {
				char first = word.charAt(0);
				TrieNode rest = new TrieNode();
				rest.add(word.substring(1), definition);
				myNextLetters.put(first, rest);
			}
		}
		
		private String lookup(String word) {
			if (word.length() == 1) {
				return myDefinition;
			}
			char first = word.charAt(0);
			if (!myNextLetters.containsKey(first)) {
				return null;
			} 
			TrieNode rest = myNextLetters.get(first);
			return rest.lookup(word.substring(1));
		}
		
	}
	
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.addDefinition("word", "definition");
		String def = d.lookupDefinition("word");
		System.out.println(def);
	}
}