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
		HashMap a = myStartingLetters;
		if (!a.containsKey(word.charAt(0))) {
			TrieNode s = new TrieNode();
			TrieNode d;
			a.put(word.charAt(0), s);
			word = word.substring(1);
			while (word.length() > 1) {
				d = new TrieNode();
				s.myNextLetters.put(word.charAt(0), d);
				word = word.substring(1);
				s = d;
			}
			d = new TrieNode();
			s.myNextLetters.put(word.charAt(0), d);
			word = word.substring(1);
			d.myDefinition = definition;
		} else {
			TrieNode b;
			b = (TrieNode) a.get(word.charAt(0));
			HashMap<Character, TrieNode> c;
			c = b.myNextLetters;
			word = word.substring(1);
			while (c.containsKey(word.charAt(0))) {
				b = c.get(word.charAt(0));
				c = b.myNextLetters;
				if (word.length() == 1) {
					b.myDefinition = definition;
					return;
				}
				word = word.substring(1);
			}
			
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode a;
		HashMap<Character,TrieNode> b;
		b = this.myStartingLetters;
		while (b.containsKey(word.charAt(0))) {
			a = b.get(word.charAt(0));
			b = a.myNextLetters;
			if (word.length() == 1) {
				return a.myDefinition;
			}
			word = word.substring(1);
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