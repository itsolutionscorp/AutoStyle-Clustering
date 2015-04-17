package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// @author Leah Dickstein

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private String nxt;
    private HashMap<Integer, String[]> synsets = new HashMap<Integer, String[]>();
    private HashMap<String, HashSet<Integer>> invsynsets = new HashMap<String, HashSet<Integer>>();
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        String[] entry = new String[3];
        while (syn.hasNextLine()) {
            nxt = syn.readLine();
            entry = nxt.split(",");
            String[] synonyms = entry[1].split(" ");
            Integer idx = Integer.parseInt(entry[0]);
            this.synsets.put(idx, synonyms);
            for (String en : synonyms) {
                if (!this.invsynsets.containsKey(en)) {
                    this.invsynsets.put(en, new HashSet<Integer>());
                }
                this.invsynsets.get(en).add(idx);
            }
        }

        this.g = new Digraph(synsets.size());
        In hypo = new In(hyponymFilename);
        while (hypo.hasNextLine()) {
            nxt = hypo.readLine();
            String[] edge = nxt.split(",");
            Integer root = Integer.parseInt(edge[0]);
            for (int i = 1; i < edge.length; i++) {
                this.g.addEdge(root, Integer.parseInt(edge[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return invsynsets.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return invsynsets.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> hypony = new HashSet<String>();
        HashSet<Integer> nums = invsynsets.get(word); //synsets of that word
        Set<Integer> descend = GraphHelper.descendants(g, nums);
        nums.addAll(descend);
        for (Integer idx : nums) {
            for (String s : synsets.get(idx)) {
                hypony.add(s);
            }
        }
        return hypony;
    }
}
