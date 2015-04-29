package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import java.util.Set;

public class WordNet {
    
    private Digraph digraph;
    private HashMap<Integer, Set<String>> synsets;
    private HashMap<Integer, String> definitions;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME 
     * @param synsetFilename This is the file of all synsets with their IDs.
     * @param hyponymFilename This is the file with each synset ID and IDs
     * of synsets of hyponyms of that synset.
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetRead = new In(synsetFilename);
        In hyponymRead = new In(hyponymFilename);
        String[] synsetList = synsetRead.readAllLines();
        String[] hyponymList = hyponymRead.readAllLines();
        synsets = new HashMap<Integer, Set<String>>();
        definitions = new HashMap<Integer, String>();
        digraph = new Digraph(synsetList.length);
        for (String line: synsetList) {
            String[] lineList = line.split(",");
            int key = Integer.parseInt(lineList[0]);
            String[] words = lineList[1].split(" ");
            Set<String> wordSet = new TreeSet<String>();
            for (String word: words) {
                wordSet.add(word);
            }
            synsets.put(key, wordSet);
            definitions.put(key, lineList[2]);
        }
        for (String hyponym: hyponymList) {
            String[] coordinates = hyponym.split(",");
            int synsetID = Integer.parseInt(coordinates[0]);
            for (int i = 1; i < coordinates.length; i++) {
                int key = Integer.parseInt(coordinates[i]);
                digraph.addEdge(synsetID , key);
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. 
     * @param word we check for
     * @return true or false depending on whether the word is a noun.
     */
    public boolean isNoun(String noun) {
        for (Set<String> set: synsets.values()) {
            if (set.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /** Returns the set of all nouns. 
     * @return Set of strings containing all nouns.
     */
    public Set<String> nouns() {
        Set<String> x = new TreeSet<String>();
        for (Set<String> set: synsets.values()) {
            x.addAll(set);
        }
        return x;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. Do not include hyponyms of synonyms.
      * @param word This is the word whose hyponyms we want.
      * @return Set<String> of hyponyms of the word.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> ints = new TreeSet<Integer>();
        for (Integer key: synsets.keySet()) {
            Set<String> set = synsets.get(key);
            if (set.contains(word)) {
                ints.add(key);
            }
        }
        Set<Integer> children = GraphHelper.descendants(digraph, ints);
        Set<String> hyp = new TreeSet<String>();
        for (Integer ckey: children) {
            hyp.addAll(synsets.get(ckey));
        }
        return hyp;
    }
}
