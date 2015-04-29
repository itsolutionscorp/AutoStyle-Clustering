package ngordnet;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Set<String> nounSet;
    private Set<Integer> ids;
    private Digraph wordNetDigraph;
    private HashMap<Integer, Set<String>> idToSynset; // maps an ID to its
    // synset
    private HashMap<String, Set<Integer>> wordToIDs; // maps a word to all its
    // IDs
    private String synsetFile, hyponymFile;
    private int nIDs;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = synsetFilename.substring(0, 2) + synsetFilename.substring(2);
        hyponymFile = hyponymFilename.substring(0, 2) + hyponymFilename.substring(2);
        nIDs = 0;
        nounSet = new HashSet<String>();
        ids = new HashSet<Integer>();
        idToSynset = new HashMap<Integer, Set<String>>();
        wordToIDs = new HashMap<String, Set<Integer>>();

        In synsetObj = new In(synsetFile);
        int id = 0, firstComma = 0, nextComma = 0;
        String synsets;
        String[] synset;
        String[] lines = synsetObj.readAllLines();

        for (String line : lines) {
            // line = synsetObj.readLine();
            firstComma = line.indexOf(',');
            nextComma = line.indexOf(',', firstComma + 1);
            id = Integer.parseInt(line.substring(0, firstComma));
            synsets = line.substring(firstComma + 1, nextComma);
            synset = synsets.split(" ");
            Set<String> mappingSynset = new TreeSet<String>();
            for (String noun : synset) {
                nounSet.add(noun);
                mappingSynset.add(noun);
                if (wordToIDs.containsKey(noun)) {
                    wordToIDs.get(noun).add(id);
                } else {
                    Set<Integer> nounIDs = new TreeSet<Integer>();
                    nounIDs.add(id);
                    wordToIDs.put(noun, nounIDs);
                }
            }
            idToSynset.put(id, mappingSynset);
            nIDs += 1;
            ids.add(id);
        }

        wordNetDigraph = new Digraph(nIDs + 1);

        In hyponymObj = new In(hyponymFile);
        int u, v;
        String[] vertices;

        lines = hyponymObj.readAllLines();

        for (String line2 : lines) {
            // line = hyponymObj.readLine();
            vertices = line2.split(",");
            u = Integer.parseInt(vertices[0]);
            for (String vertex : vertices) {
                if (!vertex.equals(vertices[0])) {
                    v = Integer.parseInt(vertex);
                    wordNetDigraph.addEdge(u, v);
                }
            }
        }
    }

    public boolean isNoun(java.lang.String noun) {
        return nounSet.contains(noun);
    }

    public Set<String> nouns() {
        return nounSet;
    }

    private Set<Integer> getWordIDs(String word) {
        // Returns all the IDs of a given word
        /*
         * Set<Integer> wordIDs = new HashSet<Integer>(); int firstComma = 0,
         * nextComma = 0, id = 0; In synsetObj = new In(synsetFile); String
         * line, synsets; String[] synset;
         *
         * while (synsetObj.hasNextLine()) { line = synsetObj.readLine();
         * firstComma = line.indexOf(','); nextComma = line.indexOf(',',
         * firstComma + 1); id = Integer.parseInt(line.substring(firstComma));
         * synsets = line.substring(firstComma + 1, nextComma); synset =
         * synsets.split(" "); for (String currentWord : synset) { if
         * (currentWord.equals(word)) { wordIDs.add(id); } } }
         *
         * return wordIDs;
         */
        return wordToIDs.get(word);
    }

    private Set<String> getIDwords(int wordID) {
        // Returns all the words for a given ID
        /*
         * Set<String> idWords = new HashSet<String>(); int firstComma = 0,
         * nextComma = 0, id = 0; In synsetObj = new In(synsetFile); String
         * line, synsets; String[] synset;
         *
         * while (synsetObj.hasNextLine()) { line = synsetObj.readLine();
         * firstComma = line.indexOf(','); nextComma = line.indexOf(',',
         * firstComma + 1); id = Integer.parseInt(line.substring(firstComma));
         * if (id == wordID) { synsets = line.substring(firstComma + 1,
         * nextComma); synset = synsets.split(" "); for (String currentWord :
         * synset) { idWords.add(currentWord); } } }
         *
         * return idWords;
         */
        return idToSynset.get(wordID);
    }

    public Set<String> hyponyms(String word) {
        // Returns the set of all hyponyms of WORD as well as all synonyms of
        // WORD.
        // If WORD belongs to multiple synsets, return all hyponyms of all of
        // these synsets.
        // See http://goo.gl/EGLoys for an example. Do not include hyponyms of
        // synonyms.
        if (!isNoun(word)) {
            return new TreeSet<String>();
        }
        Set<String> hyponymSet = new TreeSet<String>();
        Set<Integer> wordIDs = getWordIDs(word);
        Set<Integer> descendantIDs = GraphHelper.descendants(wordNetDigraph, wordIDs);

        for (int id : descendantIDs) {
            hyponymSet.addAll(getIDwords(id));
        }

        return hyponymSet;
    }
}
