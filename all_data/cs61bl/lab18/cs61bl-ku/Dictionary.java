import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	public HashMap<Character, TrieNode> myStartingLetters;

	public Dictionary() {
		myStartingLetters = new HashMap<>();
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		HashMap<Character, TrieNode> letterChain = myStartingLetters;
		char[] characters = word.toCharArray();
		for (int i = 0; i < characters.length; i++) {
			char c = characters[i];
			if (letterChain.containsKey(c)) {
				TrieNode n = letterChain.get(c);
				if (i + 1 == characters.length) {
					n.myDefinition = definition;
				}
				letterChain = letterChain.get(c).myNextLetters;
			}
		}
	}
	
		

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		HashMap<Character, TrieNode> letterChain = myStartingLetters;
		char[] characters = word.toCharArray();
		for (int i = 0; i < characters.length; i++) {
			char c = characters[i];
			if (letterChain.containsKey(c)) {
				TrieNode n = letterChain.get(c);
				if (i + 1 == characters.length) {
					return n.myDefinition;
				}
				letterChain = letterChain.get(c).myNextLetters;
			}
		}
		return null;
	}

	public class TrieNode {
		public HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		public String myDefinition;

		public TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}
}