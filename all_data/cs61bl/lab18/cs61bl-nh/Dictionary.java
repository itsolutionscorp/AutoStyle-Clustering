import java.util.HashMap;
 
public class Dictionary {
 
    /*
     * Since a trie node can have so many children, the children it has are
     * stored in a map.
     */
    private HashMap<Character, TrieNode> myStartingLetters;
    private TrieNode myRoot;
 
    public Dictionary() {
        myStartingLetters = new HashMap<>();
        myRoot = new TrieNode();
    }
 
    /**
     * Associates the input word with the input definition in the dictionary.
     */
    public void addDefinition(String word, String definition) {
        // TODO your code here!
        if(word.length() > 0){
            if(!myStartingLetters.containsKey(word.charAt(0)))
                myStartingLetters.put(word.charAt(0), myRoot.addDefinition(word.substring(1),definition));
            else
                myRoot.addDefinition(word.substring(1), definition);
             
        }
        /*char first = word.charAt(0); //Obtains first character
        int index = 1;
        char [] myLetters = new char [word.length() - 1];
        while(index < word.length()){
            myLetters[index] = word.charAt(index);
            index++;
        }*/
    }
 
    /**
     * Return the definition associated with this word in the Dictionary. Return
     * null if there is no definition for the word.
     */
    public String lookupDefinition(String word) {
        // TODO your code here!
        if(word.length() > 0){
            TrieNode temp = myStartingLetters.get(word.charAt(0));
            return temp.lookupDefinition(word.substring(1));
        }return null;
             
    }
 
    private class TrieNode {
        private HashMap<Character, TrieNode> myNextLetters;
        private TrieNode myNext;
        // Leave this null if this TrieNode is not the end of a complete word.
        private String myDefinition;
 
        private TrieNode() {
            myNextLetters = new HashMap<>();
        }
         
        public TrieNode addDefinition(String word, String definition){
            if(word.length() > 0){
                if(!myNextLetters.containsKey(word.charAt(0))){
                    myNext = new TrieNode();
                    myNextLetters.put(word.charAt(0), myNext.addDefinition(word.substring(1), definition));
                }else{
                    myNext.addDefinition(word.substring(1), definition);
                }
            }else{
                myNext = null;
                myDefinition = definition;
            }return this;
        }
         
        public String lookupDefinition(String word){
            if(word.length() > 0){
                TrieNode temp = myNextLetters.get(word.charAt(0));
                return temp.lookupDefinition(word.substring(1));
            }return myDefinition;
        }
    }
}