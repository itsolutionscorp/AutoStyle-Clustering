import java.util.HashMap;


	public class TrieNode {
		public HashMap<Character, TrieNode> myNextLetters;

		// Leave this null if this TrieNode is not the end of a complete word.
		public String myDefinition;

		public TrieNode() {
			myNextLetters = new HashMap<>();
			myDefinition = null;
		}
		
		public TrieNode(String d){
			myDefinition = d;
		}
		
		public TrieNode setDef(String d){
			myDefinition = d;
			return this;
		}
		
		public void addHelper(String word, String definition){
			System.out.println(word);
			if (word.length() == 1) {
				System.out.println("hey");
				myNextLetters.put(word.charAt(0), new TrieNode(definition));
				return;
			}
			myNextLetters.put(word.charAt(0), new TrieNode());
			myNextLetters.get(word.charAt(0)).addHelper(word.substring(1), definition);
		}
		
		public String findHelper(String word){
			if (word.equals("")) return myDefinition;
			return myNextLetters.get(word.charAt(0)).findHelper(word.substring(1));
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
