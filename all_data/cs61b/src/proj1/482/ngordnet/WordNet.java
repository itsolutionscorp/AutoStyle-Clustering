package ngordnet;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.List;
// import edu.princeton.cs.introcs.StdIn;


public class WordNet {
    private HashMap<String, List<Integer>> wordToId;
    private HashMap<Integer, List<String>> idToWord; 
    private Set<String> nounSet = new HashSet<String>();
    private int lines = 0;
    private Digraph graphOfHyponym;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        wordToId = wordToIdHM(synsetFilename);
        idToWord = idToWordHM(synsetFilename);
        graphOfHyponym = makeDigraph(hyponymFilename);
    }

    private HashMap<String, List<Integer>> wordToIdHM(String synsetFilename) {
        // Got help from a reader that I should split by a comma and then by a space instead
        // of splitting by a comma and a space in one line.
        HashMap<String, List<Integer>> results = new HashMap<String, List<Integer>>();
        In in = new In(synsetFilename);
        while (in.hasNextLine()) {
            String[] synsetList = in.readLine().split(",");
            String a = synsetList[1];
            String[] words = a.split(" ");
            for (String word : words) {
                if (results.containsKey(word)) {
                    List<Integer> ids = results.get(word);
                    ids.add(Integer.parseInt(synsetList[0]));
                    lines += 1;
                } else {
                    List<Integer> valuesList = new ArrayList<Integer>();
                    valuesList.add(Integer.parseInt(synsetList[0]));
                    results.put(word, valuesList);
                    lines += 1;     
                }
            }
        }
        return results;
    }

    
    private HashMap<Integer, List<String>> idToWordHM(String synsetFilename) {
        HashMap<Integer, List<String>> results = new HashMap<Integer, List<String>>();
        In in = new In(synsetFilename);
        while (in.hasNextLine()) {
            String[] synsetList = in.readLine().split(",");
            String a = synsetList[1];
            String[] words = a.split(" ");
            List<String> valuesList = new ArrayList<String>();
            for (String word : words) {
                if (results.containsKey(synsetList[0])) {
                    List<String> values = results.get(synsetList[0]);
                    values.add(word);
                } else {
                    int id = Integer.parseInt(synsetList[0]);
                    valuesList.add(word);
                    results.put(id, valuesList);
                }
            }
        }
        return results;
    }

    private Digraph makeDigraph(String hyponymFilename) {
        Digraph graph = new Digraph(lines);
        In in = new In(hyponymFilename);
        String[] hyponymList;
        while (in.hasNextLine()) {
            hyponymList = in.readLine().split(",");
            for (int i = 1; i < hyponymList.length; i += 1) { 
                graph.addEdge(Integer.parseInt(hyponymList[0]), Integer.parseInt(hyponymList[i]));
            }
        }
        return graph;
    }
    


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (wordToId.containsKey(noun));    
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        for (String noun: wordToId.keySet()) {
            nounSet.add(noun);
        }
        return nounSet;
    }



    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> wordSet = new HashSet<String>();
        List<Integer> synsetIds = wordToId.get(word);
        // Found how to convert List<Integer> to Set<Integer> from 
        // http://www.baeldung.com/convert-list-to-set-and-set-to-list
        Set<Integer> setOfSynsetIds = new HashSet<>(synsetIds);
        Set<Integer> ids = GraphHelper.descendants(graphOfHyponym, setOfSynsetIds);
        for (int id : ids) {
            List<String> values = idToWord.get(id);
            for (String val : values) {
                wordSet.add(val);
            }
        }
        return wordSet;
    }
}



