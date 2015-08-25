import java.util.HashMap;

import javax.swing.tree.TreeNode;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

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
		TrieNode t = new TrieNode();
		if (myStartingLetters.containsKey(word.charAt(0))) {
			t.addHelper(word, definition, 1);
		} else {
			myStartingLetters.put(word.charAt(0), t);
			t.addHelper(word, definition, 1);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		TrieNode t = myStartingLetters.get(word.charAt(0));
		return t.lookHelper(word, 1);
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition = null;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}

		public void addHelper(String word, String definition, int index) {
			if (index < word.length()) {
				TrieNode next = myNextLetters.get(word.charAt(index));
				if (next != null) {
					next.addHelper(word, definition, index + 1);
				} else {
					next = new TrieNode();
					myNextLetters.put(word.charAt(index), next);
				}
			} else {
				myDefinition = definition;
			}
		}

		private String getDefi() {
			return myDefinition;
		}

		private String lookHelper(String word, int index) {
			if (index == word.length()) {
				return getDefi();
			} else {
				TrieNode next = myNextLetters.get(word.charAt(index));
				if (next != null) {
					return lookHelper(word, index + 1);
				} else {
					return null;
				}
			}
		}
	}
}