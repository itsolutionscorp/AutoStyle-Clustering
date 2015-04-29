package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {

    private ArrayList<String[]> lst = new ArrayList<String[]>();
    //TreeMap<Integer, String> lst = new TreeMap<Integer, String>();
    private Digraph g;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);

        //string.split(String delimiter)
        //Integer.parseInt(String n)
        while (synsets.hasNextLine()) {
            String cur = synsets.readLine();
            String[] split = cur.split(",");
            String[] synonyms = split[1].split(" ");
            lst.add(Integer.parseInt(split[0]), synonyms);
        }
        g = new Digraph(lst.size());
        while (hyponyms.hasNextLine()) {
            String cur = hyponyms.readLine();
            String[] split = cur.split(",");
            for (int j = 1; j < split.length; j++) {
                g.addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[j]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String[] s : lst) {
            for (String n : s) {
                //if (Arrays.asList(lst).contains(noun))
                if (n.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new TreeSet<String>();
        for (String[] f : lst) {
            for (String n : f) {
                nouns.add(n);
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
        Set<String> res = new TreeSet<String>();
        Set<Integer> matches = new TreeSet<Integer>();
        for (String[] s : lst) {
            for (String n : s) {
                if (n.equals(word)) {
                    matches.add(lst.indexOf(s));
                }
            }
        }
        matches = GraphHelper.descendants(g, matches);

        for (int i : matches) {
            String[] split = lst.get(i);
            for (String s : split) {
                res.add(s);
            }
        }
        return res;
        // Set<String> set = new TreeSet<String>();
        // for (String[] s : lst) {
        //     for (String n : s) {
        //         if (n.equals(word)) {
        //             for (String b : s) {
        //                 set.add(b);
        //             }
        //             for (int i : g.adj(lst.indexOf(s))) {
        //                 for (String k : lst.get(i)) {
        //                     set.add(k);
        //                     for (String f : hyponyms(k)) {
        //                         set.add(f);
        //                     }
        //                 }
        //             }
        //         }
        //     }
        // }
        // return set;
    }
}
