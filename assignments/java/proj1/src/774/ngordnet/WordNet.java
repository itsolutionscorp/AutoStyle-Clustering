package ngordnet;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet {

    // Connecting hyponyms by Synset ID
    private Digraph allHyps;
    // Synset ID followed by set of corresponding synonyms with that ID
    private HashMap<Integer, HashSet<String>> allSynsets;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In sReader = new In(synsetFilename);
        In hReader = new In(hyponymFilename);
        allSynsets = new HashMap<Integer, HashSet<String>>();
        
        while (sReader.hasNextLine()) {
            String[] setWords = sReader.readLine().split(",");
            // Create set for each group of synonyms
            String[] synonyms = setWords[1].split(" ");
            HashSet<String> syns = new HashSet<String>();
            for (String sy : synonyms) {
                syns.add(sy);
            }
            /* setWords[0] is the synset ID, syns from setWords[1]
               is the list of synonyms associated with that ID */
            allSynsets.put(Integer.valueOf(setWords[0]), syns);
        }

        allHyps = new Digraph(allSynsets.size());
        while (hReader.hasNextLine()) {
            String[] setAsStrings = hReader.readLine().split(",");
            Integer synsID = Integer.valueOf(setAsStrings[0]);
            setAsStrings[0] = "";
            // Set of all Synset IDs of synsID's hyponyms, all but
            // the first entry in the line 
            for (String s : setAsStrings) {
                if (!s.equals("")) {
                    allHyps.addEdge(synsID, Integer.valueOf(s));
                }
            }
        } 
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (HashSet<String> s : allSynsets.values()) {
            if (s.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    // Flatten Hashsets that are values of allSynsets
    public Set<String> nouns() {
        HashSet<String> res = new HashSet<String>();
        for (HashSet<String> s : allSynsets.values()) {
            for (String se : s) {
                res.add(se);
            }
        }
        return res;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> wordIDs = new HashSet<Integer>();
        // Find the Synset IDs of word in the WordNet
        for (Integer i : allSynsets.keySet()) {
            if (allSynsets.get(i).contains(word)) {
                wordIDs.add(i);
            }
        }
        Set<Integer> hypIDs = GraphHelper.descendants(allHyps, wordIDs);
        HashSet<String> hyponyms = new HashSet<String>();
        // Add the words corresponding to the hypIDs found using descendants
        for (Integer id : hypIDs) {
            for (String s : allSynsets.get(id)) {
                hyponyms.add(s);
            }
        }
        return hyponyms;
    }
}
