package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Set<String> nounset;
    private Set<String> reverseset;
    private Set<Integer> ranks;
    private HashMap<String, Set<Integer>> maps;
    private HashMap<Integer, Set<String>> reverse;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        int count = 0;
        In synsetfile = new In(synsetFilename);
        In hyponymfile = new In(hyponymFilename);
        String[] words = synsetfile.readAllLines();
        String[] hyponyms = hyponymfile.readAllLines();
        nounset = new TreeSet<String>();
        maps = new HashMap<String, Set<Integer>>();
        reverse = new HashMap<Integer, Set<String>>();
        for (String word : words) {
            count = count + 1;
            String[] parts = word.split(",");
            int part0 = Integer.parseInt(parts[0]);
            String part1 = parts[1];
            reverseset = new TreeSet<String>();
            for (String s : part1.split(" ")) {
                reverseset.add(s);
                reverse.put(part0, reverseset);
                if (maps.containsKey(s)) {
                    Set<Integer> temp = maps.get(s);
                    temp.add(part0);
                    maps.put(s, temp);
                } else {
                    ranks = new TreeSet<Integer>();
                    nounset.add(s);
                    ranks.add(part0);
                    maps.put(s, ranks);
                }
            }
        }
        g = new Digraph(count);
        for (String hyponym : hyponyms) {
            String[] diffparts = hyponym.split(",");
            int vertice = Integer.parseInt(diffparts[0]);
            for (int i = 1; i < diffparts.length; i = i + 1) {
                g.addEdge(vertice, Integer.parseInt(diffparts[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounset.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounset;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> returnstring = new TreeSet<String>();
        Set<Integer> wordint = new TreeSet<Integer>();
        for (int key : maps.get(word)) {
            wordint.add(key);
        }
        Set<Integer> result = GraphHelper.descendants(g, wordint); 
        for (int number : result) {
            for (String phrase : reverse.get(number)) {
                returnstring.add(phrase);
            }
        }
        return returnstring;   
    }
}
