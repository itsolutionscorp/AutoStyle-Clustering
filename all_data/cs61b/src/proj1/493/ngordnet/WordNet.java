package ngordnet; 
import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    private In synset; 
    private In hyponym;
    private HashMap<Integer, String> synsetMap = new HashMap<Integer, String>(); 
    private Digraph hyponymGraph;
    private HashSet<String> nounSet = new HashSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename); 

        while (!synset.isEmpty()) {
            String synsetLine = synset.readLine(); 
            String[] synsetArray = synsetLine.split(",");

            synsetMap.put(Integer.parseInt(synsetArray[0]), synsetArray[1]);

            String[] nounList = synsetArray[1].split(" ");

            for (String noun: nounList) {
                if (noun != null) {
                    nounSet.add(noun);
                }
            }
        }

        hyponymGraph = new Digraph(synsetMap.size());

        while (!hyponym.isEmpty()) {
            String hyponymLine = hyponym.readLine(); 
            String[] hyponymArray = hyponymLine.split(",");
            int size = 1; 
            while (size < hyponymArray.length) {
                hyponymGraph.addEdge(Integer.parseInt(hyponymArray[0]), 
                                Integer.parseInt(hyponymArray[size]));
                size += 1; 
            }
        }
    } 
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nounSet.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet; 
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> idSet = new HashSet<Integer>();
        for (Integer id: synsetMap.keySet()) {

            String[] hyponymSplit = synsetMap.get(id).split(" ");
            for (String noun: hyponymSplit) {
                if (noun.equals(word)) {
                    idSet.add(id);
                }
            }
        }

        Set<Integer> relation = GraphHelper.descendants(hyponymGraph, idSet);
        HashSet<String> hyponymSet = new HashSet<String>(); 
        for (Integer identifier: relation) {
            String[] hyponymList = synsetMap.get(identifier).split(" ");

            for (String noun: hyponymList) {
                if (noun != null) {
                    hyponymSet.add(noun);
                }
            }
        }
        return hyponymSet;
    }
}
