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
		HashMap<Character, TrieNode> temp = new HashMap<>();
		HashMap<Character, TrieNode> end = new HashMap<>();
		temp = myStartingLetters;
		
		for (int counter = 0; counter < word.length(); counter++) {
			if (temp.containsKey(word.charAt(counter))) {
				temp = temp.get(word.charAt(counter)).myNextLetters;
			}
			else {
				temp.put(word.charAt(counter), new TrieNode());
				end = temp;
				temp = temp.get(word.charAt(counter)).myNextLetters;

			}
		}
		end.get(word.charAt(word.length() - 1)).myDefinition = definition;
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		HashMap<Character, TrieNode> temp = new HashMap<>();
		HashMap<Character, TrieNode> end = new HashMap<>();
		temp = myStartingLetters;
		
		for (int counter = 0; counter < word.length(); counter++) {
			if (temp.containsKey(word.charAt(counter))) {
				end = temp;
				temp = temp.get(word.charAt(counter)).myNextLetters;
			}
			else {
				return null;
			}
		}
		return end.get(word.charAt(word.length() - 1)).myDefinition;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}
	
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.addDefinition("cat", "kitty meow");
		d.addDefinition("can", "aluminum");
		d.addDefinition("cap", "hat");		
		System.out.println(d.lookupDefinition("cat"));
		System.out.println(d.lookupDefinition("can"));
		System.out.println(d.lookupDefinition("cap"));
		System.out.println(d.lookupDefinition("car"));

	}
}