package ngordnet;

/**
 * @author Matthew Mussomele
 *
 *
 * A class used to represent a WordNet of the English language.
 *
 * From spec:
 *
 * WordNet is a semantic lexicon for the English language that is used extensively by 
 * computational linguists and cognitive scientists; for example, it was a key component 
 * in IBM's Watson. WordNet groups words into sets of synonyms called synsets and describes
 * semantic relationships between them. One such relationship is the is-a relationship, 
 * which connects a hyponym (more specific synset) to a hypernym (more general synset). 
 * For example, "change" is a hypernym of "demotion", since "demotion" is-a (type of) 
 * "change". "change" is in turn a hyponym of "action", since "change" is-a (type of) 
 * "action".
 */



/**
 * Imports:
 *     -Princeton's Digraph class for representing edges in our WordNet
 *     -Princeton's In class for reading data in from files
 *     -Java's built in Set interface class for storing synsets
 *     -Java's built in TreeSet class for storing synsets
 *     -Java's built in ArrayList class for assisting in the file reading
 *     -Java's built in HashMap class for getting word's synset IDs quickly
 */
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;

public class WordNet {

    private static final int DEFAULT_HASH_SIZE = 2048;
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private int synsetCount;
    private Digraph wordRelations;
    private ArrayList<Set<String>> synsets;
    private HashMap<String, ArrayList<Integer>> words;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetCount = parseSynsets(synsetFilename);
        parseHyponyms(hyponymFilename);
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
        Set<Integer> ids = new TreeSet<Integer>(words.get(word));
        Set<Integer> decs = GraphHelper.descendants(wordRelations, ids);
        Set<String> hyps = new TreeSet<String>();
        for (Integer i: ids) {
            hyps.addAll(synsets.get(i));
        }
        for (Integer i: decs) {
            hyps.addAll(synsets.get(i));
        }
        return hyps;
    }


    private int parseSynsets(String synsetFilename) {
        In reader = new In(synsetFilename);
        words = new HashMap<String, ArrayList<Integer>>(DEFAULT_HASH_SIZE);
        synsets = new ArrayList<Set<String>>();
        String[] lines = reader.readAllLines();      
        for (String line: lines) {
            String[] lineData = line.split(COMMA);
            Set<String> synsetWords = new TreeSet<String>();
            int synsetID = Integer.parseInt(lineData[0]);
            for (String word: lineData[1].split(SPACE)) {
                synsetWords.add(word);
                if (!words.containsKey(word)) {
                    words.put(word, new ArrayList<Integer>());
                }
                words.get(word).add(synsetID);
            }
            synsets.add(synsetID, synsetWords);
        }
        reader.close();
        return lines.length;
    } 

    private void parseHyponyms(String hyponymFilename) {
        In reader = new In(hyponymFilename);
        String[] lines = reader.readAllLines();
        wordRelations = new Digraph(synsetCount);
        for (String line: lines) {
            String[] lineData = line.split(COMMA);
            int id = Integer.parseInt(lineData[0]);
            for (int i = 1; i < lineData.length; i += 1) {
                wordRelations.addEdge(id, Integer.parseInt(lineData[i]));
            }
        }
        reader.close();
    }

}
