package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private Map<Integer, String> identityToWord;
    private Map<String, Set<Integer>> wordToIdentity;
    private Set<String> allNouns;
    private Digraph hyponymsDigraph;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
    */
    public WordNet(String synsetFilename, String hyponymFilename) {
        identityToWord = new HashMap<Integer, String>();

        Map<Integer, Set<Integer>> wordIDToHyponymsID = new HashMap<Integer, Set<Integer>>();
        // set wordIDToHyponymsID
        int totalSynsets = readSynsetFile(synsetFilename);
        // set wordID
        readHyponymFile(hyponymFilename, wordIDToHyponymsID);
        // set hyponymsDigraph;
        hyponymsDigraph = new Digraph(totalSynsets);
        for (Map.Entry<Integer, Set<Integer>> entry : wordIDToHyponymsID.entrySet()) {
            // The key will be the origin vertex of the edge
            int hypernimID = entry.getKey();
            for (Integer id : entry.getValue()) {
                // Each value of the set becomes the destination vertex of the edge
                hyponymsDigraph.addEdge(hypernimID, id);
                //System.out.println("From " + hypernimID + " to " + id);
            }
        }
        // Populate allNouns
        populateAllNouns();
        // Initialize wordToIdentity
        initializeWordToIdentity();
        // Using identityToWord, make the inverse wordToIdentity
        for (Map.Entry<Integer, String> entry : identityToWord.entrySet()) {
            int identity = entry.getKey();
            String[] words = entry.getValue().split(" ");
            for (int i = 0; i < words.length; i += 1) {
                wordToIdentity.get(words[i]).add(identity);
            }
        }
    }

    /**Returns the set of all hypnoyms of WORD as well as all synonyms of WORD
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> identity = wordToIdentity.get(word);
        if (identity == null) {
            return null;
        }
        Set<Integer> graphID = GraphHelper.descendants(hyponymsDigraph, identity);
        Set<String> hyponymsAndSynonyms = new HashSet<String>();
        for (Integer id : graphID) {
            String[] words = identityToWord.get(id).split(" ");
            for (int i = 0; i < words.length; i += 1) {
                hyponymsAndSynonyms.add(words[i]);
            }
        }
        return hyponymsAndSynonyms;
    }

    public boolean isNoun(String noun) {
        if (allNouns.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> temp = new HashSet<String>(allNouns);
        return temp;
    }

    // ---------------------------Private Helper Methods for Constructor----------------------
    /**  Take a synset file as an input and read it.
      *  Input : Synset file to read
      *  Function : Read the file into identityToWord (Map)
      *  Return : the number of synsets in the input file
    */
    private int readSynsetFile(String synsetFilename) {
        //first read the SynsetFilename
        int totalSynsets = 0;
        In synsetReader = new In(synsetFilename);
        while (synsetReader.hasNextLine()) {
            // split the inputString into 3 [ID, synsets, meaning]
            String[] stringInputs = synsetReader.readLine().split(",");
            int synsetID = Integer.parseInt(stringInputs[0]);
            // synsets can be more than one words, therefore we split it again
            String synsets = stringInputs[1];
            //Populate the wordMeaningPair and idWordPair
            identityToWord.put(synsetID, synsets);
            String[] wordsInSynsets = synsets.split(" ");
            totalSynsets += 1;
        }
        return totalSynsets;
    }

    private void readHyponymFile(String hyponymFilename, Map<Integer, Set<Integer>> 
        wordIDToHyponymsID) {
        // Second, read the hyponymFilename
        In hyponymReader = new In(hyponymFilename);
        // Iterate through the files
        while (hyponymReader.hasNextLine()) {
            // Initialize the hyponyms set
            Set<Integer> hyponymsID = new HashSet<Integer>();
            String[] stringInputs = hyponymReader.readLine().split(",");
            // Hyponyms is from the second element forth so iterate and add to hyponyms set
            for (int i = 1; i < stringInputs.length; i += 1) {
                hyponymsID.add(Integer.parseInt(stringInputs[i]));
            }
            // First element on the line is the hypernim, keep it as the value of map
            int wordID = Integer.parseInt(stringInputs[0]);
            // If it hasn't been put yet, then put a new value
            if (wordIDToHyponymsID.get(wordID) == null) {
                wordIDToHyponymsID.put(wordID, hyponymsID);
            } else {
                // Else we just combine the already existing set of integers with the new One
                wordIDToHyponymsID.get(wordID).addAll(hyponymsID);
            }
        }
    }

    private void initializeWordToIdentity() {
        wordToIdentity = new HashMap<String, Set<Integer>>();
        for (String n : allNouns) {
            wordToIdentity.put(n, new HashSet<Integer>());
        }
    }

    private void populateAllNouns() {
        // Initialize all Nouns, which keep track of all the words in the given synsets
        allNouns = new HashSet<String>();
        for (Map.Entry<Integer, String> entry : identityToWord.entrySet()) {
            String[] words = entry.getValue().split(" ");
            for (int i = 0; i < words.length; i += 1) {
                allNouns.add(words[i]);
            }
        }
    }   
}
