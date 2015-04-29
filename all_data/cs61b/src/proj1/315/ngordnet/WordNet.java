package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/** 
 *  An object that stores the WordNet graph in a manner useful for extracting
 *  all hyponyms of a word.
 *  @author Jagdeep Manik 
 */

public class WordNet {
    /** Maps nouns to their indices (potentially > 1) */
    private Map<String, Set<Integer>> nounIndices; 
    /** The set of all nouns in the synset file */
    private Set<String> nounSet;
    /** Stores synsets at index i */
    private ArrayList<String> nounAtIndex;
    /** Graph of hyponyms and edges/vertices */
    private Digraph graph;

    /**
     *  Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     *  @param synsetFilename  Text file of synset declarations.
     *  @param hyponymFilename  Text file of hyponym declarations.  
     */ 
    public WordNet(String synsetFilename, String hyponymFilename) {
        nounSet = new HashSet<String>();
        nounAtIndex = new ArrayList<String>();
        nounIndices = new HashMap<String, Set<Integer>>();
        In stream = new In(synsetFilename);
        Set<Integer> indices;

        if (!stream.exists()) {
            throw new RuntimeException("Failed to stream file: " + synsetFilename);
        }

        /* Read synset, cache all (1) nouns (2) synsets */
        while (stream.hasNextLine()) {
            String line = stream.readLine();
            String[] tokens = line.split(",");
            nounAtIndex.add(tokens[1]);
            for (String noun : tokens[1].split(" ")) {
                nounSet.add(noun);
                if (nounIndices.containsKey(noun)) {
                    indices = nounIndices.get(noun);
                } else {
                    indices = new HashSet<Integer>();
                }
                indices.add(Integer.parseInt(tokens[0]));
                nounIndices.put(noun, indices);
            }
        }
        stream.close();

        /* Open hyponym file, if it exists. */
        stream = new In(hyponymFilename);
        if (!stream.exists()) {
            throw new RuntimeException("Failed to stream file: " + hyponymFilename);
        }

        String[] lines = stream.readAllLines();
        int size = 0;
        for (String line : lines) {
            String[] tokens = line.split(",");
            size += tokens.length;
        }
        stream.close();

        /* Read hyponyms, cache all in digraph */
        graph = new Digraph(size);
        for (String line : lines) {
            String[] tokens = line.split(",");
            for (int i = 1; i < tokens.length; i += 1) {
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
    }

    /**
     *  Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return null;
        }
        Set<String> hyp = new TreeSet<String>();
        Set<Integer> desc = GraphHelper.descendants(graph, nounIndices.get(word));
        for (int index : desc) {
            for (String noun : nounAtIndex.get(index).split(" ")) {
                hyp.add(noun);
            }
        }
        return hyp;
    }

    /** 
     *  Returns if a given noun is in the synset.
     */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /**
     *  Returns the set of nouns in the instance's synset.
     */
    public Set<String> nouns() {
        return nounSet;
    }
}
