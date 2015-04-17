package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;

public class WordNet {
    private HashMap<Integer, HashSet<String>> mappings;
    private Digraph d;

    /* SYNSETS and HYPERNYMS are filenames for the input files. */
    public WordNet(String synsets, String hypernyms) {
        mappings = new HashMap<Integer, HashSet<String>>();
        In in1 = new In(synsets);
        String line = "";
        Integer k = 0;
        String[] split;
        String[] synonyms;
        while (in1.hasNextLine()) {
            line = in1.readLine();
            split = line.split(",");
            k = Integer.parseInt(split[0]);
            synonyms = split[1].split(" ");
            mappings.put(k, new HashSet<String>(Arrays.asList(synonyms)));
        }
        d = new Digraph(mappings.size());
        In in2 = new In(hypernyms);
        int src = 0;
        while (in2.hasNextLine()) {
            line = in2.readLine();
            split = line.split(",");
            for (int i = 0; i < split.length; i += 1) {
                if (i == 0) {
                    src = Integer.parseInt(split[i]);
                } else {
                    d.addEdge(src, Integer.parseInt(split[i]));
                }
            }
        }
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> nounSet = new HashSet<String>();
        for (Integer i: mappings.keySet()) {
            for (String s: mappings.get(i)) {
                nounSet.add(s);
            }
        }
        return nounSet;
    }

    /** Returns true if WORD is a noun. WORD may be a collocation. */
    public boolean isNoun(String word) {
        for (Integer i: mappings.keySet()) {
            for (String s: mappings.get(i)) {
                if (word.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns the set of all hypoynms of WORD. */
    public Set<String> hyponyms(String word) {
        Set<String> hypos = new HashSet<String>();
        Set<Integer> intSet = new HashSet<Integer>();
        for (Integer i: mappings.keySet()) {
            for (String s: mappings.get(i)) {
                if (word.equals(s)) {
                    intSet.add(i);
                    for (Integer desc: GraphHelper.descendants(d, intSet)) {
                        for (String a: mappings.get(desc)) {
                            hypos.add(a);
                        }
                    }
                }
            }
        }
        return hypos;
    }
}
