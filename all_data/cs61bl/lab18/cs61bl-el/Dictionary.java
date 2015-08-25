import java.util.HashMap;

public class Dictionary {

	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.addDefinition("kelly", "yen");
		System.out.println(d.lookupDefinition("hello"));
		System.out.println(d.lookupDefinition("kelly"));

		d.addDefinition("kellypo", "hello");
		System.out.println(d.lookupDefinition("kellypo"));

		d.addDefinition("*", "star");
		d.addDefinition("", "super");
		System.out.println(d.lookupDefinition("*"));
		System.out.println(d.lookupDefinition(""));
		d.addDefinition("waters", "hello");
		System.out.println(d.lookupDefinition("water"));
		System.out.println(d.lookupDefinition("waterse"));
		d.addDefinition("2", null);
		System.out.println(d.lookupDefinition("2"));
	}

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
		if (word == null || word.length() == 0) {
			return;
		}

		if (!myStartingLetters.containsKey(word.charAt(0))) {
			myStartingLetters.put(word.charAt(0), new TrieNode());

			TrieNode cur = myStartingLetters.get(word.charAt(0));
			for (int i = 1; i < word.length(); i++) { // changed form -1
				cur.getMap().put(word.charAt(i), new TrieNode());
				cur = cur.getMap().get(word.charAt(i));
			}
			cur.createDef(definition);
		} else {
			TrieNode cur = myStartingLetters.get(word.charAt(0));
			for (int i = 1; i < word.length(); i++) {
				if (!cur.getMap().containsKey(word.charAt(i))) {
					cur.getMap().put(word.charAt(i), new TrieNode());
					// System.out.println("added " + word.charAt(i));
				}
				cur = cur.getMap().get(word.charAt(i));
				// System.out.println("inc");
			}
			cur.createDef(definition);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (word == null || word.length() == 0) {
			return null;
		}

		if (!myStartingLetters.containsKey(word.charAt(0))) {
			// System.out.println("no key");
			return null;
		} else {
			TrieNode cur = myStartingLetters.get(word.charAt(0));
			for (int i = 1; i < word.length(); i++) {
				if (!cur.getMap().containsKey(word.charAt(i))) {
					// System.out.println("bye");
					return null;
				}
				cur = cur.getMap().get(word.charAt(i));
				// System.out.println(word.charAt(i) + " " + cur.getDef());
				// System.out.println("found key " + word.charAt(i));

			}
			// System.out.println("out of for loop");
			return cur.getDef();
		}

	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}

		private void createDef(String def) {
			myDefinition = def;
		}

		private HashMap<Character, TrieNode> getMap() {
			return myNextLetters;
		}

		private String getDef() {
			return myDefinition;
		}
	}
}