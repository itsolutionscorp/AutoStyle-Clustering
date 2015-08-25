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
		// TODO your code here!
		char a = word.charAt(0);
		if (! myStartingLetters.containsKey(a)){
			TrieNode t = new TrieNode();
			myStartingLetters.put(a, t);
		}
		TrieNode ref = myStartingLetters.get(a);
		String input = word.substring(1);
		while (! input.isEmpty()) {
			if (! ref.myNextLetters.containsKey(input.charAt(0))) {
				ref.myNextLetters.put(input.charAt(0), new TrieNode());
			}
			ref = ref.myNextLetters.get(input.charAt(0));
			if (input.length() == 1) {
				ref.myDefinition = definition;
			}
			input = input.substring(1);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (! myStartingLetters.containsKey(word.charAt(0)))
			return null;
		TrieNode t = myStartingLetters.get(word.charAt(0));
		String s = word.substring(1);
		while (t != null) {
			if (t.myNextLetters.containsKey(s.charAt((0)))) {
				System.out.println(s.charAt(0));
				char a = s.charAt(0);
				s = s.substring(1);
				t = t.myNextLetters.get(a);
				if (s.length() == 0) {
					return t.myDefinition;
				}
			} else {
				return null;
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