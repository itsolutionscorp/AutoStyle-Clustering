package ngordnet;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet {

    private HashMap<Integer, String> idsynsetMap;
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypoFile = new In(hyponymFilename);

        String line; 
        String[] remainders;
        String[] hyponymRemainder;
        int id = 0; 
        String syn = "synset";

        idsynsetMap = new HashMap<Integer, String>();

        while (synFile.hasNextLine()) {
            line = synFile.readLine();
            remainders = line.split(",");
            id = Integer.parseInt(remainders[0]);
            syn = remainders[1];
            idsynsetMap.put(id, syn);
        }

        graph = new Digraph(id + 1);

        while (hypoFile.hasNextLine()) {
            line = hypoFile.readLine();
            remainders = line.split(",");
            id = Integer.parseInt(remainders[0]);
            hyponymRemainder = new String[remainders.length - 1];
            System.arraycopy(remainders, 1, hyponymRemainder, 0, remainders.length - 1);

            for (String hyponymString : hyponymRemainder) {
                graph.addEdge(id, Integer.parseInt(hyponymString));
            }

        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> set = new HashSet<String>();

        for (String i : idsynsetMap.values()) {
            for (String noun : i.split(" ")) {
                set.add(noun);
            }

        }

        return set;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> synsetID = new HashSet<Integer>();

        for (Integer id : idsynsetMap.keySet()) {
            for (String w : idsynsetMap.get(id).split(" ")) {
                if (word.equals(w)) {
                    synsetID.add(id);
                }
            }
        }

        Set<Integer> hyponymID = GraphHelper.descendants(graph, synsetID);

        HashSet<String> hyponyms = new HashSet<String>();
        String hyponymSyn;
        for (Integer id : hyponymID) {
            for (String w : idsynsetMap.get(id).split(" ")) {
                hyponyms.add(w);
            }
        }
        return hyponyms;
    }
}
