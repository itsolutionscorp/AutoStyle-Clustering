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
		TrieNode myMap = myStartingLetters.get(word.charAt(0));
		if(word.length()==1){
			myMap.myDefinition = definition;
		}
		for(int i = 1; i < word.length(); i++){
			myMap = myMap.myNextLetters.get(word.charAt(i));
			if(i == word.length()-1){
				myMap.myDefinition = definition;
				break;
			}
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		TrieNode myMap = myStartingLetters.get(word.charAt(0));
		if(word.length()==1){
			return myMap.myDefinition;
		}
		for(int i = 1; i < word.length(); i++){
			myMap = myMap.myNextLetters.get(word.charAt(i));
			if(i == word.length()-1){
				return myMap.myDefinition;
			}
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
	public static void main(String[]args){
		Dictionary myD = new Dictionary();
		TrieNode myT1 = myD.new TrieNode();
		TrieNode myT2 = myD.new TrieNode();
		TrieNode myT3 = myD.new TrieNode();
		TrieNode myT4 = myD.new TrieNode();
		myT4.myDefinition = null;
		myT4.myNextLetters = null;
		myT3.myDefinition = "a kind of insect";
		myT3.myNextLetters.put('i', myT4);
		myT2.myDefinition = "one";
		myT2.myNextLetters.put('t', myT3);
		myT1.myDefinition = "Uppercase a";
		myT1.myNextLetters.put('n', myT2);
		myD.myStartingLetters.put('A', myT1);
		//System.out.println(myD.lookupDefinition("Anti"));
		myD.addDefinition("Anti", "counter");
		System.out.println(myD.lookupDefinition("Anti"));
		
	}
}