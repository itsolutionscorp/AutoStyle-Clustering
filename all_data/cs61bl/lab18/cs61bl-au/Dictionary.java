import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	public HashMap<Character, TrieNode> myStartingLetters;

	public Dictionary() {
		myStartingLetters = new HashMap<>();
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		// TODO your code here!
		if (myStartingLetters.get(word.charAt(0)) == null) myStartingLetters.put(word.charAt(0), new TrieNode());
		myStartingLetters.get(word.charAt(0)).addHelper(word.substring(1), definition);
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (myStartingLetters.get(word.charAt(0)) == null) return null;
		return myStartingLetters.get(word.charAt(0)).findHelper(word.substring(1));
	}

	public class TrieNode {
		public HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
			myDefinition = null;
		}
		
		private TrieNode(String d){
			myDefinition = d;
		}
	
		private void addHelper(String word, String definition){
			if (word.length() == 1) {
				myNextLetters.put(word.charAt(0), new TrieNode(definition));
				return;
			}
			
			if (myNextLetters.get(word.charAt(0)) == null) myNextLetters.put(word.charAt(0), new TrieNode());
			myNextLetters.get(word.charAt(0)).addHelper(word.substring(1), definition);
		}
		
		private String findHelper(String word){
			if (myNextLetters.get(word.charAt(0)) == null) return null;
			if (word.equals("")) return myDefinition;
			return myNextLetters.get(word.charAt(0)).findHelper(word.substring(1));
		}
	}
	
	public static void main (String[] args){
//		Dictionary d = new Dictionary();
//		d.addDefinition("abc", "ysy");
//		d.addDefinition("abd", "wow");
//		System.out.println(d.myStartingLetters.get('a').myNextLetters.get('b').myNextLetters.get('c').myDefinition);
//		System.out.println(d.lookupDefinition("Abc"));
	}
}