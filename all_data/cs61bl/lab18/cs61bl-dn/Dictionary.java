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
		char[] chars = word.toCharArray();
		TrieNode dummy = new TrieNode();
		dummy.myNextLetters = myStartingLetters;
		for (int i = 0; i < chars.length; i++) {
			if (i == chars.length - 1) {
				if (dummy.myNextLetters.containsKey(chars[i])) {
					dummy.myNextLetters.get(chars[i]).myDefinition = definition;
					break;
				} else {
					dummy.myNextLetters.put(chars[i], new TrieNode());
					dummy.myNextLetters.get(chars[i]).myDefinition = definition;
					break;
				}
			}
			if (dummy.myNextLetters.containsKey(chars[i])) {
				dummy = dummy.myNextLetters.get(chars[i]);
			} else {
				dummy.myNextLetters.put(chars[i], new TrieNode());
				dummy = dummy.myNextLetters.get(chars[i]);
			}
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		char[] chars = word.toCharArray();
		TrieNode t = null;
		String s = null;
		if (myStartingLetters.containsKey(chars[0])) {
			if (word.length() == 1) {
				return myStartingLetters.get(chars[0]).myDefinition;
			}
			t = myStartingLetters.get(chars[0]); 
		} 
		for (int i = 1; i < chars.length; i++) {
			if (t.myNextLetters.containsKey(chars[i])) {
				s = t.myDefinition;
				t = t.myNextLetters.get(chars[i]);
			} else {
				return null;
			}
		}
		return t.myDefinition;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}
	
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.addDefinition("yi", "short name");
		d.addDefinition("yiding", "fullname");
		d.addDefinition("yizing", "typo");
		d.addDefinition("Iy", "game ID");
		System.out.println(d.lookupDefinition("yiding"));
		System.out.println(d.lookupDefinition("yi"));
		System.out.println(d.lookupDefinition("yizing"));
		System.out.println(d.lookupDefinition("Iy"));
	}
}
