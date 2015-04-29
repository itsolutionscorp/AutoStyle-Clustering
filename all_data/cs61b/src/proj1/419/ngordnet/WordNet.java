package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/** 
 * Class designed to take in a file of synsets and a file of hyponyms.
 * This class allows access to the whole set of words that are nouns, if a word
 * is a noun, or the set of hyponyms of a word.
 * @author Ryan Row
 */
public class WordNet {
    private HashMap<Integer, LinkedList<String>> vertexToString;
    private HashMap<String, LinkedList<Integer>> stringToVertex;
    private Digraph graph;
    private Set<String> nounSet = new TreeSet<String>();

    /** 
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     * 
     * @param synsetFilename - Synset file
     * @param hyponymFilename - Hyponym file
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        vertexToString = new HashMap<Integer, LinkedList<String>>();
        stringToVertex = new HashMap<String, LinkedList<Integer>>();
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        Integer vertex = 0;
        while (synset.hasNextLine()) {
            String s = synset.readLine();
            String[] newS = s.split(",");
            LinkedList<String> list = new LinkedList<String>();
            String[] words = newS[1].split(" ");
            vertex = Integer.parseInt(newS[0]);
            for (String word : words) {
                LinkedList<Integer> intList = new LinkedList<Integer>();
                intList.add(vertex);
                nounSet.add(word);
                if (stringToVertex.containsKey(word)) {
                    stringToVertex.get(word).add(vertex);
                } else {
                    stringToVertex.put(word, intList);
                }
                list.add(word);
            }
            vertexToString.put(vertex, list);
        }
        makeGraph(hyponym, vertex + 1);
    }

    /** 
     * This method is called from the constructor to create the DiGraph
     * 
     * @param hyponym - The hyponymFile
     * @param vertex - The number of vertices of the DiGraph
     */
    private void makeGraph(In hyponym, int vertex) {
        graph = new Digraph(vertex);

        while (hyponym.hasNextLine()) {
            String ints = hyponym.readLine();
            String[] newInts = ints.split(",");
            Integer key = Integer.parseInt(newInts[0]);
            for (int i = 1; i < newInts.length; i++) {
                graph.addEdge(key, Integer.parseInt(newInts[i]));
            }
        }
    }

    /**
     * Returns true if NOUN is a word in some synset.
     * 
     * @param noun - A word
     * @return - True if the word is a noun otherwise, false
     */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /** Returns the set of all nouns.
     * 
     * @return - The set of all words that are nouns
     */
    public Set<String> nouns() {
        return nounSet;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     * 
     * @param word - A word 
     * @return - The set of all hyponyms of the word
     */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new TreeSet<String>();
        Set<Integer> wordVertices = new TreeSet<Integer>();
        LinkedList vertices = stringToVertex.get(word);
        for (Object x : vertices) {
            wordVertices.add((Integer) x);
        }
        wordVertices = GraphHelper.descendants(graph, wordVertices);
        for (Integer y : wordVertices) {
            LinkedList<String> temp = vertexToString.get(y);
            for (String noun : temp) {
                hyponyms.add(noun);
            }
        }
        return hyponyms;
    }
}
