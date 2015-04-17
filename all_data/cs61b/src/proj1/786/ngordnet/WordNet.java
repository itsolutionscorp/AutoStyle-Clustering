package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;
public class WordNet {
    private String[] synsetslines;
    private String[] hyponymslines;
    private Map<Integer, String[]> syns;
    private Digraph g;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In sfn = new In(synsetFilename);
        In hfn = new In(hyponymFilename);
        synsetslines = sfn.readAllLines();
        hyponymslines = hfn.readAllLines();
        syns = new TreeMap<Integer, String[]>();
        g = new Digraph(synsetslines.length);
        for (String temp : hyponymslines) {
            String[] x = temp.split(",");
            for (int i = 1; i < x.length; i += 1) {
                g.addEdge(Integer.parseInt(x[0]), Integer.parseInt(x[i]));
            }
        }
        for (String temp: synsetslines) {
            String[] y = temp.split(",");
            int id = Integer.parseInt(y[0]);
            String wordline = y[1];
            String[] words = wordline.split(" ");
            syns.put(id, words);
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Map.Entry<Integer, String[]> entry : syns.entrySet()) {
            for (String wordmatch : entry.getValue()) {
                if (noun.equals(wordmatch)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new TreeSet<String>();
        for (Map.Entry<Integer, String[]> entry : syns.entrySet()) {
            for (String word : entry.getValue()) {
                nouns.add(word);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> hypid = new HashSet<Integer>();
        Set<String> hypos = new HashSet<String>();
        for (Map.Entry<Integer, String[]> entry : syns.entrySet()) {
            for (String wordmatch : entry.getValue()) {
                if (word.equals(wordmatch)) {
                    hypid.add(entry.getKey());
                }
            }
        }
        hypid = GraphHelper.descendants(g, hypid);
        Iterator<Integer> iterator = hypid.iterator();
        while (iterator.hasNext()) {
            Integer currenthypid = iterator.next();
            for (String s : syns.get(currenthypid)) {
                hypos.add(s);
            }
        }
        return hypos;
    }
}
