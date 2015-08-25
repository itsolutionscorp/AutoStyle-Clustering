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
		if (word.length() == 0) {
			return;
		}
		
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			TrieNode firstLetter = new TrieNode();
			myStartingLetters.put(word.charAt(0), firstLetter);
		}
		TrieNode nextLetters = myStartingLetters.get(word.charAt(0));
		int pos = 1;
		TrieNode inside;
		while (pos < word.length() - 1) {
			if ((nextLetters.myNextLetters.get(word.charAt(pos))) != null) {
				inside = nextLetters.myNextLetters.get(word.charAt(pos));
			}
			else {
				inside = new TrieNode();
			}
			
			nextLetters.myNextLetters.put(word.charAt(pos), inside);
			nextLetters = inside;                                                                                           
			pos++;
		}
		nextLetters.myNextLetters.put(word.charAt(word.length() - 1), null);
		nextLetters.myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (word.length() == 0) {
			return null;
		}
		TrieNode find = myStartingLetters.get(word.charAt(0));
		if (find == null) {
			return null;
		}
		for (int l = 1; l < word.length() - 1; l++) {
			find = find.myNextLetters.get(word.charAt(l));
			if (find == null) {
				System.out.println("what");
				return null;
			}
		}
		return find.myDefinition;
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