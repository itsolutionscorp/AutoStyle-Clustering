package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;


public class WordNet {
    private final Map<Integer, String> synsetmap;
    private Digraph g;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetmap = new HashMap<>();
        synsetreader(synsetFilename);
        g = hyponymsreader(hyponymFilename);
    }

    private void synsetreader(String synsetFilename) {
        In inputstream = new In(synsetFilename);

        while (inputstream.hasNextLine()) {
            String[] parsed = inputstream.readLine().split(",");
            int number = Integer.parseInt(parsed[0]);
            synsetmap.put(number, parsed[1]);
        }
    }

    private Digraph hyponymsreader(String hyponymFilename) {
        g = new Digraph(synsetmap.size());
        In inputstream = new In(hyponymFilename);

        while (inputstream.hasNextLine()) {
            String[] parsed = inputstream.readLine().split(",");
            Integer number = Integer.parseInt(parsed[0]);
            for (int x = 0; x < parsed.length - 1; x++) {
                g.addEdge(number, Integer.parseInt(parsed[x + 1]));
            }
        }
        return g;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> allnouns = nouns();
        return allnouns.contains(noun);
    }
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allnouns = new HashSet<>(); 
        for (int currkey : synsetmap.keySet()) { 
            String[] thevalues = synsetmap.get(currkey).split(" ");
            for (int i = 0; i < thevalues.length; i++) { 
                if (!allnouns.contains(thevalues[i])) {
                    allnouns.add(thevalues[i]);
                }
            }
        }
        return allnouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    private Set<Integer> hyponymshelper(Map<Integer, String> map, String word) {
        Set<Integer> ids = new HashSet<>();
        for (int x : synsetmap.keySet()) {
            String[] parsed = synsetmap.get(x).split(" ");
            for (int i = 0; i < parsed.length; i++) {
                if (word.equals(parsed[i])) {
                    ids.add(x);
                }
            }
        }
        return ids;
    }

    public Set<String> hyponyms(String word) {
        Set<String> finalset = new HashSet<>();
        for (int vertex : GraphHelper.descendants(g, hyponymshelper(synsetmap, word))) {
            String[] tvalues = synsetmap.get(vertex).split(" ");
            for (int i = 0; i < tvalues.length; i++) {
                if (!finalset.contains(tvalues[i])) {
                    finalset.add(tvalues[i]);
                }
            }
        }
        return finalset;
    }

}
