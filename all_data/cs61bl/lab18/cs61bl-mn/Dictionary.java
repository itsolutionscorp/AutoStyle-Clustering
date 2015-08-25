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
		Character firstLetter = word.charAt(0);
		if (myStartingLetters.get(firstLetter) == null) {
			myStartingLetters.put(firstLetter, new TrieNode());
		}
		TrieNode curNode = myStartingLetters.get(firstLetter);
		int i = 1;
		while (i < word.length()) {
			Character curChar = word.charAt(i);
			if (curNode.myNextLetters.get(curChar) == null) {
				curNode.myNextLetters.put(curChar, new TrieNode());
			}
			curNode = curNode.myNextLetters.get(curChar);
			i++;
		}
		curNode.myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode curNode = myStartingLetters.get(word.charAt(0));
		if (curNode == null) {
			return null;
		}
		int i = 1;
		while (i<word.length()) {
			curNode = curNode.myNextLetters.get(word.charAt(i));
			if (curNode == null) {
				return null;
			}
			i++;
		}
		return curNode.myDefinition;
	}
	
	public static void main (String[] args) {
		Dictionary dict = new Dictionary();
		//lower case upper case differentiation
		dict.addDefinition("Cap", "A hat");
		dict.addDefinition("cap", "a lower case hat");
		System.out.println(dict.lookupDefinition("Cap"));
		System.out.println(dict.lookupDefinition("cap"));
		//null definition
		System.out.println(dict.lookupDefinition("Ca"));
		//adding Car doesn't destroy Cat
		dict.addDefinition("Car", "A type of vehicle");
		System.out.println(dict.lookupDefinition("Car"));
		System.out.println(dict.lookupDefinition("Cap"));
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