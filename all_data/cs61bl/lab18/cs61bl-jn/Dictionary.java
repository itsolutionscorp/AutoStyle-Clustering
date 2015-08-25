import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	private HashMap<Character, TrieNode> myStartingLetters;

	public Dictionary() {
		myStartingLetters = new HashMap<Character, TrieNode>();
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		TrieNode exists = myStartingLetters.get(word.charAt(0));
		if (exists == null) {
			int count = 0;
			char[] woo = word.toCharArray();
			for (char c: woo) {
				if (count == 0) {
					myStartingLetters.put(c, new TrieNode());
					exists = myStartingLetters.get(c);
				} else if (count == word.length() - 1) {
					exists.myNextLetters.put(c, new TrieNode());
					exists.myNextLetters.get(c).myDefinition = definition;
				} else {
					exists.myNextLetters.put(c, new TrieNode());
					exists = exists.myNextLetters.get(c);
				}
				count += 1;
			}
		} else {
			char[] woo = word.toCharArray();
			int count = 0;
			for (char c: woo) {
				if (count != 0) {
					if (!exists.myNextLetters.containsKey(c)) {
						exists.myNextLetters.put(c, new TrieNode());
						System.out.println(c);
					}
					exists = exists.myNextLetters.get(c);
				}
				count += 1;
			}
			exists.myDefinition = definition;
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		int count = 0;
		TrieNode wow = myStartingLetters.get(word.charAt(0));
		char[] swag = word.toCharArray();
		for (int i = 1; i < swag.length; i++) {
			if (wow == null) {
				return null;
			}
			wow = wow.myNextLetters.get(swag[i]);
		}
		return wow.myDefinition;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<Character, TrieNode>();
		}
	}

}