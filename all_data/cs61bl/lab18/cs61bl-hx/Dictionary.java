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
		TrieNode current = null;
		
		if (myStartingLetters.containsKey(word.charAt(0))){
			current = myStartingLetters.get(word.charAt(0));
		}
		else{				
			myStartingLetters.put(word.charAt(0), new TrieNode());
			current = myStartingLetters.get(word.charAt(0));
		}
		
		TrieNode temp = current;
		for (int i = 1; i < word.length();i++){			
			
			
			if (current.myNextLetters.containsKey(word.charAt(i))){
				current = current.myNextLetters.get(word.charAt(i));
			}
			else{				
				current.myNextLetters.put(word.charAt(i), new TrieNode());
				current = current.myNextLetters.get(word.charAt(i));
				//temp = current
			}
			temp = current;
			
		}
		temp.myDefinition = definition;
		
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		
		if (!myStartingLetters.containsKey(word.charAt(0))){
			return null;
		}
		TrieNode current = myStartingLetters.get(word.charAt(0));
		TrieNode temp = myStartingLetters.get(word.charAt(0));
		for (int i = 1; i < word.length();i++){			
			
			
			if (current.myNextLetters.isEmpty()){
				return null;
			}
			if (current.myNextLetters.containsKey(word.charAt(i))){
				current = current.myNextLetters.get(word.charAt(i));
			}
			else{				
				return null;
			}
			temp = current;
			
		}
		return temp.myDefinition;	
		
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