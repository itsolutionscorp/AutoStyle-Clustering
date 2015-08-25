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
		if (word == null){
			return;
		}
		TrieNode curr = null;
		HashMap <Character, TrieNode> hashCurr = myStartingLetters;
		for (int i = 0; i < word.length() - 1; i++){
			curr = hashCurr.get(word.charAt(i));
			if (curr == null){
				hashCurr.put(word.charAt(i), new TrieNode());
				curr = hashCurr.get(word.charAt(i));
			} 
			hashCurr = curr.myNextLetters;
		}
		if (hashCurr.containsKey(word.charAt(word.length() - 1))) {
			curr = hashCurr.get(word.charAt(word.length() - 1));
			curr.myDefinition = definition;
		} else {
			curr = new TrieNode();
			curr.myDefinition = definition;
			hashCurr.put(word.charAt(word.length() - 1), curr);
		}
	}

//	/**
//	 * Associates the input word with the input definition in the dictionary.
//	 */
//	public void addDefinition(String word, String definition) {
//		// TODO your code here!
//		if (word == null){
//			return;
//		}
//		TrieNode curr = null;
//		HashMap <Character, TrieNode> hashCurr = myStartingLetters;
//		for (int i = 0; i < word.length()-1; i++){
//			curr = hashCurr.get(word.charAt(i));
//			if (curr == null){
//				hashCurr.put(word.charAt(i), new TrieNode());
//				curr = hashCurr.get(word.charAt(i));
//			} 
//			hashCurr = curr.myNextLetters;
//		}
//		if (hashCurr.containsKey(word.charAt(word.length()-1))) {
//			curr = hashCurr.get(word.charAt(word.length()-1));
//			curr.myDefinition = definition;
//		} else {
//			curr = new TrieNode();
//			curr.myDefinition = definition;
//			hashCurr.put(word.charAt(word.length()-1), curr);
//		}
//	}
	


	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word. 
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (word == null){
			return null;
		}
		TrieNode curr = myStartingLetters.get(word.charAt(0));
		if (word.length() == 1){
			return curr.myDefinition;
		}
		for (int i = 1; i < word.length(); i++){
			curr = curr.myNextLetters.get(word.charAt(i));
			if (curr == null){
				return null;
			}
		}
		return curr.myDefinition;
		
	}
	
	
	

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}
	
	

	
	
}