package ngordnet; // part of package ngordnet
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;


// read input files and store in ADTs
public class WordNet {
    private Map<String, Integer> m1 = new HashMap<String, Integer>();
    private Map<Integer, String> m2 = new HashMap<Integer, String>();
    private Digraph d;
    private int vertices; // count how many entries there are
    private Set<String> nounSet = new HashSet<String>();
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // read files (based on help from Piazza post 2855)
        In sFile = new In(synsetFilename);
        In hFile = new In(hyponymFilename);
        vertices = 0;
        while (sFile.hasNextLine()) {
            String s = sFile.readLine();
            String[] syn = s.split(",");
            int index = Integer.parseInt(syn[0]);
            m1.put(syn[1], index);
            m2.put(index, syn[1]);
            String[] nouns = syn[1].split(" ");
            for (String n:nouns) {
                nounSet.add(n);
            }
            vertices += 1;
        }
        d = new Digraph(vertices); // create Digraph database
        // add Digraph edges
        while (hFile.hasNextLine()) {
            String h = hFile.readLine();
            String[] hyp = h.split(",");
            int hypernym = Integer.parseInt(hyp[0]);
            for (int i = 1; i < hyp.length; i++) {
                int x = Integer.parseInt(hyp[i]);
                d.addEdge(hypernym, x);
            }
        }
    }
    
      /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        // set of synonym vertices
        Set<Integer> synStart = new HashSet<Integer>();
        // set of hyponym vertices
        Set<Integer> related;
        Set<String> set = new HashSet<String>();

        for (String item:m1.keySet()) {
            if (item.contains(word)) {
                int ind = m1.get(item);
                synStart.add(ind);
            } 
        }
        related = GraphHelper.descendants(d, synStart);
        for (int id:synStart) {
            //add synonyms
            String s = m2.get(id);
            if (s.contains(" ")) {
                String[] t = s.split(" ");
                for (String u:t) {
                    set.add(u);
                }
            } else {
                set.add(s);
            }
        }
        for (int id:related) {
            // add hyponyms
            String s = m2.get(id);
            if (s.contains(" ")) {
                String[] t = s.split(" ");
                for (String u:t) {
                    set.add(u);
                }
            } else {
                set.add(s);
            }
        }
        return set;
    }

     /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nounSet.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }
}
