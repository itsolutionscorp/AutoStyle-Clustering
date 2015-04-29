package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;


public class WordNet {
    private HashMap<Integer, String[]> synMap = new HashMap<Integer, String[]>();
    private HashMap<String, Set<Integer>> idMap = new HashMap<String, Set<Integer>>();
    private Digraph wordGraph; 
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synReader = new In(synsetFilename);
        In hnymReader = new In(hyponymFilename);
        String[] synStr = synReader.readAllLines(); 
        String[] hypStr = hnymReader.readAllLines(); 
        wordGraph = new Digraph(synStr.length); 
        for (String line : synStr) {
            int counter = 0;
            String[] currLine = line.split(",");
            Integer idNum = Integer.parseInt(currLine[0]);
            String synsetWords = currLine[1];
            String[] keys = synsetWords.split("\\s+");
            synMap.put(idNum, keys);
            int i = 0;
            Integer keyID = idNum; 
            while (i < keys.length) {
                String key = keys[i];
                if (idMap.containsKey(key)) {
                    idMap.get(key).add(keyID);
                } else {
                    Set<Integer> numSet = new TreeSet<Integer>();
                    numSet.add(keyID);
                    idMap.put(key, numSet);
                }
                i++;
            }
            counter++;
        }
        for (String line : hypStr) {
            int q = 0;
            String[] stringList = line.split(",");
            int[] intList = new int[stringList.length]; 
            while (q < stringList.length) {
                int x = Integer.parseInt(stringList[q]);
                intList[q] = x;
                q++;
            }
            int start = intList[0];
            int i = 1;
            while (i < intList.length) {
                int end = intList[i];
                wordGraph.addEdge(start, end);
                i++;
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (idMap.containsKey(noun));
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (String[] nouns : synMap.values()) {
            for (String noun : nouns) {
                nounSet.add(noun);
            }
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> wordSet = new TreeSet<String>();
        Set<Integer> wordID = idMap.get(word);
        Set<Integer> idSet = GraphHelper.descendants(wordGraph, wordID);
        for (int x : idSet) {
            String[] set = synMap.get(x);
            int p = 0;
            while (p < set.length) {
                wordSet.add(set[p]);
                p++;
            }
        }
        return wordSet;
    }
}
