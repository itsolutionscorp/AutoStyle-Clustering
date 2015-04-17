package ngordnet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.LinkedHashSet;

public class WordNet {
    private String[] synLines;
    private String[] hypLines;
    private Digraph d;
    private HashMap<Integer, String> ss = new HashMap<Integer, String>();
    private Set<String> nouns = new LinkedHashSet<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);
        this.synLines = synFile.readAllLines();
        this.hypLines = hypFile.readAllLines();
        this.d = new Digraph(synLines.length); 
        for (String zed : synLines) {
            String[] middle = zed.split(",");
            ss.put(Integer.parseInt(middle[0]), middle[1]);
            String[] sa = zed.split(",")[1].split(" ");
            for (String fin : sa) {
                this.nouns.add(fin);
            }
        }     
        for (String s : hypLines) {
            String[] p = s.split(",");
            String[] tokens = new String[p.length - 1];
            System.arraycopy(p, 1, tokens, 0, p.length - 1);
            for (String q : tokens) {
                d.addEdge(Integer.parseInt(p[0]), Integer.parseInt(q));
            }
        }
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> q = new LinkedHashSet<Integer>();
        for (String s : synLines) {
            String[] p = s.split(",");
            String[] o = p[1].split(" ");
            for (String s1 : o) {
                if (s1.equals(word)) {
                    q.add(Integer.parseInt(p[0]));
                    q.addAll(GraphHelper.descendants(d, q));
                }
            }
        }
        Set<String> ret = new LinkedHashSet<String>();
        for (Integer i : q) {
            String[] addMes = ss.get(i).split(" ");
            for (String rete : addMes) {
                ret.add(rete);
            }
        }
        return ret;
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

}
