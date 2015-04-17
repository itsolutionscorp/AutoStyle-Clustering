package ngordnet;

// import ngordnet.Synset;
// import ngordnet.SynsetList;
// import ngordnet.Graph;
//import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    private SynsetList synsets;
    private Graph wholePic;
    private Set<String> dicts;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new SynsetList(synsetFilename);
        wholePic = new Graph(hyponymFilename, synsets.getLen());
        dicts = synsets.tran2Set();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsets.hasSymbol(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return dicts;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> synIds = synsets.allSynsetHasWord(word);
        Set<Integer> hypoIds = wholePic.allHypo(synIds);

        Set<String> ret = new HashSet<String>();
        for (int i : synIds) {
            ret.addAll(synsets.idGet(i).wordSet());
        }

        for (int i : hypoIds) {
            ret.addAll(synsets.idGet(i).wordSet());
        }
        return ret;
    }
}
