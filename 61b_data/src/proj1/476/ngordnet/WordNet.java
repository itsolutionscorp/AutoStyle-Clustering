package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class WordNet {

    private Map<Integer, String> synmap;
    private Digraph hypograph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In inSyn = new In(synsetFilename);
        In inHypo = new In(hyponymFilename);
        int length = 0;
        synmap = new HashMap<Integer, String>();
        while (inSyn.hasNextLine()) {
            String syn = inSyn.readLine();
            String[] synSplit = syn.split(",");
            int id = Integer.parseInt(synSplit[0]);
            String words = synSplit[1];
            synmap.put(id, words);
            length += 1;
        }
        hypograph = new Digraph(length);
        while (inHypo.hasNextLine()) {
            String hypo = inHypo.readLine();
            String[] hypoSplit = hypo.split(",");
            int hypernym = Integer.parseInt(hypoSplit[0]);          
            for (int i = 1; i < hypoSplit.length; i += 1) {
                int hyponym = Integer.parseInt(hypoSplit[i]);
                hypograph.addEdge(hypernym, hyponym);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nouns().contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (String syn : synmap.values()) {
            String[] synSplit = syn.split(" ");
            for (String noun : synSplit) {
                nouns.add(noun);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> ids = findIDs(word);
        Set<Integer> hypoIDs = GraphHelper.descendants(hypograph, ids);
        for (Integer id : hypoIDs) {
            String syn = synmap.get(id);
            String[] synSplit = syn.split(" ");
            for (String noun : synSplit) {
                hyponyms.add(noun);
            }
        }
        return hyponyms;
    }

    private Set<Integer> findIDs(String word) {
        Set<Integer> ids = new HashSet<Integer>();
        for (Map.Entry<Integer, String> entry : synmap.entrySet()) {
            int id = entry.getKey();
            String syn = entry.getValue();
            if (word.equals(syn)) {
                ids.add(id);
            } else {
                String[] synSplit = syn.split(" ");
                if (Arrays.asList(synSplit).contains(word)) {
                    ids.add(id);
                }
            }
        }
        return ids;
    }
}
