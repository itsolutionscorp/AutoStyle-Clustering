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
		TrieNode latestNode = null;
		Character first = new Character(word.charAt(0));
		if (!(myStartingLetters.containsKey(first))) {
			TrieNode newNode = new TrieNode();
			latestNode = newNode;
			myStartingLetters.put(first, newNode);
		}
		if (latestNode == null) {
			latestNode = myStartingLetters.get(first);
		}
		word = word.substring(1);
		while (word.length() > 1) {
			Character nextLetter = new Character(word.charAt(0));
			if (latestNode.getLetters().containsKey(nextLetter)) {
				latestNode = latestNode.getLetters().get(nextLetter);
			}
			else {
				latestNode = latestNode.addChild(nextLetter);
			}
			word = word.substring(1);
		}
		Character finalChar = new Character(word.charAt(0));
		if (latestNode.getLetters().containsKey(finalChar)) {
			latestNode.setDef(definition);
			return;
		}

		TrieNode finalNode = latestNode.addChild(finalChar);
		finalNode.setDef(definition);
	}
	
	public TrieNode addChild(TrieNode node, Character c) {
		return node.addChild(c);
	}
	
	

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		Character letter = new Character(word.charAt(0));
		TrieNode node = myStartingLetters.get(letter);
		
		while (word.length() > 1) {
			if (node == null) {
				return null;
			}
			word = word.substring(1);
			letter = new Character(word.charAt(0));
			node = node.getLetters().get(letter);
		}
		return node.getDef();
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		public TrieNode addChild(Character c) {
			TrieNode node = new TrieNode();
			myNextLetters.put(c, node);
			return node;
		}
		
		public void setDef(String def) {
			myDefinition = def;
		}
		
		public String getDef() {
			return myDefinition;
		}
		
		public HashMap<Character, TrieNode> getLetters() {
			return myNextLetters;
		}
	}
	
	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.addDefinition("Hello", "A greeting");
		dict.addDefinition("Cal", "Awesome");
		System.out.println(dict.lookupDefinition("Hello"));
		System.out.println(dict.lookupDefinition("Cal"));
	}
}