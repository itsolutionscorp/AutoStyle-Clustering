package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private HashMap<Integer, String> storeSynset;
    private Digraph dI;
    private int V;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        
        In inSynset = new In(synsetFilename);
        In inHyponym = new In(hyponymFilename);
        
        storeSynset = new HashMap<Integer, String>();
        
        while (!inSynset.isEmpty()) {
            String line = inSynset.readLine();
            V += 1;
            String[] rawTokens = line.split(",");
           
            storeSynset.put(Integer.parseInt(rawTokens[0]), rawTokens[1]);
        }

        dI = new Digraph(V);
        
        while (!inHyponym.isEmpty()) {
            String newLine = inHyponym.readLine();
            String[] hypoRawTokens = newLine.split(",");
            int synsetID = Integer.parseInt(hypoRawTokens[0]);
            Integer[] hyponymArray = new Integer[hypoRawTokens.length - 1];
            
            for (int i = 1, j = 0; j < hyponymArray.length; i++, j++) {
                hyponymArray[j] = Integer.parseInt(hypoRawTokens[i]);
            }
            
            for (int hyponym : hyponymArray) {
                dI.addEdge(synsetID, hyponym);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int key : storeSynset.keySet()) {
            String[] wordsSpace = storeSynset.get(key).split(" ");
            for (String word : wordsSpace) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (int key : storeSynset.keySet()) {
            String[] wordsSpace = storeSynset.get(key).split(" ");
            if (wordsSpace.length > 0) {
                for (String word : wordsSpace) {
                    nounSet.add(word);
                    
                }
            } else {
                nounSet.add(storeSynset.get(key)); 
            }
        }
        return nounSet;      
    }
    

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        
        Set<Integer> storeNums = new HashSet<Integer>();
        
        for (int key : storeSynset.keySet()) {
            String[] storeArray = storeSynset.get(key).split(" ");
            for (String item : storeArray) {
                if (word.equals(item)) {
                    storeNums.add(key);
                }
            }
        }    

        Set<Integer> idSet = GraphHelper.descendants(dI, storeNums);
        
        Set<String> hyponymSet = new HashSet<String>(); 
        
        for (int nums : idSet) {
            for (int key2 : storeSynset.keySet()) {
                String[] storeArray2 = storeSynset.get(key2).split(" ");
                for (String item2 : storeArray2) {
                    if (nums == key2) {
                        hyponymSet.add(item2);
                    }
                }
            }
        }
        return hyponymSet;
    }    
}
