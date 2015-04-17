package ngordnet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/** An object that stores the WordNet graph in a manner useful for extracting all hyponyms of a
 *  word.
 *
 *  @author Sidd Karamcheti
 */
public class WordNet {
    private HashMap<Integer, Set<String>> synset;
    private HashMap<String, Set<Integer>> words;
    private Digraph hyponymMap;

    /** Creates a WordNet using files SYNSETFILENAME and HYPONYMFILENAME
     *
     *  @param  synsetFilename   Filepath for input synsets.
     *  @param  hyponymFilename  Filepath for input hyponyms.
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        this.synset = new HashMap<>();
        this.words = new HashMap<>();
        this.populateSynset(synsetIn);

        In hyponymIn = new In(hyponymFilename);
        this.hyponymMap = new Digraph(synset.size());
        this.populateDigraph(hyponymIn);
    }

    /** Reads through synsetIn line by line, and populates the underlying HashMap
     *
     *  @param  synsetIn  In object containing contents of synset file.
     */
    private void populateSynset(In synsetIn) {
        while (synsetIn.hasNextLine()) {
            String line = synsetIn.readLine();
            String[] tokens = line.split(",");

            Set<String> synonyms = new HashSet<>();
            int index = Integer.parseInt(tokens[0]);
            for (String s : tokens[1].split(" ")) {
                synonyms.add(s);
                if (this.words.containsKey(s)) {
                    this.words.get(s).add(index);
                } else {
                    Set<Integer> lst = new HashSet<>();
                    lst.add(index);
                    this.words.put(s, lst);
                }
            }
            this.synset.put(index, synonyms);
        }
    }

    /** Reads through hyponymIn line by line, and populates the underlying HashMap
     *
     *  @param  hyponymIn  In object containing contents of synset file.
     */
    private void populateDigraph(In hyponymIn) {
        while (hyponymIn.hasNextLine()) {
            String line = hyponymIn.readLine();
            String[] nodes = line.split(",");
            int root = Integer.parseInt(nodes[0]);
            for (int i = 1; i < nodes.length; i++) {
                this.hyponymMap.addEdge(root, Integer.parseInt(nodes[i]));
            }
        }
    }

    /** Returns true if NOUN is a word in some synset.
     *
     *  @param  noun  Input string to check across synsets.
     *  @return       Returns boolean value whether the noun exists in synset or not.
     */
    public boolean isNoun(String noun) {
        return this.words.containsKey(noun);
    }

    /** Returns the set of all nouns.
     *
     *  @return       Returns the set of all nouns across all synsets.
     */
    public Set<String> nouns() {
        return this.words.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     *  WORD. If WORD belongs to multiple synsets, return all hyponyms of
     *  all of these synsets. See http://goo.gl/EGLoys for an example.
     *  Do not include hyponyms of synonyms.
     *
     *  Adds synonyms first, then all hyponyms using GraphHelper.
     *
     *  @param  word  Hypernym string to find all hyponyms for.
     *  @return       Return set of all hyponyms of word.
     */
    public Set<String> hyponyms(String word) {
        Set<String> wordHyponyms = new HashSet<>();
        if (this.isNoun(word)) {
            /* Add all synonyms of word first. */
            Set<Integer> synIndices = this.words.get(word);
            for (int index : synIndices) {
                for (String s : this.synset.get(index)) {
                    if (!s.equals(word)) {
                        wordHyponyms.add(s);
                    }
                }
            }
            /* Now add all hyponyms of word, using GraphHelper. */
            Set<Integer> hyponymIndices = GraphHelper.descendants(this.hyponymMap, synIndices);
            for (int index : hyponymIndices) {
                for (String s : this.synset.get(index)) {
                    wordHyponyms.add(s);
                }
            }
        }
        return wordHyponyms;
    }
}
