import java.util.ArrayList;
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
		char firstChar = word.charAt(0);
		if (myStartingLetters.get(firstChar) == null) {
			myStartingLetters.put(firstChar, new TrieNode());
			myStartingLetters.get(firstChar).addDefHelper(word.substring(1), definition);
		} else {
			myStartingLetters.get(firstChar).addDefHelper(word.substring(1), definition);
		}

	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (word != null) {
			return myStartingLetters.get(word.charAt(0)).lookUpHelper(word.substring(1));
		}
		return null;
	}
	public static void main(String[] arg) {
		Dictionary d = new Dictionary();
		d.addDefinition("can", "can i fuck you");
		d.addDefinition("cat", "animal");
		d.addDefinition("cap", "hat");
		d.addDefinition("two", "2");
		d.addDefinition("too", "also");
		System.out.println("");
		System.out.println(d.lookupDefinition("can"));
		System.out.println(d.lookupDefinition("cat"));
		System.out.println(d.lookupDefinition("cap"));
		System.out.println(d.lookupDefinition("two"));
		System.out.println(d.lookupDefinition("too"));
		System.out.println(d.lookupDefinition("cad"));
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private void setDefinition(String def){
			myDefinition = def;
		}

		private void addDefHelper(String subWord, String definition) {
			char firstChar = subWord.charAt(0);
			if (myNextLetters.get(firstChar) == null) {
				if (subWord.length() == 1) {
					TrieNode tn = new TrieNode();
					tn.setDefinition(definition);
					myNextLetters.put(firstChar, tn);
				} else {
					myNextLetters.put(firstChar, new TrieNode());
					myNextLetters.get(firstChar).addDefHelper(subWord.substring(1), definition);
				}
			} else {
				myNextLetters.get(firstChar).addDefHelper(subWord.substring(1), definition);
			}
		}

		private String lookUpHelper(String subWord) {
			char firstChar = subWord.charAt(0);
			if ( myNextLetters.get(firstChar) != null) {
				if (subWord.length() == 1) {
					return myNextLetters.get(firstChar).myDefinition;
				} else {
					return myNextLetters.get(firstChar).lookUpHelper(subWord.substring(1));
				}
			} else {
				return null;
			}
		}
	}
	
}