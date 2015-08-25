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
		Character firstLetter = word.charAt(0);
		String substring = word.substring(1);
		if (myStartingLetters.containsKey(firstLetter) == false) {
			TrieNode next = new TrieNode();
			next.addDefinition(substring, definition);
			myStartingLetters.put(firstLetter, next);
		} else {
			TrieNode next = myStartingLetters.get(firstLetter);
			next.addDefinition(substring, definition);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		Character firstLetter = word.charAt(0);
		String substring = word.substring(1);
		if (myStartingLetters.containsKey(firstLetter) == false) return null;
		else {
			return myStartingLetters.get(firstLetter).lookupDefinition(substring);
		}
	}
	
	
	public static void main (String[] args) {
		Dictionary test = new Dictionary();
		test.addDefinition("Boba", "yummy");
		System.out.println(test.lookupDefinition("Boba"));
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private void addDefinition(String word, String definition) {
			if (word.isEmpty()) {
				myDefinition = definition;
				return;
			}
			Character firstLetter = word.charAt(0);
			String substring = word.substring(1);
			if (myNextLetters.containsKey(firstLetter) == false) {
				TrieNode next = new TrieNode();
				next.addDefinition(substring, definition);
				myNextLetters.put(firstLetter, next);
			} else {
				TrieNode next = myNextLetters.get(firstLetter);
				next.addDefinition(substring, definition);
			}	
		}
		
		private String lookupDefinition(String word) {
			if (word.isEmpty()) {
				return myDefinition;
			}
			Character firstLetter = word.charAt(0);
			String substring = word.substring(1);
			if (myNextLetters.containsKey(firstLetter) == false) return null;
			else {
				return myNextLetters.get(firstLetter).lookupDefinition(substring);
			}
		}
	}
}