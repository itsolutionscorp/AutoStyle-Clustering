package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph dg;
    private TreeMap<Integer, String> map;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        int count = 0;
        map = new TreeMap<Integer, String>();
        while (syn.hasNextLine()) {
            String line = syn.readLine();
            String[] parts = line.split(",");
            int index = Integer.parseInt(parts[0]);
            String synset = parts[1];
            map.put(index, synset);
            count += 1;
        }
        dg = new Digraph(count);
        In hyp = new In(hyponymFilename);
        while (hyp.hasNextLine()) {
            String line = hyp.readLine();
            String[] parts = line.split(",");
            int hyper = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i += 1) {
                int hypo = Integer.parseInt(parts[i]);
                dg.addEdge(hyper, hypo);
            }
        }
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer i : map.keySet()) {
            String w = map.get(i);
            String[] mults = w.split(" ");
            for (String c : mults) {
                if (noun.equals(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> words = new HashSet<String>();
        for (Integer i : map.keySet()) {
            String word = map.get(i);
            String[] colls = word.split(" ");
            for (String c : colls) {
                words.add(c);
            }
        }
        return words;
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
        Set<String> words = new HashSet<String>();
        Set<Integer> hypos = GraphHelper.descendants(dg, findKeys(word));
        for (Integer i : hypos) {
            String w = map.get(i);
            String[] colls = w.split(" ");
            for (String c : colls) {
                words.add(c);
            }
        }
        return words;
    }

    // Finds the indices that contain w.
    private Set<Integer> findKeys(String w) {
        String[] colls = w.split(" ");
        Set<Integer> k = new HashSet<Integer>();
        for (String c : colls) {
            for (Integer i : map.keySet()) {
                String[] mult = map.get(i).split(" ");
                for (String m : mult) {
                    if (c.equals(m)) {
                        k.add(i);
                    }
                }
            }
        }
        return k;
    }
}
