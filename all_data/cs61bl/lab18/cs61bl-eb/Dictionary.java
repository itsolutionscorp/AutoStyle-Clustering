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
		Character first = word.charAt(0);
		TrieNode letter = myStartingLetters.get(first);
		if (letter == null) {
			myStartingLetters.put(first, new TrieNode()); 
			letter = myStartingLetters.get(first);
		}  
		// Note: we cannot have else because we have to keep adding 
		// 			the word if it is not there 
		for (int i = 1; i < word.length(); i++) {
			// look for char at index i 
			Character current =  word.charAt(i); 
			TrieNode node = letter.myNextLetters.get(current); 
			if (node == null) {
				letter.myNextLetters.put(current, new TrieNode());
				letter = letter.myNextLetters.get(current);
			} else {
				letter = node; 
			}
		}
		letter.myDefinition = definition; 

	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		Character first = word.charAt(0);
		TrieNode letter = myStartingLetters.get(first);
		if (letter == null) {
			return null;
		} else {
			for (int i = 1; i < word.length(); i++) {
				// look for char at index i 
				Character current =  word.charAt(i); 
				TrieNode node = letter.myNextLetters.get(current); 
				if (node == null) {
					return null; 
				} else {
					letter = node; 
				}
			}
			return letter.myDefinition; 
		}
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
		d.addDefinition("Hilfinger", "a professor"); 
		d.addDefinition("Hitler", "a dictator"); 
		d.addDefinition("Hillegas", "a street in Berkeley"); 
		d.addDefinition("tree", "a plant"); 
		d.addDefinition("Tree", "an important plant"); 
		d.addDefinition("tea", "a drink"); 
		System.out.println("the definition of Hilfinger is " + d.lookupDefinition("Hilfinger"));
		System.out.println("the definition of Hitler is " + d.lookupDefinition("Hitler")); 
		System.out.println("the definition of Hillegas is " + d.lookupDefinition("Hillegas")); 
		System.out.println("the definition of tree is " + d.lookupDefinition("tree")); 
		System.out.println("the definition of Tree is " + d.lookupDefinition("Tree")); 
		System.out.println("the definition of tea is " + d.lookupDefinition("tea")); 
		System.out.println("the definition of Tea is " + d.lookupDefinition("Tea")); 
		System.out.println("the definition of Hilfinge is " + d.lookupDefinition("Hilfinge"));
		System.out.println("the definition of Hilfingerr is " + d.lookupDefinition("Hilfingerr"));
		
		System.out.println("\nnow modify the definition of tea");
		d.addDefinition("tea", "a drink modified"); 
		System.out.println("the definition of tea is " + d.lookupDefinition("tea")); 
	}
}