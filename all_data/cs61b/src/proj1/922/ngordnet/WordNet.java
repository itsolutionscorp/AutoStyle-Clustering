package ngordnet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.io.IOException;
import edu.princeton.cs.algs4.Digraph;

/**
 * The WordNet class. Used to represent the Hyponym and Synomym structure of WordNet
 * @author Oliver Habryka
 */
public class WordNet {
    private Map<Integer, List<String>> synSet;
    private Map<String, Set<Integer>> wordSet;
    private Digraph hypSet; 

    /**
     * Constructor for WordNet. Sets up most of the data structure and reads in the files.
     * @param synFilename location of the Synonym File.
     * @param hypFilename location of the Hyponym File.
     */
    public WordNet(String synFilename, String hypFilename) {
        try {
            synSet = new HashMap<Integer, List<String>>();
            wordSet = new HashMap<String, Set<Integer>>();

            {
                CsvReader t = new CsvReader(synFilename);
                while (t.readRecord()) {
                    int id = Integer.parseInt(t.get(0));
                    List<String> tlist = Arrays.asList(t.get(1).split(" "));
                    ArrayList<String> words = new ArrayList<String>(tlist);
                    synSet.put(id, words);
                    for (String w : words) {
                        if (wordSet.get(w) == null) {
                            wordSet.put(w, new HashSet<Integer>());
                        }
                        wordSet.get(w).add(id);
                    }
                }
            }

            hypSet = new Digraph(synSet.size());
            {
                CsvReader t = new CsvReader(hypFilename);
                while (t.readRecord()) {
                    for (int i = 1; i < t.getColumnCount(); i++) {
                        hypSet.addEdge(Integer.parseInt(t.get(0)), Integer.parseInt(t.get(i)));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
             /* Ignore IOException. */
             /* Ignore FileNotFoundException */
        }
    }

    /**
     * Returns true if word is in the database of words
     * @param noun noun to be checked
     * @return true if word is in the database of words
     */
    public boolean isNoun(String noun) {
        return wordSet.containsKey(noun);
    }

    /**
     * returns the set of nouns
     * @return set of nouns
     */
    public Set<String> nouns() {
        return wordSet.keySet();
    }

    /**
     * Returns the synomyns AND hyponyms for a word (descending down the tree)
     * @param word word to be checkes
     * @return Set of synomyms and hyponyms
     */
    public Set<String> hyponyms(String word) {
        Set<String> t = new HashSet<String>();
        if (wordSet.get(word) != null) {
            for (int descendant : GraphHelper.descendants(hypSet, wordSet.get(word))) {
                for (String w : synSet.get(descendant)) {
                    t.add(w);
                }
            }
        }
        return t;
    }
}
