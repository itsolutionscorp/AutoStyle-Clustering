package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class WordNet {
    private HashMap<Integer, ArrayList<String>> idToWords;
    private HashMap<String, Set<Integer>> wordToIDs;
    private Digraph hyperToHypos;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);
        String[] currLn;
        String[] hypList;
        idToWords = new HashMap<Integer, ArrayList<String>>();
        wordToIDs =  new HashMap<String, Set<Integer>>();
        ArrayList<String> currWords;
        Integer currentID;

        //Read the file of synsets and construct the idToWords and wordToIDs HashMaps
        while (synFile.hasNextLine()) {
            currLn = synFile.readLine().split(",");
            currWords = new ArrayList<String>(Arrays.asList(currLn[1].split(" ")));
            currentID = Integer.parseInt(currLn[0]);
            idToWords.put(currentID, currWords);
            for (String word : currWords) {
                if (!wordToIDs.containsKey(word)) {
                    Set<Integer> temp = new HashSet<Integer>();
                    temp.add(currentID);
                    wordToIDs.put(word, temp);
                } else {
                    wordToIDs.get(word).add(currentID);
                }
            }
        }
        
        hyperToHypos = new Digraph(idToWords.size());

        //Read the file of hyponym ids
        while (hypFile.hasNextLine()) {
            currLn = hypFile.readLine().split(",");
            ArrayList<Integer> hyponymIDs = new ArrayList<Integer>(currLn.length - 1);
            for (int i = 1; i < currLn.length; i++) {
                hyperToHypos.addEdge(Integer.parseInt(currLn[0]), Integer.parseInt(currLn[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToIDs.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToIDs.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    //Take list of hyperIDs using word. Then, add all of it's synonyms into the set. 
    //Then add all it's hyponyms and remove the original word
    public Set<String> hyponyms(String word) { 
        Set<String> hypAndSyn = new HashSet<String>();
        for (Integer hyper : GraphHelper.descendants(hyperToHypos, wordToIDs.get(word))) {
            hypAndSyn.addAll(idToWords.get(hyper));
        }
        return hypAndSyn;
    }

}
