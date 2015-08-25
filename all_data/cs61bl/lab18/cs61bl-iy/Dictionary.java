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
		TrieNode cur = new TrieNode();
		TrieNode next = new TrieNode();
		myStartingLetters.put(word.charAt(0), cur);
		word = word.substring(1);
		while (word.length() > 0) {
			cur.myNextLetters.put(word.charAt(0), next );
			cur = next;
			word = word.substring(1);
		}
		cur.myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode rst = myStartingLetters.get(word.charAt(0));
		word = word.substring(1);
		while (rst != null) {
			rst = rst.myNextLetters.get(word.charAt(0));
			word = word.substring(1);
			if (word.length() == 0) {
				return rst.myDefinition;
			}
		}		
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