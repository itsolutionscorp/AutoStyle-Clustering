package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class WordNet {
    private Digraph wordTree;
    private HashSet<String> wordSet;
    private HashMap<Integer, String> idPairs;

    /* Constructor for a new WordNet. 
     * @param synsetFile the file containing all synsets.
     * @param hyponymFile is the file contain all hyponyms.
     * Files are parsed and words are put into a HashSet for quick
     * access for isNoun method. Id, String pairs are stored in a 
     * Hashmap for relation and access for placing hyponyms when
     * constructing the Digraph wordTree */
    public WordNet(String synsetFile, String hyponymFile) {
        In synsetIn = new In(synsetFile);
        wordSet = new HashSet<String>();
        idPairs = new HashMap<Integer, String>();
        String file = synsetIn.readAll();
        synsetIn.close();
        String[] lines = file.split("\n");
        for (String i: lines) {
            String[] tokens = i.split(",");
            int id = Integer.parseInt(tokens[0]);
            String words = tokens[1];
            if (words.contains(" ")) {
                String[] split = words.split(" ");
                for (String x: split) {
                    wordSet.add(x);
                }
            } else {
                wordSet.add(words);
            }
            idPairs.put(id, words);
            String gloss = tokens[2]; //currently not doing anything with gloss
        }
        int sizeDigraph = idPairs.size();
        wordTree = new Digraph(sizeDigraph);
        In hyponymIn = new In(hyponymFile);
        String hyponyms = hyponymIn.readAll();
        hyponymIn.close();
        String[] hyponymLines = hyponyms.split("\n");
        for (String i: hyponymLines) {
            String[] values = i.split(",");
            int hypernym = Integer.parseInt(values[0]);
            for (int j = 1; j < values.length; j++) {
                int hyponym = Integer.parseInt(values[j]);
                wordTree.addEdge(hypernym, hyponym);
            }
        }
    }

    /* Returns true if a word is in the wordSet.
     * @param noun is the word to be checked. 
     * @return a boolean determined by noun's membership in wordSet. */
    public boolean isNoun(String noun) {
        return wordSet.contains(noun);
    }

    /* Returns a set of all nouns in the wordTree.
     * @return a set of all nouns. */
    public Set<String> nouns() {
        return wordSet;
    }

    /* A method which returns a set of all hyponyms of a word including the
     * word itself.
     * @param word is the word to get hyponyms of.
     * @return is a set of all hyponyms including the word itself. */
    public Set<String> hyponyms(String word) {
        Set<String> result = new HashSet<String>();
        result.add(word);
        Set<Integer> hyponymsToGet = getKeysByValue(idPairs, word);
        Set<Integer> reachable = GraphHelper.descendants(wordTree, hyponymsToGet);
        for (int i: reachable) {
            String hyponym = idPairs.get(i);
            if (hyponym.contains(" ")) {
                String[] split = hyponym.split(" ");
                for (String s: split) {
                    result.add(s);
                }
            } else {
                result.add(hyponym);
            }
        }
        return result;
    }

    /* Helper method to get the key of a value from a map.
     * @param map is the map to search.
     * @param value is the value one wants the key of.
     * @return is a set of corresponding keys. */
    private static Set<Integer> getKeysByValue(Map<Integer, String> map, String value) {
        Set<Integer> keys = new HashSet<Integer>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String[] line = entry.getValue().split(" ");
            for (String s: line) {
                if (s.equals(value)) {
                    keys.add(entry.getKey());
                }

            }
        }
        return keys;
    }
  
}
