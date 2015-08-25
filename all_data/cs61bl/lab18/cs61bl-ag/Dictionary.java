import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a tree node can have so many children, the children it has are
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
		char[] letters = word.toCharArray();
		HashMap<Character, TrieNode> currentLetters = myStartingLetters;
		int q = 0;
		while(true){
			if(currentLetters.get(letters[q]) == null) {
				currentLetters.put(new Character(letters[q]), new TrieNode());
				if(q == letters.length-1){
					currentLetters.get(letters[q]).myDefinition = definition;
					return;
				}
			}
			else {
				currentLetters = currentLetters.get(letters[q]).myNextLetters;
				q++;
			}
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		char[] letters = word.toCharArray();
		HashMap<Character, TrieNode> currentLetters = myStartingLetters;
		for(int q = 0; q<letters.length; q++){
			if(currentLetters.get(letters[q])==null){
				return null;
			}
			if(q==letters.length-1){
				return currentLetters.get(letters[q]).myDefinition;
			}
			else{
				currentLetters = currentLetters.get(letters[q]).myNextLetters;
			}
		}
		return null;
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
		Dictionary MataNui = new Dictionary();
		MataNui.addDefinition("Tahu", "Toa of Fire");
		MataNui.addDefinition("Lewa", "Toa of Air");
		MataNui.addDefinition("Gali", "Toa of Water");
		MataNui.addDefinition("Kopaka", "Toa of Ice");
		MataNui.addDefinition("Onua", "Toa of Earth");
		MataNui.addDefinition("Pohatu", "Toa of Stone");
		MataNui.addDefinition("Potato", "Wannabe Toa of Food");
		MataNui.addDefinition("TahU", "Tahu\'s doppleganger");
		MataNui.addDefinition("Takanuva", "Toa of Light");
		System.out.println(MataNui.lookupDefinition("Tahu"));
		System.out.println(MataNui.lookupDefinition("TahU"));
		System.out.println(MataNui.lookupDefinition("Vakama"));
		System.out.println(MataNui.lookupDefinition("Potato"));
		System.out.println(MataNui.lookupDefinition("Takanuva"));
	}
}