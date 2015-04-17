package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private HashMap<Integer, String> ids;
    private HashMap<String, Set<Integer>> nouns;
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsets, String hyponyms) {
        ids = new HashMap<Integer, String>();
        nouns = new HashMap<String, Set<Integer>>();
        getNouns(synsets);
        graph = new Digraph(ids.size());
        getHyponyms(hyponyms);      
    }

    private void getNouns(String synsets) {
        In in = new In(synsets);
        while (!in.isEmpty()) {
            String[] synset = in.readLine().split(",");
            int id = Integer.parseInt(synset[0]);
            ids.put(id, synset[1]);
            for (String noun: synset[1].split(" ")) {
                Set<Integer> numberSynsets = nouns.get(noun);
                if (numberSynsets == null) {
                    numberSynsets = new TreeSet<Integer>();
                    numberSynsets.add(id);
                    nouns.put(noun, numberSynsets);
                } else {
                    numberSynsets.add(id);
                }
            }
        }
    }

    private void getHyponyms(String hyponyms) {

        In in = new In(hyponyms);
        while (!in.isEmpty()) {
            String[] hyponym = in.readLine().split(",");
            int id = Integer.parseInt(hyponym[0]);
            for (int i = 1; i < hyponym.length; i++) {
                graph.addEdge(id, Integer.parseInt(hyponym[i]));

            }
        }
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> wordSet = nouns.get(word);
        Set<String> hyponymStrings = new TreeSet<String>();
        Set<Integer> descendants = GraphHelper.descendants(graph, wordSet);
        for (Integer descendant: descendants) {
            for (String noun: ids.get(descendant).split(" ")) {
                if (!hyponymStrings.contains(noun)) {
                    hyponymStrings.add(noun);
                }
            }
        }
        return hyponymStrings;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nouns.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }
}
