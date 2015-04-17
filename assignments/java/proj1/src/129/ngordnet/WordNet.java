package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Map<String, Integer> storage = new HashMap<String, Integer>();
    private Map<Integer, String> invertstorage = new HashMap<Integer, String>();
    private Digraph wordnet;
    private int numwords;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        String[] parts;
        int id;

        // --------- doing processing of the synset file
        while (!synset.isEmpty()) {
            parts = (synset.readLine()).split(",");
            id = Integer.parseInt(parts[0]);
            invertstorage.put(id, parts[1]);
            storage.put(parts[1], id);
            numwords++;
        }
        // --------- doing processing of the hyponym file
        In hyponym = new In(hyponymFilename);
        wordnet = new Digraph(numwords);
        while (!hyponym.isEmpty()) {
            parts = (hyponym.readLine()).split(",");
            id = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                wordnet.addEdge(id, Integer.parseInt(parts[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (noun.contains(" ")) {
            return false;
        }
        if (nouns().contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new TreeSet<String>();
        for (String key : storage.keySet()) {
            if (key.contains(" ")) {
                String[] parts = key.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    nouns.add(parts[i]);
                }
            } else {
                nouns.add(key);
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
        Set<String> hyponyms = new TreeSet<String>();
        Set<Integer> wordID = new TreeSet<Integer>();
        for (String key : storage.keySet()) {
            if (key.contains(word)) {
                wordID.add(storage.get(key));
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(wordnet, wordID);
        for (Integer key: descendants) {
            if (invertstorage.containsKey(key)) {
                if ((invertstorage.get(key)).contains(" ")) {
                    String[] parts = (invertstorage.get(key)).split(" ");
                    for (int i = 0; i < parts.length; i++) {
                        hyponyms.add(parts[i]);
                    }
                } else {
                    hyponyms.add(invertstorage.get(key));
                }
            }
        }
        return hyponyms;
    }
}
