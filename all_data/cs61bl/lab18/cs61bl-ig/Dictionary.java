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
	
	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.addDefinition("", "cat");
		System.out.println(dict.lookupDefinition(""));
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {

		HashMap<Character, TrieNode> current = myStartingLetters;
		TrieNode node = new TrieNode();
		while (true) {
			if(word.length() == 0) {
				node.myDefinition = definition;
				break;
			}
			
			char c = word.charAt(0);
			if (!current.containsKey(c)) {
				current.put(c, new TrieNode());
				//System.out.println("added c");
			}
			node = current.get(c);
			current = current.get(c).myNextLetters;
			//node = current.get(c);
			
			word = word.substring(1);
		}

	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		HashMap<Character, TrieNode> start = myStartingLetters;
		TrieNode node = new TrieNode();
		//String def = null;
		while(word.length() != 0) {
			char c = word.charAt(0);
			node = start.get(c);
			start = start.get(c).myNextLetters;
			
			
			word = word.substring(1);
		}
		return node.myDefinition;
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