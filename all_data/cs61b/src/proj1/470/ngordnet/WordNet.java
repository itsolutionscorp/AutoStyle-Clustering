package ngordnet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Set<String> allNouns = new HashSet<String>(); // keep track of what
                                                          // nouns
    // are in the synsetFile
    private Map<HashSet<String>, 
        HashSet<Integer>> word2Num = 
            new HashMap<HashSet<String>, HashSet<Integer>>();
    private Map<Integer, HashSet<String>> num2Word = new HashMap<Integer, HashSet<String>>();
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        int c = 0; // see how many synsets are there: for constructing digraph
        /** Generate a set of noun **/
        while (synset.hasNextLine()) {
            HashSet<String> key = new HashSet<String>(); // for the map
            String line = synset.readLine();
            String[] tokens = line.split(",");
            String[] words = tokens[1].split(" ");
            for (String word : words) {
                allNouns.add(word);
                key.add(word);
            }
            
            // Putting stuff into maps, quicker access
            if (word2Num.keySet().contains(key)) {
                HashSet<Integer> oldNumbers = word2Num.get(key);
                oldNumbers.add(c);
                word2Num.put(key, oldNumbers);

            } else {
                HashSet<Integer> numbers = new HashSet<Integer>();
                numbers.add(c);
                word2Num.put(key, numbers);
            }

            num2Word.put(c, key);
            c++;
        }

        /** Generate a graph relating all the sets **/
        g = new Digraph(c);
        while (hyponym.hasNextLine()) {
            String line = hyponym.readLine();
            String[] tokens = line.split(",");
            int x = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                g.addEdge(x, Integer.parseInt(tokens[i]));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> allHyponyms = new HashSet<String>();
        Set<HashSet<String>> keyset = word2Num.keySet();

        for (HashSet<String> keys : keyset) {
            if (keys.contains(word)) {
                Set<Integer> search = new TreeSet<Integer>();
                for (Integer k : word2Num.get(keys)) {
                    search.add(k);
                }
                Set<Integer> reach = GraphHelper.descendants(g, search);

                /** Add all hyponyms into the return set **/
                for (Integer i : reach) {
                    HashSet<String> target = num2Word.get(i);

                    for (String wordInSet : target) {
                        allHyponyms.add(wordInSet);
                    }
                }

                allHyponyms.add(word);
            }
        }

        return allHyponyms;
    }
}
