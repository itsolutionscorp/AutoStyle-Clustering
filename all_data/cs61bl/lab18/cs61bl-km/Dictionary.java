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
			if (word.length() >= 3) {
				myStartingLetters.put(word.charAt(0), new TrieNode(word.charAt(1),word.substring(2)) );
			}
			else if (word.length() == 2) {
				myStartingLetters.put(word.charAt(0),new TrieNode(word.charAt(1)));
			}
			else if (word.length() == 1) {
				myStartingLetters.put(word.charAt(0), new TrieNode());
			}
			if (word.length() == 0) {
				System.out.println("Please enter a valid word");
				return;
			}
			TrieNode iter = myStartingLetters.get(word.charAt(0));
			if (word.length() > 1) {
				word = word.substring(1);
				while (!iter.isLast()) {
					Character first_char = word.charAt(0);
					iter = iter.getTrieNode(first_char);
					try {
						word = word.substring(1);
					}
					catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
			}
		iter.setDefinition(definition);
		}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (word.length() == 0) {
			return null;
		}
		TrieNode toIter = myStartingLetters.get(word.charAt(0));
		if (word.length() > 1) {
			word = word.substring(1);
			while (!(toIter == null) && !toIter.isLast()) {
				toIter = toIter.getTrieNode(word.charAt(0));
				word = word.substring(1);
			}
		}
		if (toIter == null) {
			return null;
		}
		String defn = toIter.getDefinition();
		return defn;
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;
		private boolean last;

		private TrieNode() {
			myNextLetters = new HashMap<Character,TrieNode>();
			last = true;
		}
		
		private TrieNode(Character first) {
			myNextLetters = new HashMap<Character,TrieNode>();
			myNextLetters.put(first, new TrieNode());
		}
		
		private TrieNode(Character first, String rest) {
			myNextLetters = new HashMap<Character, TrieNode>();
			if (rest.length() == 1) {
				TrieNode trieRest = new TrieNode(rest.charAt(0));
				myNextLetters.put(first, trieRest);
			}
			else {
				TrieNode trieRest = new TrieNode(rest.charAt(0), rest.substring(1));
				myNextLetters.put(first, trieRest);
			}
		}
		
		public boolean isLast() {
			return last;
		}
		
		public TrieNode getTrieNode (Character c) {
			TrieNode toReturn = myNextLetters.get(c);
			return toReturn;
		}
		
		public String getDefinition() {
			return myDefinition;
		}
		
		public void setDefinition (String Definition) {
			if (last) {
				myDefinition = Definition;
			}
		}
		
	}
}