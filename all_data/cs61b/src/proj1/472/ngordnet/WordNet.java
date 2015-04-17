package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private int vertices;
    private Digraph digraph;
    private HashMap<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        hashSynsets(synsetFilename);
        makeDigraph(hyponymFilename);
    }

    /* Returns a HashMap hashing synsets to their indices from SYNSETFILENAME */
    private void hashSynsets(String synsetFilename) {
        In synsetReader = new In(synsetFilename);
        while (synsetReader.hasNextLine()) {
            String[] fields = synsetReader.readLine().split(",");
            String[] synsetWords = fields[1].split(" ");
            Set<String> synset = new HashSet<String>();
            for (String word : synsetWords) {
                synset.add(word);
            }
            map.put(new Integer(Integer.parseInt(fields[0])), synset);
            vertices = vertices + 1;
        } 
    }

    /* Returns a digraph mapping all hyponyms from HYPONYMFILENAME */
    private void makeDigraph(String hyponymFilename) {
        In hyponymReader = new In(hyponymFilename);
        digraph = new Digraph(vertices);
        while (hyponymReader.hasNextLine()) {
            String[] hyponyms = hyponymReader.readLine().split(",");
            int hypernym = Integer.parseInt(hyponyms[0]);
            for (int i = 1; i < hyponyms.length; i++) {
                digraph.addEdge(hypernym, Integer.parseInt(hyponyms[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (Set<String> synset : map.values()) {
            nouns.addAll(synset);
        } return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> synsetIDs = new HashSet<Integer>();
        for (Integer key : map.keySet()) {
            Set<String> synset = map.get(key);
            if (synset.contains(word)) {
                synsetIDs.add(key);
            }
        }

        Set<Integer> hyponymIDs = GraphHelper.descendants(digraph, synsetIDs);
        for (Integer id : hyponymIDs) {
            for (String hyponym : map.get(id)) {
                hyponyms.add(hyponym);
            }
        } return hyponyms;
    }
}
