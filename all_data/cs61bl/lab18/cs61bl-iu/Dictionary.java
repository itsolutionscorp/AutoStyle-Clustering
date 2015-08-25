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
		if (myStartingLetters.containsKey(word.charAt(0))){
			int counter = 0;
			while(counter < word.length()){
				myStartingLetters.put(word.charAt(counter), new TrieNode());
			}
		}
		
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (!myStartingLetters.containsKey(word.charAt(0))){
			return null;
		} 
		int i = 0;
		HashMap<Character, TrieNode> pointer = myStartingLetters;
		while(pointer.get(word.charAt(i)).myNextLetters.containsKey(word.charAt(i + 1))){
			if (pointer.get(word.charAt(i)).myNextLetters == null){
				return null;
			}
			pointer = pointer.get(word.charAt(i)).myNextLetters;
			i ++;
		}
		return pointer.get(word.charAt(i - 1)).getDef();
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
			
		}
		
		public String getDef(){
			return myDefinition;
		}
		
		private TrieNode(String ch, String def){
			HashMap<Character, TrieNode> pointer = myNextLetters;
			if (ch.length() == 1){
				myDefinition = def;
				if (myNextLetters == null){
					myNextLetters = new HashMap<>();
				}
			} else{
				int counter = 0;
				while (pointer.containsKey(ch.charAt(counter))){
					if(pointer.get(ch.charAt(counter)).myNextLetters != null){
						pointer = pointer.get(ch.charAt(counter)).myNextLetters;
					}
					counter ++;
				} 
					pointer.put(ch.charAt(0), new TrieNode(ch.toString().substring(1), def));
			}
		}
	}
	public static void main(String[] args){
		Dictionary d = new Dictionary();
		d.addDefinition("dog", "animal");
	}
}