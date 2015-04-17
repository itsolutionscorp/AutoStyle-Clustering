package ngordnet;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
/** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Map<String, Set<Integer>> dict;
    private Digraph relations;

    public WordNet(String synsetFilename, String hyponymFilename) {
        dict = new HashMap<String, Set<Integer>>();
        int numSynsets = 0;
        In synsetRead = new In(synsetFilename);
        while (synsetRead.hasNextLine()) {
            numSynsets += 1;
            String[] line = synsetRead.readLine().split(",");
            Integer id = Integer.parseInt(line[0]);
            String[] words = line[1].split(" ");
            for (String x: words) {
                if (dict.containsKey(x)) {
                    dict.get(x).add(id);
                } else {
                    HashSet<Integer> temp = new HashSet<Integer>();
                    temp.add(id);
                    dict.put(x, temp);
                }
            }
        }
        synsetRead.close();
        relations = new Digraph(numSynsets);
        In hyponymRead = new In(hyponymFilename);
        while (hyponymRead.hasNextLine()) {
            String[] line = hyponymRead.readLine().split(",");
            int hypernym = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i += 1) {
                int hyponym = Integer.parseInt(line[i]);
                relations.addEdge(hypernym, hyponym);
            }
        }
        hyponymRead.close();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return dict.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return dict.keySet();
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
        Set<Integer> vertices = GraphHelper.descendants(relations, dict.get(word));
        Set<String> allHyponyms = new HashSet<String>();
        for (String w: dict.keySet()) {
            for (int i: vertices) {
                if (dict.get(w).contains(i)) {
                    allHyponyms.add(w);
                }
            }
        }
        return allHyponyms;
    }
}
