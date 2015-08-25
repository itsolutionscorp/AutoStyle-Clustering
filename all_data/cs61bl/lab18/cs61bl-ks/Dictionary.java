import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
		// System.out.println(definition);
		// TODO your code here
		if (word == null) {
			return;
		}
		char[] w = word.toCharArray();
		// System.out.println(w);
		if (w.length == 0) {
			return;
		}
		TrieNode node = myStartingLetters.get(w[0]);
		if (node == null) {
			node = new TrieNode();
		}
		if (w.length == 1) {
			// System.out.println(definition);

			node.addDef(definition);

			// System.out.println(node.def());
		} else {
			char[] nextLetters = Arrays.copyOfRange(w, 1, w.length);
			node.addDef(nextLetters, definition);
		}

		myStartingLetters.put(w[0], node);

	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		char[] w = word.toCharArray();
		TrieNode lookup = myStartingLetters.get(w[0]);
		if (lookup != null && w.length > 1) {
			
			return lookup.lookupDef(Arrays.copyOfRange(w, 1, w.length));
		} else if (lookup != null) {
			return lookup.def();
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

		public String def() {
			//System.out.println(myDefinition);
			// TODO Auto-generated method stub
			// System.out.println(myDefinition);
			return myDefinition;
		}

		public String lookupDef(char[] w) {
			//System.out.println(w);
			// TODO Auto-generated method stub
			TrieNode lookup = myNextLetters.get(w[0]);
			if (lookup != null && w.length > 1) {
				return lookup.lookupDef(Arrays.copyOfRange(w, 1, w.length));
			} else if (lookup != null) {
				// System.out.println(def());
				// System.out.println(lookup.def());
				return lookup.def();
			}
			return null;
		}

		private void addDef(char[] w, String def) {
			TrieNode node = myNextLetters.get(w[0]);
			if (node == null) {
				node = new TrieNode();
			}
			if (w.length == 1) {
				node.addDef(def);
			} else {

				char[] nextLetters = Arrays.copyOfRange(w, 1, w.length);

				node.addDef(nextLetters, def);

			}
			myNextLetters.put(w[0], node);

		}

		public void addDef(String definition) {
			// TODO Auto-generated method stub

			myDefinition = definition;
			// System.out.println(myDefinition);

		}

	}
}