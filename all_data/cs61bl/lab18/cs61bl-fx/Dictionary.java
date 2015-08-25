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
		char start = word.charAt(0);
		if (!myStartingLetters.containsKey(start)) {
			myStartingLetters.put(start, new TrieNode());
		}
		addHelper(word.substring(1), definition, myStartingLetters.get(start));
	}

	private void addHelper(String word, String definition, TrieNode node) {
		if (word.length() == 0) {
			node.myDefinition = definition;
			return;
		}
		char start = word.charAt(0);
		if (!node.myNextLetters.containsKey(start)) {
			node.myNextLetters.put(start, new TrieNode());
		}
		addHelper(word.substring(1), definition, node.myNextLetters.get(start));
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// char start = word.charAt(0);
		// word = word.substring(1);
		// if (!myStartingLetters.containsKey(start)) {
		// return null;
		// } else {
		// TrieNode temp = myStartingLetters.get(start);
		// while (temp.myNextLetters.containsKey(start)) {
		// if (word.length() == 0) {
		// return temp.myDefinition;
		// }
		// start = word.charAt(0);
		// word = word.substring(1);
		// temp = temp.myNextLetters.get(start);
		//
		// } return temp.myDefinition;
		// }
		char start = word.charAt(0);
		if (!myStartingLetters.containsKey(start)) {
			return null;
		}
		return lookupHelper(word.substring(1), myStartingLetters.get(start));
	}

	public String lookupHelper(String word, TrieNode temp) {
		if (word.length() == 0) {
			return temp.myDefinition;
		}
		else if (!temp.myNextLetters.containsKey(word.charAt(0))) {
			return null;
		}
		char start = word.charAt(0);
		return lookupHelper(word.substring(1), temp.myNextLetters.get(start));
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
		d.addDefinition("cattle", "an animal");
		d.addDefinition("cattles", "two cattles");
		d.addDefinition("cap", "sth u put on head");
		d.addDefinition("Cape", "name");
		d.addDefinition("cape", "clothing");
		d.addDefinition("&apybara", "weirdd");
		d.addDefinition("to", "connecting word");
		d.addDefinition("too", "much");

		System.out.println(d.lookupDefinition("cattle"));
		System.out.println(d.lookupDefinition("cattles"));
		System.out.println(d.lookupDefinition("cap"));
		System.out.println(d.lookupDefinition("can"));
		System.out.println(d.lookupDefinition("Cape"));
		System.out.println(d.lookupDefinition("cape"));
		System.out.println(d.lookupDefinition("&apybara"));
		System.out.println(d.lookupDefinition("to"));
		System.out.println(d.lookupDefinition("too"));
		System.out.println(d.lookupDefinition("tooo"));
	}

	private Object myStartingLetters(char c) {
		// TODO Auto-generated method stub
		return null;
	}
}