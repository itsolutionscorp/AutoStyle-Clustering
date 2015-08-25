package lab18;
import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	private HashMap<Character, TrieNode> myStartingLetters;
	private HashMap<TrieNode,String> wordsDefinition;
	

	public Dictionary() {
		myStartingLetters = new HashMap<>();
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		// TODO your code here!
		helpaddDefinition(0,word,definition);
	}
	
	public void helpaddDefinition(int n,String word, String definition){
		if(n<word.length()){
			if(!myStartingLetters.get((word.substring(n, n+1))).myNextLetters.containsKey((word.substring(n+1, n+2)))){
				myStartingLetters.get((word.substring(n, n+1))).myNextLetters.put((word.substring(n+1, n+2)),
						myStartingLetters.get((word.substring(n, n+1))))
			}
			helpaddDefinition(n+1,word,definition);
		}
		else{
			wordsDefinition.put(myStartingLetters.get(word.substring(n, n+1))
					.myNextLetters.get(word.substring(n+1, n+2)), definition);
		}
	}
	

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		return helplookupDefinition(0,word,definition);
	}
	
	public String helplookupDefinition(int n, String word){
		if(n<word.length()){
			if(!myStartingLetters.get((word.substring(n, n+1))).myNextLetters.containsKey((word.substring(n+1, n+2)))){
				myStartingLetters.get((word.substring(n, n+1))).myNextLetters.put((word.substring(n+1, n+2)),
						myStartingLetters.get((word.substring(n, n+1))))
			}
			helplookupDefinition(n+1,word);
		}
		
		return	wordsDefinition.get(myStartingLetters.get(word.substring(n, n+1))
					.myNextLetters.get(word.substring(n, n+1));
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;
		//private HashMap<TideNode, String> Definition;
		
		
		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
			//Definition =new HashMap<>();
			//Definition.put(myNextLetters,myDefinition);
		}
	}
}