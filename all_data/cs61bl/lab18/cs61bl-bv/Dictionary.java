import java.util.HashMap;

public class Dictionary {
	
	public static void main (String[] args) {
		Dictionary d = new Dictionary();
		String dog = "dog";
		String dog_def = "this is a dog";
		String dairy = "dairy";
		String dairy_def = "this is a dairy product";
		String hairy = "hairy";
		String hairy_def = "this describes something with many hairs";
		d.addDefinition(dog, dog_def);
		d.addDefinition(dairy, dairy_def);
		d.addDefinition(hairy, hairy_def);
		
		String def_1 = d.lookupDefinition(dog);
		System.out.println(def_1);
		
		String def_2 = d.lookupDefinition(hairy);
		System.out.println(def_2);
		
		String def_3 = d.lookupDefinition(dairy);
		System.out.println(def_3);
		
		String def_4 = d.lookupDefinition("win");
		System.out.println(def_4);
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
		// TODO your code here!
		String help_word = word.substring(1);
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			TrieNode next_Letter = new TrieNode();
			myStartingLetters.put(word.charAt(0), next_Letter);
			next_Letter.helperAdd(help_word, definition);

		} else {
			myStartingLetters.get(word.charAt(0)).helperAdd(help_word, definition);
		}

	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		String help_word = word.substring(1);
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			return null;
		} else {
			return myStartingLetters.get(word.charAt(0)).helperLookUp(help_word);
		}
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}

		public void helperAdd (String word, String definition) {

			String sub_word = word.substring(1);
			Character c = word.charAt(0);

			if (myNextLetters.containsKey(c)) {
				if (word.length() == 1) {
					myNextLetters.get(c).myDefinition = definition;
				} else {
					myNextLetters.get(c).helperAdd(sub_word, definition);
				}
			} else {
				TrieNode next_node = new TrieNode();
				if (word.length() == 1) {
					next_node.myDefinition = definition;
					myNextLetters.put(c, next_node);
				} else {		
					myNextLetters.put(c, next_node);
					next_node.helperAdd(sub_word, definition);
				}
			}
		}

		public String helperLookUp (String word) {

			String sub_word = word.substring(1);
			Character c = word.charAt(0);

			if (myNextLetters.containsKey(c)) {
				if (word.length() == 1) {
					return myNextLetters.get(c).myDefinition;
				} else {
					return myNextLetters.get(c).helperLookUp(sub_word);
				}
			} else {
				return null;
			}
		}

	}

}