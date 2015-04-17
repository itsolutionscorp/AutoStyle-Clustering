package ngordnet;

import java.util.HashMap; // Package for Hash Map
import java.util.HashSet; // Package for Hash Set
import java.util.Set; // Package for Set
import edu.princeton.cs.introcs.In; // Package for reading files

public class WordNet {
    private HashMap<Integer, String[]> synMap; // Hash Map of number:word-set pairs of synsets
    private HashMap<Integer, Set<Integer>> hypMap; // Hash Map of number:number pairs of hyponyms
    private HashMap<String, Set<Integer>> nounMap; // Hash Map for noun:synset pairs

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synMap = new HashMap<Integer, String[]>();
        hypMap = new HashMap<Integer, Set<Integer>>();
        nounMap = new HashMap<String, Set<Integer>>();

        // Start by making synMap and nounMap
        In synIn = new In(synsetFilename); // In for synset file
        int synCount = 0;
        String curLine;
        String[] lineSplit;
        String[] curWords;
        Integer curID;
        Set<Integer> ids;
        while (synIn.hasNextLine()) {
            curLine = synIn.readLine(); // Read next line
            lineSplit = curLine.split(","); // Split on commas
            curID = Integer.parseInt(lineSplit[0]); // Current integer key
            curWords = lineSplit[1].split(" "); // Split on spaces
            synMap.put(curID, curWords); // Add id:words to synMap
            for (String word : curWords) {
                if (nounMap.containsKey(word)) {
                    nounMap.get(word).add(curID); // Add current id
                } else {
                    ids = new HashSet<Integer>();
                    ids.add(curID);
                    nounMap.put(word, ids); // Adds non-found noun to map, with synset id
                }
            }
            synCount += 1; // Counts number of synsets for making the graph
        }

        // Now, make hypMap (instead of using the DiGraph)
        In hypIn = new In(hyponymFilename); // In for hyponym file
        Integer curHyp;
        Set<Integer> curHypSet = new HashSet<Integer>();
        while (hypIn.hasNextLine()) {
            curLine = hypIn.readLine(); // Read next line
            lineSplit = curLine.split(","); // Split on commas
            curID = Integer.parseInt(lineSplit[0]); // Current synset id
            boolean idInHyp = hypMap.containsKey(curID);
            for (int i = 1; i < lineSplit.length; i++) {
                curHyp = Integer.parseInt(lineSplit[i]); // Current hyponym id
                if (idInHyp) {
                    hypMap.get(curID).add(curHyp);
                } else {
                    curHypSet = new HashSet<Integer>();
                    curHypSet.add(curHyp);
                    hypMap.put(curID, curHypSet);
                    idInHyp = true;
                }
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounMap.containsKey(noun); // True if "noun" is a key in NounSet
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounMap.keySet(); // A set of all keys of nounMap
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hypsSyns = new HashSet<String>(); // Set of hyponyms and synonyms
        Set<Integer> synIDs = nounMap.get(word); // Set of IDs for all synsets associated with word
        Set<Integer> hypIDs; // Set of IDs for all hyponyms of word
        String[] curSynWords; // Array of words associated with current ID
        String[] curHypWords; // Array of words associated with current synset
        for (Integer synID : synIDs) {
            curSynWords = synMap.get(synID); // Array of words in current synset
            for (String synWord : curSynWords) {
                hypsSyns.add(synWord); // Add members of current synset to return set
            }
            hypIDs = hyponymHelper(synID); // IDs of hyponyms of current synset
            if (hypIDs != null) {
                for (Integer hypID : hypIDs) {
                    curHypWords = synMap.get(hypID); // Hyponyms of current synset
                    for (String hypWord : curHypWords) {
                        hypsSyns.add(hypWord); // Add hyponyms of current synset
                    }
                }
            }
        }
        return hypsSyns;
    }

    // Helper function for hyponyms that gets all the decendents of the given synset
    private Set<Integer> hyponymHelper(Integer synID) {
        Set<Integer> children = hypMap.get(synID);
        HashSet<Integer> out = new HashSet<Integer>();
        out.add(synID);
        if (children == null) {
            return out;
        }
        for (Integer child : children) {
            for (Integer id : hyponymHelper(child)) {
                out.add(id);
            }
        }
        return out;
    }

}
