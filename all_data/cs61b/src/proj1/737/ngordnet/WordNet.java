package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;


public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME  */
    private TreeMap<Integer, String> synsets = new TreeMap<>();
    private Digraph graph;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In reader = new In(synsetFilename);
        String line = reader.readLine();
        while (line != null) {
            String[] rawTokens = line.split(",");
            synsets.put(Integer.parseInt(rawTokens[0]), rawTokens[1]);
            line = reader.readLine();
        }
        graph = new Digraph(synsets.size());
        String hyponyms = "";
        In hyporeader = new In(hyponymFilename);
        String hypoline = hyporeader.readLine();
        while (hypoline != null) {
            String[] rawTokens = hypoline.split(",");
            for (int i = 1; i < rawTokens.length; i += 1) {
                graph.addEdge(Integer.parseInt(rawTokens[0]), Integer.parseInt(rawTokens[i]));
            }
            hypoline = hyporeader.readLine();
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Map.Entry<Integer, String> entry : synsets.entrySet()) {
            String[] multwords = entry.getValue().split(" ");
            for (String i : multwords) {
                if (i.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new TreeSet<String>();
        for (Map.Entry<Integer, String> entry : synsets.entrySet()) {
            String[] multwords = entry.getValue().split(" ");
            for (String i : multwords) {
                nouns.add(i);   
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
        Set<Integer> hyponymsIDs = new TreeSet<Integer>();
        Set<String> hyponymset = new TreeSet<String>();
        if (this.isNoun(word)) {
            for (Map.Entry<Integer, String> entry : synsets.entrySet()) {
                String[] multwords = entry.getValue().split(" ");
                for (String i : multwords) {
                    if (i.equals(word)) {
                        hyponymsIDs.add(entry.getKey());
                    }
                }
            }
            Set<Integer> ids = GraphHelper.descendants(graph, hyponymsIDs);
            for (int i : ids) {
                if (synsets.get(i).contains(" ")) {
                    String[] multwords = synsets.get(i).split(" ");
                    for (int p = 0; p < multwords.length; p++) {
                        hyponymset.add(multwords[p]);
                    }
                } else {
                    hyponymset.add(synsets.get(i));
                }
            }
            return hyponymset;
        } else {
            return null;
        }
    }
}
