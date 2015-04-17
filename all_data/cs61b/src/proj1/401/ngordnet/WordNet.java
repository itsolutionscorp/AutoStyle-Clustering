package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {

    private HashMap<Integer, HashSet<String>> synset;
    private Digraph hypset;
    private HashMap<String, HashSet<Integer>> id;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new HashMap<Integer, HashSet<String>>();
        id = new HashMap<String, HashSet<Integer>>();
        In synsetIn = new In(synsetFilename);
        String line;
        String[] components;
        String[] synonyms;
        int index;
        int[] values;
        int elements = 0;
        while (synsetIn.hasNextLine()) {
            components = synsetIn.readLine().split(",");
            index = Integer.parseInt(components[0]);
            synonyms = components[1].split(" ");
            for (String s : synonyms) {
                if (!id.containsKey(s)) {
                    id.put(s, new HashSet<Integer>());
                }
                id.get(s).add(index);
                for (Integer i : id.get(s)) {
                    if (!synset.containsKey(i)) {
                        synset.put(i, new HashSet<String>());
                    }
                }
                for (String syn : synonyms) {
                    synset.get(index).add(syn);
                }
            }
            elements += 1;
        }
        In hypsetIn = new In(hyponymFilename);
        hypset = new Digraph(elements);
        while (hypsetIn.hasNextLine()) {
            components = hypsetIn.readLine().split(",");
            index = Integer.parseInt(components[0]);
            values = new int[components.length - 1];
            for (int i = 0; i < values.length; i += 1) {
                values[i] = Integer.parseInt(components[i + 1]);
            }
            for (Integer v : values) {
                hypset.addEdge(index, v);
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return id.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            throw new IllegalArgumentException("Not a noun");
        }
        Set<Integer> ids = id.get(word);
        Set<String> nyms = new HashSet<String>();
        ids = GraphHelper.descendants(hypset, ids);
        for (Integer i : ids) {
            if (synset.containsKey(i)) {
                nyms.addAll(synset.get(i));
            }
        }
        return nyms;
    }
}
