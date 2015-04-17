package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, Set<String>> h;
    private Digraph d;
    public WordNet(String synsetFilename, String hyponymFilename) {
        h = new HashMap<Integer, Set<String>>();
        String s;
        String[] tokens;
        Integer id;
        In in1 = new In(synsetFilename);
        Set<String> synset = new HashSet<String>();
        while (in1.hasNextLine()) {
            s = in1.readLine();
            s = s.substring(0, s.indexOf(",", s.indexOf(",") + 1));
            tokens = s.split("[ ,]");
            id = Integer.parseInt(tokens[0]);
            synset = new HashSet<String>(Arrays.asList(tokens));
            synset.remove(Integer.toString(id));
            h.put(id, synset);
        }
        d = new Digraph(h.size());
        In in2 = new In(hyponymFilename);
        while (in2.hasNextLine()) {
            s = in2.readLine();
            tokens = s.split("[,]");
            for (id = 1; id < tokens.length; id++) {
                d.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[id]));
            }
            
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String s: nouns()) {
            if (s.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> s = new TreeSet<String>();
        for (Set<String> stringset:h.values()) {
            for (String word : stringset) {
                s.add(word);
            }
        }
        return s;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> synsetnumbers = new HashSet<Integer>();
        Set<String> hyponyms = new HashSet<String>();
        for (Integer synsetnumber:h.keySet()) {
            Set<String> temp0 = h.get(synsetnumber);
            if (temp0.contains(word)) {
                synsetnumbers.add(synsetnumber);
            }
        }
        Set<Integer> hypo = new HashSet<Integer>(synsetnumbers);
        Set<String> temp;

        while (hypo.size() != 0) {
            hypo = GraphHelper.descendants(d, hypo);
            for (Integer i : hypo) {
                temp = h.get(i);
                for (String s : temp) {
                    hyponyms.add(s);
                }
            }
            for (Integer s :synsetnumbers) {
                hypo.remove(s);
            }
            synsetnumbers = new HashSet<Integer>(hypo);
            temp = null;
        }
        return hyponyms;
    }
}
