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
		if (!myStartingLetters.keySet().contains(word.charAt(0))) {
			TrieNode newLetter = new TrieNode();
			myStartingLetters.put(word.charAt(0), newLetter);
			if (word.length() == 1) {
				newLetter.myDefinition = definition;
			}
			else {
				newLetter.addNextLetters(word.substring(1), definition);
			}
		}
		else {
			if (word.length() == 1) {
				myStartingLetters.get(word.charAt(0)).myDefinition = definition;
			}
			else {
				myStartingLetters.get(word.charAt(0)).addNextLetters(word.substring(1), definition);
			}
		}
	}
	
	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			return null;
		} else {
			return myStartingLetters.get(word.charAt(0)).lookup(word);
		}
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private void addNextLetters(String word, String definition) {
			if (word.length() == 0) {
				myDefinition = definition;
			}
			else {
				if (!myNextLetters.containsKey(word.charAt(0))) {
					TrieNode nextLetter = new TrieNode();
					myNextLetters.put(word.charAt(0), nextLetter);
					nextLetter.addNextLetters(word.substring(1), definition);					
				}
				else {
					myNextLetters.get(word.charAt(0)).addNextLetters(word.substring(1), definition);
				}
			}	
		}
		
		private String lookup(String word) {
			if (word.length() == 1) {
				return myDefinition;
			} 
			else {
				return myNextLetters.get(word.charAt(1)).lookup(word.substring(1));
			}
		}
	}
	
	public static void main (String[] args) {
		Dictionary d = new Dictionary();
		d.addDefinition ("dog", "cute and furry domestic animal");
		System.out.println(d.lookupDefinition("dog"));
		d.addDefinition ("dig", "excavate");
		System.out.println(d.lookupDefinition("dig"));
		d.addDefinition ("kitten", "baby cat");
		System.out.println(d.lookupDefinition("kitten"));
		d.addDefinition ("antidisestablishmentarianism", "who knows! something to do with the Church of England");
		System.out.println(d.lookupDefinition("antidisestablishmentarianism"));


	}
}