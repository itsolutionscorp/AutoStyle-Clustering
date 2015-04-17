package ngordnet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph dg;
    private HashMap<String, HashSet<Integer>> wordToIdMap = new HashMap<String, HashSet<Integer>>();
    private HashMap<Integer, HashSet<String>> idToWordMap = new HashMap<Integer, HashSet<String>>();
    public WordNet(String synsetFilename, String hyponymFilename) {
        In sf = new In(synsetFilename);
        In hf1 = new In(hyponymFilename);
        String[] s;
        Integer a;
        int counter = 0;
        HashSet<Integer> ids;
        HashSet<String> synonyms;
        String[] spaceSplit;
        while (sf.hasNextLine()) {
            s = sf.readLine().split(",");
            a = Integer.parseInt(s[0]);
            spaceSplit = s[1].split(" ");
            for (String p: spaceSplit) {
                if (idToWordMap.containsKey(a)) {
                    idToWordMap.get(a).add(p);
                } else {
                    synonyms = new HashSet<String>();
                    synonyms.add(p);
                    idToWordMap.put(a, synonyms);
                }
                if (wordToIdMap.containsKey(p)) {
                    wordToIdMap.get(p).add(a);
                } else {
                    ids = new HashSet<Integer>();
                    ids.add(a);
                    wordToIdMap.put(p, ids);
                }
            }
        }
        dg = new Digraph(idToWordMap.size());
        while (hf1.hasNextLine()) {
            s = hf1.readLine().split(",");
            a = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i += 1) {
                dg.addEdge(a, Integer.parseInt(s[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToIdMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToIdMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> vertices =  GraphHelper.descendants(dg, wordToIdMap.get(word));
            HashSet<String> hyponyms = new HashSet<String>();
            for (int x: vertices) {
                hyponyms.addAll(idToWordMap.get(x));
            }
            return hyponyms;
        } else {
            return null;
        }
    }
}
