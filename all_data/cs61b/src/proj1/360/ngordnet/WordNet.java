package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    /** contains hyponym relations **/
    private Digraph wn;

    /** contains id: synset map **/
    private HashMap<Integer, String> synMap;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetsIn = new In(synsetFilename);
        In hyponymsIn = new In(hyponymFilename);

        int id = 0; String synset = "";
        String line; String[] rawTokens;
        String[] hypoTokens;

      /** create map from id to synset **/
        synMap = new HashMap<Integer, String>();
        while (synsetsIn.hasNextLine()) {
            line = synsetsIn.readLine();
            rawTokens = line.split(",");
            id = Integer.parseInt(rawTokens[0]);
            synset = rawTokens[1];
            synMap.put(id, synset);
        }

      /** create digraph connecting id of synset to id's of its hyponyms **/
        wn = new Digraph(id + 1);
        while (hyponymsIn.hasNextLine()) {
            line = hyponymsIn.readLine();
            rawTokens = line.split(",");
            id = Integer.parseInt(rawTokens[0]);
            hypoTokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, hypoTokens, 0, rawTokens.length - 1);
            for (String hypoString : hypoTokens) {
                wn.addEdge(id, Integer.parseInt(hypoString));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> result = new HashSet<String>();
        for (String s : synMap.values()) {
            for (String noun : s.split(" ")) {
                result.add(noun);
            }
        }
        return result;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
      /* contains ID's of synsets containing word */
        HashSet<Integer> synID = new HashSet<Integer>();
        for (Integer id : synMap.keySet()) {
            for (String w : synMap.get(id).split(" "))  {
                if (word.equals(w)) {
                    synID.add(id);
                }                
            }   
        }

        /* contains ID's of hyponym synsets */
        Set<Integer> hypoID = GraphHelper.descendants(wn, synID);

        /* finds synset corresponding to hypoID, splits it, adds each word to result */
        HashSet<String> result = new HashSet<String>();
        String hypoSyn;
        for (Integer id : hypoID) {
            for (String w : synMap.get(id).split(" ")) {
                result.add(w);
            }
        }
        return result;

    }
}
