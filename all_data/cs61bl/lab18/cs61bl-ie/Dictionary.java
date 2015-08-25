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
		TrieNode t = new TrieNode();
		if(!myStartingLetters.containsKey(word.charAt(0))){
			for(int i = 0; i <word.length(); i++){
				TrieNode temp = new TrieNode();
				if(i == word.length()-1){
					temp.myDefinition = definition;
					t.myNextLetters.put(word.charAt(i), temp);
				} else{
					t.myNextLetters.put(word.charAt(i), temp);
				}
			}
			myStartingLetters.put(word.charAt(0), t);
		} else{
			for(int i = 0; i <word.length(); i++){
				TrieNode temp = myStartingLetters.get(word.charAt(0));
				if(!temp.myNextLetters.containsKey(word.charAt(i))){
					temp.myNextLetters.put(word.charAt(i), temp);
				}
			}
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if(!myStartingLetters.containsKey(word.charAt(0))){
			return null;
		}else{
			TrieNode myT = myStartingLetters.get(word.charAt(0));
			for(int i = 0; i <word.length(); i++){
				if(!myT.myNextLetters.containsKey(word.charAt(i))){
					return null;
				}
			}
			return myT.myNextLetters.get(word.charAt(word.length()-1)).myDefinition;
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
}