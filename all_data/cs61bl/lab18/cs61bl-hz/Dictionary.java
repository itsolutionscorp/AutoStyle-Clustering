import java.util.ArrayList;
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
		char[] c = word.toCharArray();
		ArrayList<Character> a = new ArrayList<Character>();
		for (int i = 0; i < c.length ; i ++) {
			a.add(c[i]);
		}
		TrieNode t = null;
		if (!myStartingLetters.containsKey(a.get(0))) {
			t = new TrieNode();
			myStartingLetters.put(a.get(0), t);
		} else {
			t = myStartingLetters.get(a.get(0));
		}
		a.remove(0);
		t.add(a, definition);
	}
	
	

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		char[] c = word.toCharArray();
		ArrayList<Character> a = new ArrayList<Character>();
		for (int i = 0; i < c.length ; i ++) {
			a.add(c[i]);
		}
		HashMap<Character, TrieNode> t = myStartingLetters;
		TrieNode x = null;
		while (!a.isEmpty()) {
			if (!t.containsKey(a.get(0))) {
				return null;
			} else {
				x = t.get(a.get(0));
				t = x.myNextLetters;
				a.remove(0);
			}
		}
		return x.myDefinition;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		public void add(ArrayList<Character> a, String definition) {
			if (a.isEmpty()) {
				myDefinition = definition;
			} else {
				TrieNode t = null;
				if (!myNextLetters.containsKey(a.get(0))) {
					t = new TrieNode();
					myNextLetters.put(a.get(0), t);
				} else {
					t = myNextLetters.get(a.get(0));
				}
				a.remove(0);
				t.add(a, definition);
			}
		}
	}
}