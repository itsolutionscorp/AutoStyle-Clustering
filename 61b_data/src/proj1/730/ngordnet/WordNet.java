package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, HashSet<String>> synsetWords;
    private Digraph digraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In inSyn = new In(synsetFilename);
        In inHyp = new In(hyponymFilename);

        synsetWords = new HashMap<Integer, HashSet<String>>();
        while (!inSyn.isEmpty()) {
            String thisLine = inSyn.readLine();
            String[] splitthisLine = thisLine.split(",");
            String[] splitwords = splitthisLine[1].split(" ");
            HashSet<String> words = new HashSet<String>();
            for (int i = 0; i < splitwords.length; i++) {
                words.add(splitwords[i]);
            }
            synsetWords.put(Integer.parseInt(splitthisLine[0]), words);
        }

        digraph = new Digraph(synsetWords.size());

        while (!inHyp.isEmpty()) {
            String idLine = inHyp.readLine();
            String[] splitIdLine = idLine.split(",");
            int nums = splitIdLine.length;
            for (int i = 1; i < nums; i++) {
                digraph.addEdge(Integer.parseInt(splitIdLine[0]), Integer.parseInt(splitIdLine[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer key : synsetWords.keySet()) {
            HashSet<String> check = synsetWords.get(key);
            if (check.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    private HashSet<String> getSynset(int num) {
        for (Integer key : synsetWords.keySet()) {
            if (key == num) {
                return synsetWords.get(key);
            }
        }
        return null;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (Integer key : synsetWords.keySet()) {
            HashSet<String> check = synsetWords.get(key);
            for (String stuff : check) {
                nouns.add(stuff);
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
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> hypIds = new HashSet<Integer>();
        for (Integer key : synsetWords.keySet()) {
            HashSet<String> check = synsetWords.get(key);
            if (check.contains(word)) {
                hypIds.add(key);
            }
        }
        Set<Integer> descend = GraphHelper.descendants(digraph, hypIds);
        for (Integer id : descend) {
            for (String noun : getSynset(id)) {
                hyponyms.add(noun);
            }
        }
        return hyponyms;
    }
    
}
