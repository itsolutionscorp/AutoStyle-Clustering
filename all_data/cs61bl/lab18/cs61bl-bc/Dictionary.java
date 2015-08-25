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
		myStartingLetters.put(word.charAt(0), new TrieNode());
		if (word.length() == 1) {
			myStartingLetters.get(word.charAt(0)).myDefinition = definition;
		}
		else {
			char[] wordCharacters = word.toCharArray();
			TrieNode wordPointer = myStartingLetters.get(word.charAt(0));
			for (int i = 1; i < wordCharacters.length; i++) {
				wordPointer.myNextLetters.put(wordCharacters[i], new TrieNode());
				wordPointer = wordPointer.myNextLetters.get(wordCharacters[i]);
			}
			wordPointer.myDefinition = definition;
		}
	}
	
	

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (word.length() == 1) {
			return myStartingLetters.get(word.charAt(0)).myDefinition;
		}
		else if (!myStartingLetters.containsKey(word.charAt(0))) {
			return null;
		}
		else if (myStartingLetters.containsKey(word.charAt(0))){
			char[] wordCharacters = word.toCharArray();
			TrieNode wordPointer = myStartingLetters.get(word.charAt(0));
			for (int i = 1; i < wordCharacters.length; i++) {
				if (wordPointer.myNextLetters.containsKey(wordCharacters[i])) {
					wordPointer = wordPointer.myNextLetters.get(wordCharacters[i]);
				}
			}
			return wordPointer.myDefinition;
		}
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
	
	public static void main(String[] args) {
		Dictionary myDictionary = new Dictionary();
		myDictionary.addDefinition("Nancy", "a cool person");
		System.out.println("definition of Nancy: " + myDictionary.lookupDefinition("Nancy"));
		myDictionary.addDefinition("Nava", "another cool person");
		System.out.println("definition of Nava: " + myDictionary.lookupDefinition("Nava"));
	}
}