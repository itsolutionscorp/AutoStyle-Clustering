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
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			TrieNode myTrie = new TrieNode();
			myStartingLetters.put(word.charAt(0), myTrie);
			myTrie.addDefinition(word,definition,1);
		}
		else {
			myStartingLetters.get(word.charAt(0)).addDefinition(word,definition,1);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			return null;
		}
		return myStartingLetters.get(word.charAt(0)).lookupDefinition(word,1);
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		private void addDefinition(String word, String definition, int n) {
			if (n >= word.length()) {
				myDefinition = definition;
				return;
			}
			else if (myNextLetters.containsKey(word.charAt(n))) {
				myNextLetters.get(word.charAt(n)).addDefinition(word, definition, n+1);
			}
			else {
				TrieNode myOtherTrie = new TrieNode();
				myNextLetters.put(word.charAt(n),myOtherTrie);
				myOtherTrie.addDefinition(word, definition, n+1);
			}
		}
		private String lookupDefinition(String word, int n) {
			if (n >= word.length()) {
				return myDefinition;
			}
			else if (!myNextLetters.containsKey(word.charAt(n))) {
				return null;
			}
			else {
				return myNextLetters.get(word.charAt(n)).lookupDefinition(word,n+1);
			}
		}
	}
	
	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.addDefinition("cat","great animal");
		String str = dict.lookupDefinition("cat");
		System.out.println(str);
		System.out.println(dict.myStartingLetters.containsKey('c'));
		System.out.println(dict.myStartingLetters.get('c').myNextLetters.containsKey('a'));
		System.out.println(dict.myStartingLetters.get('c').myNextLetters.get('a').myNextLetters.containsKey('t'));
		System.out.println(dict.myStartingLetters.get('c').myDefinition);
		System.out.println(dict.myStartingLetters.get('c').myNextLetters.get('a').myNextLetters.get('t').myDefinition);
		dict.addDefinition("octopus", "bro");
		String str2 = dict.lookupDefinition("octopus");
		System.out.println(str2);
		dict.addDefinition("cant","please please work");
		String str3 = dict.lookupDefinition("cant");
		System.out.println(str3);
	}
}