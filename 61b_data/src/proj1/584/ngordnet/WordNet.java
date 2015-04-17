package ngordnet;

import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {

    private Digraph wordNet;
    private TreeMap<Integer, String> map;
    private Set<String> synsets;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);

        map = new TreeMap<Integer, String>();
        synsets = new TreeSet<String>();

        String[] synsetLines = synsetFile.readAllLines();
        wordNet = new Digraph(synsetLines.length);

        for (int i = 0; i < synsetLines.length; i++) {
            String[] tokens = synsetLines[i].split(",");
            map.put(Integer.parseInt(tokens[0]), tokens[1]);
            synsets.add(tokens[1]);
        }

        String[] hyponymLines = hyponymFile.readAllLines();

        for (int i = 0; i < hyponymLines.length; i++) {
            String[] tokens = hyponymLines[i].split(",");
            for (int j = 1; j < tokens.length; j++) {
                wordNet.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[j]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String s : synsets) {
            String[] words = s.split(" ");
            for (String w : words) {
                if (w.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new TreeSet<String>();
        for (String s: synsets) {
            String[] words = s.split(" ");
            for (String w : words) {
                nouns.add(w);
            }
        }
        return nouns;
    }

    /* Returns the set of IDs of all synsets that contain the word. */
    private Set<Integer> findSynset(String word) {
        Set<Integer> ids = new TreeSet<Integer>();
        for (int i = 0; i < map.size(); i++) {
            String synset = map.get(i);
            String[] words = synset.split(" ");
            for (String w : words) {
                if (w.equals(word)) {
                    ids.add(i);
                }
            }
        }
        return ids;
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

        Set<Integer> synsetIDs = findSynset(word);
        Set<Integer> hyponymIDs = GraphHelper.descendants(wordNet, synsetIDs);

        Set<String> returnSet = new TreeSet<String>();

        for (int id : synsetIDs) {
            String[] synonyms = map.get(id).split(" ");
            for (String s : synonyms) {
                returnSet.add(s);
            }
        }

        for (int id : hyponymIDs) {
            String[] hyponyms = map.get(id).split(" ");
            for (String h : hyponyms) {
                returnSet.add(h);
            }
        }

        return returnSet;
    }


}
