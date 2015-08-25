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
		if(word.isEmpty())
			return;
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			myStartingLetters.put(word.charAt(0), new TrieNode(null));
		}
		myStartingLetters.get(word.charAt(0)).add(word.substring(1), definition);
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if(word.isEmpty())
			return null;
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			return null;
		}
		return myStartingLetters.get(word.charAt(0)).get(word.substring(1));
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode(String definition) {
			myNextLetters = new HashMap<Character, TrieNode>();
			myDefinition = definition;
		}

		public void add(String s, String definition) {
			if (s.isEmpty()) {
				return;
			} else if (s.length() == 1) {
				myNextLetters.put(s.charAt(0), new TrieNode(definition));
			} else {
				if (!myNextLetters.containsKey(s.charAt(0))) {
					myNextLetters.put(s.charAt(0), new TrieNode(null));
				}
				myNextLetters.get(s.charAt(0)).add(s.substring(1), definition);
			}
		}
		
		public String get(String s) {
			if (s.length() == 1) {
				if(myNextLetters.containsKey(s.charAt(0)))
					return myNextLetters.get(s.charAt(0)).myDefinition;
				else
					return null;
			} else {
				if (!myNextLetters.containsKey(s.charAt(0))) {
					return null;
				}
				return myNextLetters.get(s.charAt(0)).get(s.substring(1));
			}
		}
	}
	
	public static void main(String[] args) {
		Dictionary dic = new Dictionary() ;
		dic.addDefinition("cat","animal");
		dic.addDefinition("can","tin");
		dic.addDefinition("dog","other animal");
		dic.addDefinition("bark", "shit dogs do");
		System.out.println(dic.lookupDefinition("bark"));
		System.out.println(dic.lookupDefinition("nah"));
		System.out.println(dic.lookupDefinition("cat"));
		System.out.println(dic.lookupDefinition("can"));
		System.out.println(dic.lookupDefinition("cane"));
	}
}