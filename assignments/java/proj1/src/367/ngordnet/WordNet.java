package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;

public class WordNet {
    private Digraph wn;
    private HashMap<Integer, String[]> syns;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        In hypo = new In(hyponymFilename);
        syns = new HashMap<Integer, String[]>();
        String[] lin;
        while (syn.hasNextLine()) {
            lin = syn.readLine().split(",");
            syns.put(Integer.parseInt(lin[0]), lin[1].split(" "));
        }
        wn = new Digraph(syns.size());
        while (hypo.hasNextLine()) {
            lin = hypo.readLine().split(",");
            for (int e = 1; e < lin.length; e++) {
                wn.addEdge(Integer.parseInt(lin[0]), Integer.parseInt(lin[e]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> all = new TreeSet<String>();
        for (String[] value : syns.values()) {
            for (String noun: value) {
                all.add(noun);
            }
        }
        return all;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
        * WORD. If WORD belongs to multiple synsets, return all hyponyms of
        * all of these synsets. See http://goo.gl/EGLoys for an example.
        * Do not include hyponyms of synonyms.
        */
    public Set<String> hyponyms(String word) {
        Set<String> hypos = new TreeSet<String>();
        Set<Integer> wordSyns = new TreeSet<Integer>();

        boolean containsW = false;
        for (Map.Entry<Integer, String[]> entry : syns.entrySet()) {
            for (String n : entry.getValue()) {
                if (word.equals(n)) {
                    wordSyns.add(entry.getKey());
                    containsW = true;
                }
            }

            if (containsW) {
                for (String n : entry.getValue()) {
                    hypos.add(n);
                }
            }
            containsW = false;
        }

        Set<Integer> descends = GraphHelper.descendants(wn, wordSyns);
        for (Integer descend: descends) {
            for (String s : syns.get(descend)) {
                hypos.add(s);
            }
        }
        return hypos;
    }
}
