package ngordnet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
 * Things that might come in handy:
 * @In Methods
 *  public boolean isEmpty(); -- to check if its empty
 *  public boolean hasNextLine() -- For the while loop condition
 *  public boolean hasNextChar() -- To check for commas?
 *  public String readLine() -- Read the current line
 *  public int readInt() -- For reading the synset IDs?
 *  public String readString()  --Reads and returns next string
 */


/* An object that stores the WordNet graph in a manner useful for 
 * extracting all hyponyms of a word.*/
public class WordNet {
    private HashMap<String, ArrayList<Integer>> nounsMap = 
        new HashMap<String, ArrayList<Integer>>();
    private HashMap<Integer, ArrayList<String>> hypoMap = 
        new HashMap<Integer, ArrayList<String>>();
    private Digraph hypograph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        if (!synsetFilename.isEmpty() && !hyponymFilename.isEmpty()) { //can also use null?
            In synsetF = new In(synsetFilename);
            In hyponymF = new In(hyponymFilename);
            int vCount = 0;
            int eCount = 0;
            
            while (synsetF.hasNextLine()) {
                String sLines = synsetF.readLine();               
                String[] sTokens = sLines.split(",");  
                String[] synsetArr = sTokens[1].split(" ");     
                Integer synsetID = Integer.parseInt(sTokens[0]);
                
                for (int i = 0; i < synsetArr.length; i += 1) {
                    if (!hypoMap.containsKey(synsetID)) {
                        ArrayList<String> wordsArray = new ArrayList<String>();
                        wordsArray.add(synsetArr[i]);
                        hypoMap.put(synsetID, wordsArray);
                    }
                    
                    if (hypoMap.containsKey(synsetID)) {
                        if (!hypoMap.get(synsetID).contains(synsetArr[i])) {
                            hypoMap.get(synsetID).add(synsetArr[i]);
                        }
                    }
                    
                    if (!nounsMap.containsKey(synsetArr[i])) {
                        ArrayList<Integer> synsetIDArray = new ArrayList<Integer>();
                        synsetIDArray.add(synsetID);
                        nounsMap.put(synsetArr[i], synsetIDArray);
                    } else { 
                        nounsMap.get(synsetArr[i]).add(synsetID);
                    }
                }    
            }
            
            hypograph = new Digraph(nounsMap.size());
            
            while (hyponymF.hasNextLine()) {
                String hLines = hyponymF.readLine(); 
                String[] hyponymArr = hLines.split(",");   
                int[] intArray = new int[hyponymArr.length]; 
                for (int i = 0; i < hyponymArr.length; i++) {
                    intArray[i] = Integer.parseInt(hyponymArr[i]);
                }
                int hSynsetID = intArray[0];
                for (int i = 1; i < intArray.length; i += 1) {
                    hypograph.addEdge(hSynsetID, intArray[i]);
                }
            }
        } else {
            throw new NullPointerException("One of the files is empty!");
        }
    } 
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounsMap.containsKey(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounsMap.keySet();
    }
 
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD 
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> hyponymsAndSynonyms = new HashSet<String>();
        HashSet<Integer> valueIDs = new HashSet<Integer>();
        Set<Integer> graphResult;

        for (Integer sID : nounsMap.get(word)) {
            valueIDs.add(sID);
        }

        graphResult = GraphHelper.descendants(hypograph, valueIDs);

        for (Integer hID : graphResult) {
            for (int i = 0; i < hypoMap.get(hID).size(); i += 1) {
                hyponymsAndSynonyms.add(hypoMap.get(hID).get(i));
            }
        }
        return hyponymsAndSynonyms;
    }
}
