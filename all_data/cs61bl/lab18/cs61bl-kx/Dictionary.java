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
		if(!myStartingLetters.containsKey(word.charAt(0))){
			TrieNode n = new TrieNode();
			myStartingLetters.put(word.charAt(0), n);
			for(int i=1;i<word.length();i++){
				//System.out.println(word.charAt(i));
				TrieNode next = new TrieNode();
				n.addKeyVal(word.charAt(i), next);
				n = next;
			}
			n.setDef(definition);
		}
		else{
			TrieNode first = myStartingLetters.get(word.charAt(0));
			for(int i=1;i<word.length();i++){
				if(first.getLetters().containsKey(word.charAt(i))){
					first = first.getLetters().get(word.charAt(i));
				}
				else{
					//System.out.println("went in here");
					TrieNode n = new TrieNode();
					first.getLetters().put(word.charAt(i), n);
					first = n;
				}
			}
			first.setDef(definition);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		// TODO your code here!
		TrieNode n = myStartingLetters.get(word.charAt(0));
		for(int i=1;i<word.length();i++){
			//System.out.println(word.charAt(i));
			n = n.getLetters().get(word.charAt(i));
		}
		return n.getDef();
	}

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
		
		public void addKeyVal(Character c, TrieNode t){
			myNextLetters.put(c,  t);
		}
		
		public void setDef(String def){
			myDefinition = def;
		}
		
		public HashMap<Character, TrieNode> getLetters(){
			return myNextLetters;
		}
		
		public String getDef(){
			return myDefinition;
		}
	}
	
	/*public static void main(String args[]){
		Dictionary d = new Dictionary();
		d.addDefinition("cat", "louis");
		d.addDefinition("cam", "cameo");
		d.addDefinition("love", "kim");
		d.addDefinition("cop", "butality");
		d.addDefinition("cops", "more butality");
		d.addDefinition("bball", "win");
		System.out.println(d.lookupDefinition("cops"));
	}*/
}