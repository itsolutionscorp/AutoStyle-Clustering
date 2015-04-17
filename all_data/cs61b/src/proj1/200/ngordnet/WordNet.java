package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph chart; // Digrapgh of all the hyponyms
    private HashMap<Integer, HashSet<String>> idS; // more useful
    private HashMap<String, HashSet<Integer>> nId; // more important
    private Set<String> nouns;
    private In fileSynset;
    private In fileHyponym;
    // I took inspration on my implementation on how to read files with 
    // StdIn on the internet, no copy and paste though
    public WordNet(String synsetFilename, String hyponymFilename) {
        fileSynset = new In(synsetFilename);
        fileHyponym = new In(hyponymFilename);
        idS = new HashMap<Integer, HashSet<String>>();
        nId = new HashMap<String, HashSet<Integer>>();
        while (!fileSynset.isEmpty()) {
            String total = fileSynset.readLine();
            String[] indi = total.split(",");
            String word = indi[1];
            String[] words = word.split(" ");
            int tag = Integer.parseInt(indi[0]);
            HashSet<String> totes = new HashSet<String>();
            for (String w : words) {
                totes.add(w);
            } 
            idS.put(tag, totes);
            for (String w : words) {
                HashSet<Integer> tet = nId.get(w);
                if (tet == null) {
                    tet = new HashSet<Integer>();
                }
                tet.add(tag);
                nId.put(w, tet);
            }
        }
        nouns = nId.keySet();
        chart = new Digraph(idS.size());
        while (!fileHyponym.isEmpty()) {
            String setter = fileHyponym.readLine();
            String[] individuals = setter.split(",");
            int key = Integer.parseInt(individuals[0]);
            for (int l = 1; l < individuals.length; l++) {
                int filler = Integer.parseInt(individuals[l]);
                chart.addEdge(key, filler);
            }
        }
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (noun == null) {
            return false;
        }
        if (noun.equals(" ")) {
            return false;
        }
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> returner = new HashSet<String>();
        Set<Integer> params = nId.get(word);
        Set<Integer> wetter = GraphHelper.descendants(chart, params);
        for (int f : wetter) {
            Set<String> d = idS.get(f);
            for (String s : d) {
                returner.add(s);
            }
        }
        return returner;
    }

}
