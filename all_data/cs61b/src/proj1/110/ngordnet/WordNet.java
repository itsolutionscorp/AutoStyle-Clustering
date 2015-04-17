package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

public class WordNet {

    /** Map from noun strings to a Set of all synset ids containing the strings */
    private HashMap<String, Set<Integer>> nounIDs;

    /** Map from ID numbers to Synsets */
    private HashMap<Integer, Synset> synsets;

    /** Digraph of synset ID's for hyponym - hypernym relationships */
    private Digraph relationships;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nounIDs = new HashMap<String, Set<Integer>>();
        synsets = new HashMap<Integer, Synset>();
        In synsetReader = new In(synsetFilename);
        In hyponymReader = new In(hyponymFilename);
        while (synsetReader.hasNextLine()) {
            String[] parsed = synsetReader.readLine().split(",");
            int thisID = Integer.parseInt(parsed[0]);
            String[] thisSynonyms = parsed[1].split(" ");
            Synset thisSynset = new Synset(thisID, thisSynonyms, parsed[2]);
            synsets.put(thisSynset._id, thisSynset);
            putNounsInMap(thisSynset, nounIDs);
        }
        relationships = new Digraph(synsets.size());
        while (hyponymReader.hasNextLine()) {
            String[] parsed = hyponymReader.readLine().split(",");
            int thisID = Integer.parseInt(parsed[0]);
            for (int i = 1; i < parsed.length; i++) {
                relationships.addEdge(thisID, Integer.parseInt(parsed[i]));
            }
        }
    }

    /** Helper method to put the nouns of a given synset into a map */
    private static void putNounsInMap(Synset syn, HashMap<String, Set<Integer>> map) {
        for (String s : syn._synonyms) {
            if (!map.containsKey(s)) {
                Set<Integer> ids = new TreeSet<Integer>();
                ids.add(syn._id);
                map.put(s, ids);
            } else {
                map.get(s).add(syn._id);
            }
        }
    }

    /** Nested Synset class stores the ID, synonyms, and definition */
    private class Synset {

        /** ID */
        private int _id;
        /** synonyms */
        private Set<String> _synonyms;
        /** definition */
        private String _definition;

        public Synset(int id, String[] synonyms, String definition) {
            _synonyms = new TreeSet<String>();
            for (String s : synonyms) {
                _synonyms.add(s);
            }
            _id = id;
            _definition = definition;
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounIDs.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounIDs.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return null;
        }
        Set<Integer> ids = GraphHelper.descendants(relationships, nounIDs.get(word));
        Set<String> allHyponyms = new TreeSet<String>();
        for (Integer id : ids) {
            for (String s : synsets.get(id)._synonyms) {
                allHyponyms.add(s);
            }
        }
        return allHyponyms;
    }
}
