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
		// TODO your code here!
		// Last character in word should be associated with definition.
		// Otherwise, each character has a null definition.
		myStartingLetters.put(word.charAt(0), new TrieNode(word.charAt(0), word.substring(1), definition));
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!

		// Go through myStartingLetters, look up each character,
		// at the last character of word, return myDefinition.
		if (myStartingLetters.containsKey(word.charAt(0))) {
			return myStartingLetters.get(word.charAt(0)).lookup(word);
		}
		return null;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode(Character c, String rest, String def) {
			myNextLetters = new HashMap<Character, TrieNode>();

			if (rest.length() > 0) {
				myNextLetters.put(c, new TrieNode(rest.charAt(0), rest.substring(1), def));
			} else {
				myNextLetters.put(c, null);
				myDefinition = def;
			}
		}

		private String lookup(String rest) {
			if (myNextLetters.containsKey(rest.charAt(0))) {
				if (rest.length() > 1) {
					return myNextLetters.get(rest.charAt(0)).lookup(rest.substring(1));
				}
			}
			return myDefinition;
		}
	}

	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.addDefinition("cat", "cute furry");
		d.addDefinition("dog", "also cute furry");
		d.addDefinition("can", "thing");

		System.out.println(d.lookupDefinition("cat"));
		System.out.println(d.lookupDefinition("ca"));
		System.out.println(d.lookupDefinition("dog"));
		System.out.println(d.lookupDefinition("can"));
	}
}