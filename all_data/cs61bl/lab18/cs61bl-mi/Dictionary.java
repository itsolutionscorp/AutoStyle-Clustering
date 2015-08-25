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
		char[] allChars = word.toCharArray();
		if (!myStartingLetters.containsKey(allChars[0])) {
			Character toPut = allChars[0];
			myStartingLetters.put(toPut, new TrieNode());
			if (allChars.length == 1) {
				myStartingLetters.put(toPut, new TrieNode(definition));
				return;
			}
		}
		else if (myStartingLetters.containsKey(allChars[0]) && allChars.length == 1) {
			myStartingLetters.get(allChars[0]).myDefinition = definition;
		}
		HashMap<Character, TrieNode> currMap = myStartingLetters.get(allChars[0]).myNextLetters;
		for (int i = 1; i < allChars.length; i++) {
			Character toPut = allChars[i];
			if (!currMap.containsKey(toPut) && i == allChars.length - 1) {
				currMap.put(toPut, new TrieNode(definition));
				return;
			}
			else if (!currMap.containsKey(toPut)) {
				currMap.put(toPut, new TrieNode());
			}
			else if (currMap.containsKey(toPut) && i == allChars.length - 1) {
				currMap.get(toPut).myDefinition = definition;
			}			
			currMap = currMap.get(toPut).myNextLetters;
			
			
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		char[] allChars = word.toCharArray();
		if (allChars.length == 1) {
			if (!myStartingLetters.containsKey(allChars[0])) {
				return null;
			}
			else {
				return myStartingLetters.get(allChars[0]).myDefinition;
			}
		}
		if (!myStartingLetters.containsKey(allChars[0])) {
			return null;
		}
		HashMap<Character, TrieNode> currMap = myStartingLetters.get(allChars[0]).myNextLetters;
		for (int i = 1; i < allChars.length; i++) {
			Character toPut = allChars[i];
			if (!currMap.containsKey(toPut)) {
				return null;
			}
			else if (currMap.containsKey(toPut) && i == allChars.length - 1) {
				return currMap.get(toPut).myDefinition;
			}
			currMap = currMap.get(toPut).myNextLetters;
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
		
		private TrieNode(String def) {
			myNextLetters = new HashMap<>();
			myDefinition = def;
		}
	}
	
	public static void main(String[] args) {
		Dictionary myDictionary = new Dictionary();
		myDictionary.addDefinition("hat", "headwear");
		myDictionary.addDefinition("hates", "currently dislikes");
		myDictionary.addDefinition("haters", "people who currently dislike");
		System.out.println(myDictionary.lookupDefinition("haters"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}