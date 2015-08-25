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
		if (myStartingLetters.containsKey(word.charAt(0)) == false) {
			myStartingLetters.put(word.charAt(0), new TrieNode());
//			System.out.println("did it");
		} TrieNode current = myStartingLetters.get(word.charAt(0));
		for (int i = 1; i < word.length(); i++) {
			char c = word.charAt(i);
			if(current.myNextLetters.containsKey(c)) {
				current = current.myNextLetters.get(c);
//				System.out.println("already here");
				continue;
			}
			if (i+ 1 == word.length()) {
				current.myNextLetters.put(c, new TrieNode());
				current = current.myNextLetters.get(c);
				current.myDefinition = definition;
//				System.out.println("added def");
				return;
			}else 
				current.myNextLetters.put(c, new TrieNode());
				current = current.myNextLetters.get(c);
//				System.out.println("continuing input");
		} 	
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (myStartingLetters.containsKey(word.charAt(0))) {
			TrieNode current = myStartingLetters.get(word.charAt(0));
			for (int i = 1; i < word.length(); i++) {
				char c = word.charAt(i);
				if (current.myNextLetters.containsKey(c)) {
					if(i + 1 == word.length()) {
						String def = current.myNextLetters.get(c).myDefinition;
//						System.out.println("found def");
//						System.out.println(def);
						return def;
					}else 
						current = current.myNextLetters.get(c);
//						System.out.println("still searching for def");
						continue;
				}else 
					return null;
			}
		}
//		System.out.println("terminating");
		return null;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
	}
	
//	public static void main(String[] args) {
//		Dictionary dict = new Dictionary();
//		dict.addDefinition("food", "edible");
//		System.out.println(dict.lookupDefinition("drink"));
//		System.out.println(dict.lookupDefinition("food"));
//		dict.addDefinition("drink", "thirst quenching");
//		System.out.println(dict.lookupDefinition("drink"));
//		dict.addDefinition("drum", "beat it good");
//		System.out.println(dict.lookupDefinition("drum"));
//		dict.addDefinition("foody", "looks for food");
//		System.out.println(dict.lookupDefinition("foody"));
//	}
	
}