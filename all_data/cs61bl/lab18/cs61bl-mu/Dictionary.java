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
		if (word == null){
			return;
		}
		TrieNode node = null;
		HashMap<Character, TrieNode> current = myStartingLetters;
		for (char x : word.toCharArray()){
			node = current.get(x);
			if (node  == null){
				current.put(x, new TrieNode());
				node = current.get(x);
			}
			current = node.myNextLetters;
		}
		node.myDefinition = definition;
	}
	

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (word == null){
			return null;
		}
		TrieNode node = null;
		HashMap<Character, TrieNode> current = myStartingLetters;
		for (char x : word.toCharArray()){
			node = current.get(x);
			if (node == null){
				return null;
			}
			current = node.myNextLetters;
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
//	public static void main(String[] args) {
//		Dictionary book = new Dictionary();
//		book.addDefinition("remove","exit");
//		book.addDefinition("removes","exits");
//		System.out.println(book.lookupDefinition("removes"));
//		System.out.println(book.lookupDefinition("rem"));
//		book.addDefinition("add","put");
//		book.addDefinition("adds","puts");
//		System.out.println(book.lookupDefinition("add"));
//		book.addDefinition("where","to");
//		System.out.println(book.lookupDefinition("where"));
//		System.out.println(book.lookupDefinition("Add"));
//		System.out.println(book.lookupDefinition("rEMO"));
//		
//		
//	}
}