package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;

public class WordNet {
    
    private Digraph graph;
    private Map<Integer, Set<String>> map;
    private String[] synset, hypernym;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename)  {
        In file = new In(synsetFilename), file1 = new In(hypernymFilename);
        synset = file.readAllLines();
        map = new HashMap();
        hypernym = file1.readAllLines();
        for (String s : synset) {
            Set<String> vals = new TreeSet();
            String[] temp = s.split(",");
            int k = Integer.parseInt(temp[0]);
            for (String s1 : temp[1].split(" ")) {
                vals.add(s1);
            }
            map.put(k, vals);
        }
        graph = new Digraph(synset.length);
        for (String s : hypernym) {
            String[] temp = s.split(",");
            int first = Integer.parseInt(temp[0]);
            for (int i = 1; i < temp.length; i += 1) {
                graph.addEdge(first, Integer.parseInt(temp[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet();
        for (int i : map.keySet()) {
            allNouns.addAll(map.get(i));
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new TreeSet();
        Set<Integer> ints = new TreeSet();
        for (int i : map.keySet()) {
            if (map.get(i).contains(word)) {
                ints.add(i);
            }
        }
        Set<Integer> vals = GraphHelper.descendants(graph, ints);
        hyponyms.add(word);
        for (int i : vals) {
            hyponyms.addAll(map.get(i));
        }
        return hyponyms;
    }
}
