package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<Integer, Set<String>> synsetmap;
    private HashMap<Integer, Set<Integer>> hypomap;
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetmap = new HashMap<Integer, Set<String>>();
        hypomap = new HashMap<Integer, Set<Integer>>();


        In file = new In(synsetFilename);
        while (file.hasNextLine()) {
            String currline = file.readLine();
            String[] temp = currline.split(",");
            Integer n = Integer.parseInt(temp[0]);
            String m = temp[1];
            String[] m2 = m.split(" ");
            Set<String> split = new HashSet<String>();
            for (String p : m2) {
                split.add(p);
            }
            synsetmap.put(n, split);
        }
        In file2 = new In(hyponymFilename);
        while (file2.hasNextLine()) {
            Set<Integer> m = new HashSet<Integer>();
            String currline = file2.readLine();
            String[] temp = currline.split(",");
            Integer n = Integer.parseInt(temp[0]);
            for (Integer i = 1; i < temp.length; i++) {
                Integer hypo = Integer.parseInt(temp[i]);
                m.add(hypo);
            }
            if (hypomap.containsKey(n)) {
                m.addAll(hypomap.get(n));
            }
            hypomap.put(n, m);
        }
        graph = new Digraph(synsetmap.size());
        for (Map.Entry<Integer, Set<Integer>> entry:hypomap.entrySet()) {
            Set<Integer> hypos = entry.getValue();
            for (Integer i:hypos) {
                graph.addEdge(entry.getKey(), i);
            }
        }
    }

  /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Map.Entry<Integer, Set<String>> entry:synsetmap.entrySet()) {
            if (entry.getValue().contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> noun = new HashSet<String>();
        for (Map.Entry<Integer, Set<String>> entry:synsetmap.entrySet()) {
            noun.addAll(entry.getValue());
        }  
        
        return noun;
    }

      /** Returns the set of all hyponyms of WORD as well as all synonyms of
        * WORD. If WORD belongs to multiple synsets, return all hyponyms of
        * all of these synsets. See http://goo.gl/EGLoys for an example.
        * Do not include hyponyms of synonyms.
        */
    public Set<String> hyponyms(String word) {
        Set<Integer> id = getid(word);
        Set<Integer> hypoids = GraphHelper.descendants(graph, id);
        Set<String> hypostrings = new HashSet<String>();
        for (Integer i : hypoids) {
            hypostrings.addAll(synsetmap.get(i));
        }
        return hypostrings;
    }

    private Set<Integer> getid(String word) {
        Set<Integer> id = new HashSet<Integer>();
        for (Map.Entry<Integer, Set<String>> entry : synsetmap.entrySet()) {
            if (entry.getValue().contains(word)) {
                id.add(entry.getKey());
            }
        }
        return id;
    }
}
