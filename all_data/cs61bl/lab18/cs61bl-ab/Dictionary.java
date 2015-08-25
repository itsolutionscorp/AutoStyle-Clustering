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
		if(!myStartingLetters.containsKey(word.charAt(0))){
			myStartingLetters.put(word.charAt(0), new TrieNode());
		}
		if(word.length()==1) myStartingLetters.get(word.charAt(0)).add("", definition);
		else myStartingLetters.get(word.charAt(0)).add(word.substring(1), definition);
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if(!myStartingLetters.containsKey(word.charAt(0))){
			return null;
		}
		if (word.length()==1) return myStartingLetters.get(word.charAt(0)).lookup("");
		else return myStartingLetters.get(word.charAt(0)).lookup(word.substring(1));
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private void add(String word, String definition){
			if (word.length()==0){
				myDefinition = definition;
			}else {
				if(!myNextLetters.containsKey(word.charAt(0))){
					myNextLetters.put(word.charAt(0), new TrieNode());
				}
				if(word.length()==1) myNextLetters.get(word.charAt(0)).add("", definition);
				else myNextLetters.get(word.charAt(0)).add(word.substring(1), definition);
			}
		}
		private String lookup(String word){
			if(word.equals("")) return myDefinition;
			if(!myNextLetters.containsKey(word.charAt(0)))return null;
			if (word.length()==1) return myNextLetters.get(word.charAt(0)).lookup("");
			else return myNextLetters.get(word.charAt(0)).lookup(word.substring(1));
		}
	}
	
	public static void main(String[] args){
		Dictionary myDic = new Dictionary();
		myDic.addDefinition("abc", "stupid");
		System.out.println(myDic.lookupDefinition("abc"));
		myDic.addDefinition("to", "haha");
		myDic.addDefinition("too", "confusion");
		System.out.println(myDic.lookupDefinition("to"));
		System.out.println(myDic.lookupDefinition("too"));
		System.out.println(myDic.lookupDefinition("ab"));
		System.out.println(myDic.lookupDefinition("t"));
	}
}