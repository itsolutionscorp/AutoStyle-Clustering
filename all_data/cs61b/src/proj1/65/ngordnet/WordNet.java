package ngordnet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import edu.princeton.cs.algs4.Digraph;

/**
 * The <tt>WordNet</tt> organizes words into sets of synonyms with hyponym
 * relationships
 * 
 * @author Joseph Chen
 * 
 */
public class WordNet {

    /**
     * The maximum capacity of the list of <tt>WordSet</tt>s in this
     * <tt>WordNet</tt>
     */
    private final int maxSize;

    /**
     * A list of <tt>WordSet</tt> objects storing all data
     */
    private final WordSet[] wordSets;

    /**
     * A mapping of each word against its occurence over <tt>WordNet</tt>,
     * stored as a list of node ids of each <tt>WordSet</tt> corresponding to
     * the word
     */
    private final Map<String, Set<Integer>> wordList;

    /**
     * A <i>directed graph</i> storing hyponym relationships between each
     * <tt>WordSet</tt>
     */
    private Digraph digraph;

    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     * 
     * @param synsetFilename
     *            Name of file containing all synonym sets
     * @param hyponymFilename
     *            Name of file containing all hyponym relationships
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Count number of nodes in this synset file
        int nodeCount = ResourceHandler.getLineCount(synsetFilename);

        // Initialize WordSet
        maxSize = nodeCount;
        wordSets = new WordSet[nodeCount];
        wordList = new TreeMap<String, Set<Integer>>();

        // Initialize digraph with number of nodes
        digraph = new Digraph(nodeCount);

        // Read all words from synset and store them in WordSet
        Scanner scan = new Scanner(ResourceHandler.open(synsetFilename));

        try {
            while (scan.hasNextLine()) {
                String[] synSet = scan.nextLine().split(",", 3);
                int id = Integer.parseInt(synSet[0]);
                Set<String> synonyms = new HashSet<String>();
                synonyms.addAll(Arrays.asList(synSet[1].split(" ")));
                String definition = synSet[2];
                new WordSet(id, synonyms, definition);
            }
        } catch (NullPointerException | NumberFormatException
                | IndexOutOfBoundsException e) {
            System.err.println("Bad synset file: " + synsetFilename);
        } finally {
            scan.close();
        }

        // Read all hyponym relationships and add to digraph
        scan = new Scanner(ResourceHandler.open(hyponymFilename));

        try {
            while (scan.hasNextLine()) {
                String[] hyponym = scan.nextLine().split(",");
                for (int i = 1; i < hyponym.length; i++) {
                    digraph.addEdge(Integer.parseInt(hyponym[0]),
                            Integer.parseInt(hyponym[i]));
                }
            }
        } catch (NullPointerException | NumberFormatException
                | IndexOutOfBoundsException e) {
            System.err.println("Bad hyponym file: " + hyponymFilename);
        } finally {
            scan.close();
        }
    }

    /**
     * Returns true if NOUN is a word in some synset.
     * 
     * @param noun
     *            A word to check if exists in this synset.
     * @return Whether <tt>noun</tt> is in this <tt>WordNet</tt>
     */
    public boolean isNoun(String noun) {
        return get(noun) != null;
    }

    /**
     * Returns the set of all nouns.
     * 
     * @return A set of all words in this <tt>WordNet</tt>
     */
    public Set<String> nouns() {
        return getAll();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     * 
     * @param word
     *            The word for which to search available hyponyms and synonyms
     * @return A set of all words that are hyponyms or synonyms of <tt>word</tt>
     *         in this <tt>WordNet</tt>
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIDs = get(word);
        Set<String> hyponyms = new HashSet<String>();
        for (int id : GraphHelper.descendants(digraph, synsetIDs)) {
            hyponyms.addAll(get(id).getSynonyms());
        }
        return hyponyms;
    }

    /* ================= */
    /* |WORDSET METHODS| */
    /* ================= */

    /**
     * Retrieve all word in this <tt>WordSet</tt>
     * 
     * @return Set of all words
     */
    private Set<String> getAll() {
        return wordList.keySet();
    }

    /**
     * Retrieve all <tt>id</tt>s which contain the word
     * 
     * @param word
     *            A word to search for
     * @return A list of <tt>id</tt>s
     */
    private Set<Integer> get(String word) {
        if (wordList.containsKey(word)) {
            return wordList.get(word);
        }
        return null;
    }

    /**
     * Retrieve a <tt>WordSet</tt> in slot <tt>id</tt>
     * 
     * @param id
     *            The slot of a <tt>WordSet</tt>
     * @return The <tt>WordSet</tt> at slot <tt>id</tt>
     */
    private WordSet get(int id) {
        if (id < 0 || id >= maxSize) {
            return null;
        }
        return wordSets[id];
    }

    /**
     * A node of <tt>WordNet</tt> which stores information on synonym sets
     * regarding each <tt>WordSet</tt>'s <b>id</b> in the <tt>WordNet</tt>,
     * <b>synonym</b> set and <b>definition</b>
     * 
     * @author Joseph Chen
     * 
     */
    private class WordSet {

        private int id;
        private Set<String> synonyms;
        private String definition;

        /**
         * Constructs a <tt>WordSet</tt> to store data for a set of synonymous
         * words
         * 
         * @param nodeID
         *            The unique id of this node
         * @param words
         *            A list of synonyms
         * @param def
         *            Definition of the words in this node
         */
        private WordSet(int nodeID, Set<String> words, String def) {
            if (nodeID < 0 || nodeID > maxSize || wordSets[nodeID] != null) {
                throw new IllegalArgumentException("WordSet " + nodeID
                        + " is already occupied by " + wordSets[nodeID]);
            } else {
                this.id = nodeID;
                this.synonyms = words;
                this.definition = def;

                wordSets[nodeID] = this;

                for (String word : words) {
                    if (wordList.containsKey(word)) {
                        wordList.get(word).add(nodeID);
                    } else {
                        Set<Integer> ids = new HashSet<Integer>();
                        ids.add(nodeID);
                        wordList.put(word, ids);
                    }
                }
            }
        }

        /**
         * Gets the id of this <tt>WordSet</tt>
         * 
         * @return The id of this node
         */
        private int getId() {
            return id;
        }

        /**
         * Gets the set of synonyms of this <tt>WordSet</tt>
         * 
         * @return The synonyms at this node
         */
        private Set<String> getSynonyms() {
            return synonyms;
        }

        /**
         * Defines the synonyms of this <tt>WordSet</tt>
         * 
         * @return The definition of this node
         */
        private String define() {
            return definition;
        }
    }
}
