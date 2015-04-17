package ngordnet;
import edu.princeton.cs.introcs.In;

import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;

public class WordNet {
//    public Hashtable<Integer, String[]> idToWord;
//    public Hashtable<String, HashSet<Integer>> wordToID;
//    public Digraph graph;
    
    private Hashtable<Integer, String[]> idToWord;
    private Hashtable<String, HashSet<Integer>> wordToID;
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);

        /* Fill idToWord and wordToID */
        int numOfVertices = 0;
        idToWord = new Hashtable<Integer, String[]>();
        wordToID = new Hashtable<String, HashSet<Integer>>();
        while (!synset.isEmpty()) {
            String line = synset.readLine();
            String[] array = line.split(",");
            Integer id = Integer.parseInt(array[0]);
            String[] words = array[1].split(" ");
            idToWord.put(id, words); // Fill idToWord

            for (String word: words) {
                if (!wordToID.containsKey(word)) {
                    HashSet<Integer> ids = new HashSet<Integer>();
                    ids.add(id);
                    wordToID.put(word, ids);        // Fill wordToID
                } else {
                    HashSet<Integer> ids = wordToID.get(word);
                    ids.add(id);
                }
            }
            numOfVertices += 1;
        }
        
        /* Fill graph */
        graph = new Digraph(numOfVertices);
        while (!hyponym.isEmpty()) {
            String line = hyponym.readLine();
            String[] array = line.split(",");
            Integer initialVertex = Integer.parseInt(array[0]);
            for (int i = 1; i < array.length; i += 1) {
                Integer terminalVertex = Integer.parseInt(array[i]);
                graph.addEdge(initialVertex, terminalVertex);
//                System.out.println(initialVertex + " -> " + terminalVertex);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToID.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return new HashSet<String>(Collections.list(wordToID.keys()));
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> wordIDs = wordToID.get(word);
        Set<Integer> hyponymIDs = GraphHelper.descendants(graph, wordIDs);
//        System.out.println(hyponymIDs);
        HashSet<String> hyponymsAndSynsets = new HashSet<String>();
        for (Integer id: wordIDs) {
            for (String synonym: idToWord.get(id)) {
                hyponymsAndSynsets.add(synonym);
            }
        }
        for (Integer id: hyponymIDs) {
            for (String hyponym: idToWord.get(id)) {
                hyponymsAndSynsets.add(hyponym);
//                System.out.println(id);
            }
        }
        return hyponymsAndSynsets;
    }
}
