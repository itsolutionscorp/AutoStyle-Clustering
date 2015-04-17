package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;

/* An object that stores the WordNet graph in a manner useful for extracting all hyponyms of a word.
 * @author Nick Landolfi
 */
public class WordNet {
    private Map<Integer, Set<String>> synsets;
    private Map<String, Set<Integer>> words;
    private Digraph hyponymGraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsets = new HashMap<Integer, Set<String>>();
        this.words = new HashMap<String, Set<Integer>>();

        processSynsetFile(synsetFilename);
        processHyponymFile(hyponymFilename);
    }

    private void processSynsetFile(String name) {
        In file = new In(name);
        for (String line : file.readAllLines()) {
            String[] splits = line.split(",");
            Integer synid = Integer.parseInt(splits[0]);
            for (String word : splits[1].split(" ")) {
                addWord(word, synid);
            }
        }

        doneProcessingSynsets();
    }

    private boolean doneSynsets;
    private void doneProcessingSynsets() {
        this.hyponymGraph = new Digraph(this.synsets.keySet().size());
        doneSynsets = true;
    }

    private void processHyponymFile(String name) {
        if (!doneSynsets) {
            throw new RuntimeException("Synsets not yet loaded");
        }

        In file = new In(name);
        for (String line : file.readAllLines()) {
            String[] splits = line.split(",");
            Integer base = Integer.parseInt(splits[0]);
            for (String sid : splits) {
                Integer other = Integer.parseInt(sid);
                if (base != other) {
                    addHyponym(base, other);
                }
            }
        }
    }

    private void addWord(String word, Integer synid) {
        Set<String> s;
        Set<Integer> i;

        if (synsets.containsKey(synid)) {
            s = synsets.get(synid);
        } else {
            s =  new TreeSet<String>();
        }


        if (words.containsKey(word)) {
            i = words.get(word);
        } else {
            i = new TreeSet<Integer>();
        }

        s.add(word);
        i.add(synid);

        synsets.put(synid, s);
        words.put(word, i);
    }

    private void addHyponym(Integer base, Integer other) {
        hyponymGraph.addEdge(base, other);
    }

    private void addHyponyms(Integer base, Set<Integer> others) {
        for (Integer other : others) {
            addHyponym(base, other);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return words.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return words.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> s = new TreeSet<String>();

        if (!isNoun(word)) {
            return s; //empty
        }

        Set<Integer> synids = words.get(word);
        s.addAll(wordsForSynsets(synids));
        s.addAll(wordsForSynsets(GraphHelper.descendants(hyponymGraph, synids)));

        return s;
    }

    private Set<String> wordsForSynsets(Set<Integer> synids) {
        Set<String> s = new TreeSet<String>();

        for (Integer id : synids) {
            s.addAll(synsets.get(id));
        }

        return s;
    }

    private Set<String> wordsForSynset(Integer synid) {
        return synsets.get(synid);
    }
}
