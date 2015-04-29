package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class WordNet {
    private Digraph g;
    private Map<Integer, Set<String>> m;
    private Set<String> nouns;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {
        In synset = new In(synsetFilename);
        In hypernym = new In(hypernymFilename);
        String[] synsetArray = synset.readAllLines();
        String[] hypernymArray = hypernym.readAllLines();
        g = new Digraph(synsetArray.length);
        m = new HashMap<Integer, Set<String>>();
        nouns = new HashSet<String>();

        for (String line : hypernymArray) {
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                g.addEdge(id, Integer.parseInt(tokens[i]));
            }
        }

        for (String line : synsetArray) {
            String[] tokens = line.split(",");
            String[] words = tokens[1].split(" ");
            Set<String> s = new HashSet<String>();
            for (String w : words) {
                s.add(w);
                nouns.add(w);
            }
            m.put(Integer.parseInt(tokens[0]), s);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        /* obtaining keys help from stack overflow: 
         * http://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
         */
        Set<Integer> synsetIDs = new TreeSet<Integer>();
        for (Map.Entry<Integer, Set<String>> e : m.entrySet()) {
            if (e.getValue().contains(word)) {
                synsetIDs.add(e.getKey());
            }
        }
        Set<String> results = new HashSet<String>();
        for (Integer i : GraphHelper.descendants(g, synsetIDs)) {
            for (String w : m.get(i)) {
                results.add(w);
            }
        }
        return results;
    }
}
