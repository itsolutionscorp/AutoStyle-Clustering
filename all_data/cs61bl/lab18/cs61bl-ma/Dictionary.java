import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	private HashMap<Character, TrieNode> myStartingLetters;
	
	public static void main(String[] args) {
		Dictionary d = new Dictionary(); 
		d.addDefinition("top", "kek");
		System.out.println(d.lookupDefinition("top"));
		d.addDefinition("rare", "pepe"); 
		System.out.println(d.lookupDefinition("rare"));
		d.addDefinition("toph", "fox"); 
		System.out.println(d.lookupDefinition("toph"));
	}

	public Dictionary() {
		myStartingLetters = new HashMap<>();
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		HashMap<Character, TrieNode> letters = myStartingLetters;
		TrieNode n = null;
		while (word.length() != 0) {
			char firstLetter = word.charAt(0);
			if (n != null) {
				letters = n.getLetters();
			}
			if (letters.containsKey(firstLetter)) {
				n = letters.get(firstLetter);
			} else {
				n = new TrieNode();
				letters.put(firstLetter, n);
			}
			word = word.substring(1);
		}
		n.setDefinition(definition);
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		HashMap<Character, TrieNode> letters = myStartingLetters;
		TrieNode n = null;
		while (word.length() != 0) {
			char firstLetter = word.charAt(0); 
			if (n != null) {
				letters = n.getLetters(); 
			}
			if (letters.containsKey(firstLetter)) {
				n = letters.get(firstLetter); 
			} else {
				return null; 
			}
			word = word.substring(1);
		}
		return n.getDefinition();
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;
		
		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		public void setDefinition(String definition) {
			myDefinition = definition; 
		}
		
		public String getDefinition() {
			return myDefinition;
		}
		
		public HashMap<Character, TrieNode> getLetters() {
			return myNextLetters;
		}
	}
}