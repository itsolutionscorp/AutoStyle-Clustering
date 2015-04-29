package ngordnet;
  
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
    
public class WordNet {
    /** Map of Synset ID -> Synset. */
    private HashMap<String, String> idToSynset = new HashMap<>();
    /** Map of Noun -> ArrayList of Synset IDs of all synsets in which Noun is contained. */
    private HashMap<String, ArrayList<String>> nounToIDs = new HashMap<>();
    /** Map of Synset ID -> List of direct hyponym synset IDs for corresponding synset. */
    private HashMap<String, ArrayList<String>> idToHyponyms = new HashMap<>();
    /** Digraph of synsets */
    private Digraph digraph;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Create the maps idToSynset, nounToIDs.
        createMapsOfSynsetInfo(synsetFilename);
        
        // Create the map idToHyponyms.
        createMapIDtoHyponyms(hyponymFilename);

        // Instantiate digraph with number of vertices equal to the number of synsets provided.
        digraph = new Digraph(idToSynset.size());

        /** Add edges to digraph.
          * 1) Grab a synset ID (i.e. grab a synset).
          * 2) Check that the synset if a hypernym. 
          *    If yes, continue. Otherwise, go to step 1.
          * 3) Get IDs of synset's hyponyms.
          * 3) Draw edges from synset to each of its hyponyms.
          * 5) Repeat until synsets are exhausted.
          */
        ArrayList<String> hyponymIDs = new ArrayList<>();
        for (String synsetID : idToSynset.keySet()) {
            hyponymIDs = idToHyponyms.get(synsetID);

            if (hyponymIDs == null) {
                continue;
            }

            for (String id : hyponymIDs) {
                digraph.addEdge(Integer.parseInt(synsetID), Integer.parseInt(id));
            }

        }
    }
       
    /** Create maps idToSynset, and nounToIDs. */
    private void createMapsOfSynsetInfo(String synsetFilename) {
        In synsetTxt = new In(synsetFilename);
        ArrayList<String> synsetInfo = new ArrayList<>();
        ArrayList<String> wordsInSynset = new ArrayList<>();
        Pattern comma = Pattern.compile("[,]");
        Pattern space = Pattern.compile("[ ]");
        while (synsetTxt.hasNextLine()) {
            // Grab synset ID, synset, and definition.
            synsetInfo = new ArrayList<String>(Arrays.asList(comma.split(synsetTxt.readLine())));
            // Remove definition.
            synsetInfo.remove(2);
            // Grab synset ID.
            String synsetID = synsetInfo.get(0);
            // Grab synomyn set (a list of words).
            String synset = synsetInfo.get(1);
            // Add synset ID and synset pairings to map idToSynset.
            idToSynset.put(synsetID, synset);
            // Convert synset from one long String of words, to array of individual words.
            wordsInSynset = new ArrayList<String>(Arrays.asList(space.split(synset)));

            for (String word : wordsInSynset) {
                ArrayList<String> idsForWord = nounToIDs.get(word);
                if (idsForWord != null) {
                    idsForWord.add(synsetID);
                } else {
                    idsForWord = new ArrayList<String>();
                    idsForWord.add(synsetID);
                }
                nounToIDs.put(word, idsForWord);
            }
        }
    } 
    
    /** Create idToHyponyms map. */
    private void createMapIDtoHyponyms(String hyponymFilename) {
        In hyponymTxt = new In(hyponymFilename);
        ArrayList<String> ids = new ArrayList<>();
        Pattern comma = Pattern.compile("[,]");
        while (hyponymTxt.hasNextLine()) {
            // Grab list of ids (synset index 0, hyponym other).
            ids = new ArrayList<String>(Arrays.asList(comma.split(hyponymTxt.readLine())));
            String synsetID = ids.get(0);
            ids.remove(0);
            if (idToHyponyms.containsKey(synsetID)) {
                ids.addAll(idToHyponyms.get(synsetID));
            }
            idToHyponyms.put(synsetID, ids);
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounToIDs.keySet().contains(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounToIDs.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        ArrayList<String> synsetIdsString = nounToIDs.get(word);
        if (synsetIdsString == null) {
            return new HashSet<String>();
        }

        Set<Integer> synsetIDs = new HashSet<>();
        for (String id : synsetIdsString) {
            synsetIDs.add(Integer.parseInt(id));
        }
        Set<Integer> hyponymIDs = GraphHelper.descendants(digraph, synsetIDs);
        ArrayList<String> hyponyms = new ArrayList<>();
        Pattern space = Pattern.compile("[ ]");
        for (Integer id : hyponymIDs) {
            String hyp = idToSynset.get(String.valueOf(id));
            hyponyms.addAll(Arrays.asList(space.split(hyp)));
        }
        return new HashSet<String>(hyponyms);
    }

}
