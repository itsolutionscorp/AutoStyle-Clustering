import java.util.HashMap;
import java.io.*;

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
		char[] wordChars = word.toCharArray();
		HashMap<Character, TrieNode> lettersToCheck = myStartingLetters;
		for (int i = 0; i < wordChars.length; i++){
			char c = wordChars[i];
			if (!lettersToCheck.containsKey(c)) //hasmap does not contain c
				lettersToCheck.put(c, new TrieNode());
			if (i < wordChars.length - 1)	
				lettersToCheck = lettersToCheck.get(c).myNextLetters;
			else{
				lettersToCheck.get(c).myDefinition = definition;
			}
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		char[] wordChars = word.toCharArray();
		HashMap<Character, TrieNode> lettersToCheck = myStartingLetters;
		for (int i = 0; i < wordChars.length; i++){
			char c = wordChars[i];
			if (!lettersToCheck.containsKey(c))
				return null;
			if (i < wordChars.length - 1)
				lettersToCheck = lettersToCheck.get(c).myNextLetters;
			else{
				return lettersToCheck.get(c).myDefinition;
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