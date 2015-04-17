package ngordnet;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private Map<Integer, String[]> nounLookup = new HashMap<>();
    private Map<String, Set<Integer>> idLookup = new HashMap<>();
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypoFile = new In(hyponymFilename);
        while (synFile.hasNextLine()) {
            String[] parts = synFile.readLine().split(",");
            int id = Integer.parseInt(parts[0]);
            String[] nouns = parts[1].split(" ");
            nounLookup.put(id, nouns);
            for (String noun : nouns) {
                if (!idLookup.containsKey(noun)) {
                    idLookup.put(noun, new HashSet<Integer>());
                }
                idLookup.get(noun).add(id);
            }
        }
        graph = new Digraph(nounLookup.size());
        while (hypoFile.hasNextLine()) {
            String[] parts = hypoFile.readLine().split(",");
            int origin = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                graph.addEdge(origin, Integer.parseInt(parts[i]));
            }
        }
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return idLookup.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return idLookup.keySet();
    }
    
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String noun) {
        Set<String> set = new HashSet<>();
        Set<Integer> ids = GraphHelper.descendants(graph, idLookup.get(noun));
        for (int id : ids) {
            for (String str : nounLookup.get(id)) {
                set.add(str);
            }
        }
        return set;
    }
}
