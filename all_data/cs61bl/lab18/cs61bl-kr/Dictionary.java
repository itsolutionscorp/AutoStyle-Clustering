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
		char[] added = word.toCharArray(); 
		TrieNode toBeAdded = new TrieNode(); 
		TrieNode next = new TrieNode(); 
		int counter = 0;
		while (myStartingLetters.containsKey(added[counter])) {
			counter++; 
		}
		for (int i = counter; i < added.length; i++) {
			next = addNode(toBeAdded,added[i]); 
			toBeAdded = next; 
			}
		toBeAdded.terminal = true; 
		toBeAdded.myDefinition = definition;
		}
	
	public TrieNode addNode(TrieNode t, char c) {  
		TrieNode added = new TrieNode(); 
		t.myNextLetters.put(c, added);
		return added; 
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		char[] toLookUp = word.toCharArray();  
		TrieNode current = new TrieNode(); 
		TrieNode found = new TrieNode(); 
		int i = 0; 
		while(myStartingLetters.containsKey(toLookUp[i])) {
			i++; 	
		}
		current = myStartingLetters.get(toLookUp[i]);
		while(current.myNextLetters.containsKey(toLookUp[i])) {
			i++; 
		}
		found = current.myNextLetters.get(toLookUp[i]); 
		
		if (found.terminal) {
			return found.myDefinition;
		}
		return null; 
	}
	
	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;
		private boolean terminal = false; 

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
			
		}
	}
}