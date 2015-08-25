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
		char[] wordToChars = word.toCharArray();
		HashMap<Character, TrieNode> lettersSoFar = myStartingLetters;
		for (int i = 0; i < wordToChars.length; i++) {
			char toCheck = wordToChars[i];
			if (!lettersSoFar.containsKey(toCheck)) {
				lettersSoFar.put(toCheck, new TrieNode());
			} if (i < wordToChars.length - 1) {
				lettersSoFar = lettersSoFar.get(toCheck).myNextLetters;
			} else {
				lettersSoFar.get(toCheck).myDefinition = definition;
			}
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		char[] wordToChars = word.toCharArray();
		HashMap<Character, TrieNode> lettersSoFar = myStartingLetters;
		for (int i = 0; i < wordToChars.length; i++) {
			char toCheck = wordToChars[i];
			if (!lettersSoFar.containsKey(toCheck)) {
				return null;
			} if (i < wordToChars.length - 1) {
				lettersSoFar = lettersSoFar.get(toCheck).myNextLetters;
			} else {
				return lettersSoFar.get(toCheck).myDefinition;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.addDefinition("word", "thing");
		d.addDefinition("wool", "soft");
		d.addDefinition("w", "letter");
		d.addDefinition("art", "exquisite");
		d.addDefinition("10", "number");
		System.out.println(d.lookupDefinition("word"));
		System.out.println(d.lookupDefinition("wool"));
		System.out.println(d.lookupDefinition("w"));
		System.out.println(d.lookupDefinition("art"));
		System.out.println(d.lookupDefinition("10"));
		System.out.println(d.lookupDefinition("wor"));
		System.out.println(d.lookupDefinition("woo"));
		System.out.println(d.lookupDefinition("ar"));
		System.out.println(d.lookupDefinition("1"));
		System.out.println(d.lookupDefinition(""));
		
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