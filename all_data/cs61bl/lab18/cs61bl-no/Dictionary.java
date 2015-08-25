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
		
		if(!myStartingLetters.containsKey(word.charAt(0))){
			myStartingLetters.put(word.charAt(0), new TrieNode() );
		}

		TrieNode temp = myStartingLetters.get(word.charAt(0));

		for (int i = 1; i < word.length(); i++) {
			if (temp.myNextLetters.containsKey(word.charAt(i))) {
				temp = temp.myNextLetters.get(word.charAt(i));
			} else {
				temp.myNextLetters.put(word.charAt(i), new TrieNode());
				temp = temp.myNextLetters.get(word.charAt(i));
			}
		}

		temp.myDefinition = definition;
		return;
		
		
		
		
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if(!myStartingLetters.containsKey(word.charAt(0))){
			return "There's no such word in the dictionary.";
		}
		
		TrieNode temp = myStartingLetters.get(word.charAt(0));
		
		for (int i = 1; i < word.length(); i++) {
			if(temp.myNextLetters.get(word.charAt(i)) == null){
				return "There's no such word in the dictionary.";
			}
			temp = temp.myNextLetters.get(word.charAt(i));
		}
		
		if(temp.myDefinition == null){
			return "There's no such word in the dictionary.";
		}
		
		return temp.myDefinition;
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
		
		Dictionary a = new Dictionary();
		a.addDefinition("water", "A colorless, transparent, odorless, tasteless liquid that forms the seas, lakes, rivers, and rain and is the basis of the fluids of living organisms.");
		a.addDefinition("waterbottle", "A bottle that contains water");
		a.addDefinition("sh*t", "something good I guess");
		a.addDefinition("cs61a", "A fun class");
		a.addDefinition("cs61b", "Anoter fun class");
		a.addDefinition("cs61bl-summer", "A frustrating class");
		
		System.out.println(a.lookupDefinition("sh*t"));
		System.out.println(a.lookupDefinition("water"));
		System.out.println(a.lookupDefinition("wat"));
		System.out.println(a.lookupDefinition("watttt"));
		System.out.println(a.lookupDefinition("cs61a"));
		System.out.println(a.lookupDefinition("cs61b"));
		System.out.println(a.lookupDefinition("cs61bl"));
		System.out.println(a.lookupDefinition("cs61bl-summer"));
		
		
	}
	
}