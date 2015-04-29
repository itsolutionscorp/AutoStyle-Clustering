package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
/*
 *  @author Henderson Wong
 */

public class WordNet {

    private Digraph wordGraph;
    private TreeMap<Integer, Set<String>> synsetMap;
    private TreeMap<Integer, String> definitionMap;
    private Set<String> allWords = new TreeSet<String>();


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsIn = new In(synsetFilename);
        In hypoIn = new In(hyponymFilename);
        synsetMap =  new TreeMap<Integer, Set<String>>();
        definitionMap =  new TreeMap<Integer, String>(); // optional
        
        while (synsIn.hasNextLine()) {
            String[] synString = synsIn.readLine().split(","); 
            String[] individuals = synString[1].split(" "); //split individual words too!
            Set<String> wordSet = new TreeSet<String>();
            for (String str : individuals) {
                wordSet.add(str);
                allWords.add(str);
            }
            synsetMap.put(Integer.parseInt(synString[0]), wordSet);
            definitionMap.put(Integer.parseInt(synString[0]), synString[2]);
        }

        wordGraph = new Digraph(synsetMap.size());

        while (hypoIn.hasNextLine()) {
            String[] hypoStr = hypoIn.readLine().split(",");
            String[] hypoValue = Arrays.copyOfRange(hypoStr, 1, hypoStr.length);
            int key = Integer.parseInt(hypoStr[0]);
            for (String val : hypoValue) {
                wordGraph.addEdge(key, Integer.parseInt(val));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allWords.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allWords;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        // Graph Helper the Blue felds in the slide
        Set<String> hyponymsOf = new TreeSet<String>();
        Set<Integer> targetInt = new TreeSet<Integer>();

        /** The following loop searches the values of synsetMap for the given word
          * If found, it adds the corresponding key to targetInt, which is later 
          * passed into GraphHelper.descendants
          * Syntax for iterating over a map is taken from
          * http://stackoverflow.com/questions/46898/iterate-over-each-entry-in-a-map1
          */
        for (Map.Entry<Integer, Set<String>> entry : synsetMap.entrySet()) {
            if (entry.getValue().contains(word)) {
                targetInt.add(entry.getKey());
            }
        }

        // We now build our return value by using values from descendants()
        Set<Integer> childrenInt = GraphHelper.descendants(wordGraph, targetInt);
        for (int key : childrenInt) {
            for (String val: synsetMap.get(key)) {
                hyponymsOf.add(val);
            }
        }
        return hyponymsOf;
    }
}
