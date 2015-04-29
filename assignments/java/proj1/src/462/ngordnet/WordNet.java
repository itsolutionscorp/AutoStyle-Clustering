package ngordnet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


/** Implements WordNet.
 *  @author Tejomay Gadgil
 */

public class WordNet {
    private Digraph graph;
    private String[][] synsetMap;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        String[] synsetLines = synsetIn.readAllLines();
        String[] hyponymLines = hyponymIn.readAllLines();
        graph = new Digraph(synsetLines.length);
        synsetMap = new String[synsetLines.length][];
        int counter = 0;
        for (String line : synsetLines) {
            String[] lineArray = line.split(",");
            synsetMap[counter] = lineArray[1].split(" ");
            counter = counter + 1;
        }
        for (String rel : hyponymLines) {
            String[] relArray = rel.split(",");
            if (relArray.length > 1) {
                for (int i = 1; i < relArray.length; i++) {
                    graph.addEdge(Integer.parseInt(relArray[0]), Integer.parseInt(relArray[i]));
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String[] line : synsetMap) {
            for (String word : line) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (String[] line : synsetMap) {
            for (String word : line) {
                nounSet.add(word);
            }
        }
        return nounSet;
    }

    /* Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
    */
      
    public Set<String> hyponyms(String word) {
        Set<String> hyponymsSet = new HashSet<String>();
        Set<Integer> hyponymsIndex = new HashSet<Integer>();
        int counter = 0;
        for (String[] line : synsetMap) {
            if (Arrays.asList(line).contains(word)) {
                hyponymsIndex.add(counter);
                for (String synonym : line) {
                    hyponymsSet.add(synonym);
                }
            }
            counter = counter + 1;
        }
        for (int hypoInd : GraphHelper.descendants(graph, hyponymsIndex)) {
            for (String hyponym : synsetMap[hypoInd]) {
                hyponymsSet.add(hyponym);
            }
        }
        return hyponymsSet;
    }
}
