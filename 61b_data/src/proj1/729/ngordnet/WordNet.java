package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

public class WordNet {
    private HashMap<Integer, String[]> map;
    private Digraph digraph;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        map = new HashMap<Integer, String[]>();
        String line;
        String[] store;
        int id;
        String[] word;
        while (synset.hasNextLine()) {
            line = synset.readLine();
            store = line.split(",");
            id = Integer.parseInt(store[0]);
            word = store[1].split(" ");
            map.put(id, word);
        }
        
        digraph = new Digraph(map.size());
        In hyponym = new In(hyponymFilename);
        int top;
        while (hyponym.hasNextLine()) {
            line = hyponym.readLine();
            store = line.split(",");
            top = Integer.parseInt(store[0]);
            for (int i = 1; i < store.length; i++) {
                int temp = Integer.parseInt(store[i]);
                digraph.addEdge(top, temp);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String[] i : map.values()) {
            for (String s : i) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> setOfNouns = new HashSet();
        for (String[] i : map.values()) {
            for (String s : i) {
                setOfNouns.add(s);
            }
        }
        return setOfNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> setOfHyponyms = new HashSet();
        HashSet<Integer> a = new HashSet();
        for (int i : map.keySet()) {
            for (String s : map.get(i)) {
                if (s.equals(word)) {
                    a.add(i);
                    for (int dec : GraphHelper.descendants(digraph, a)) {
                        for (String b : map.get(dec)) {
                            setOfHyponyms.add(b);
                        }
                    }
                }
            }
        }
        return setOfHyponyms;
    }
}
