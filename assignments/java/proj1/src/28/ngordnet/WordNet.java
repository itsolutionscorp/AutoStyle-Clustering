package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/** An object that stores the WordNet graph in a manner useful for extracting
  * all hyponyms of a word.*/
public class WordNet {
    
    private HashMap<String, HashSet<Integer>> nounsMapSI;
    private HashMap<Integer, String> nounsMapIS;
    private Digraph graph;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        In synsetIn = new In(synsetFilename);
        /** A map of synsets in which every word is related to its ID */
        nounsMapSI = new HashMap<String, HashSet<Integer>>();
        nounsMapIS = new HashMap<Integer, String>();
        for (int i = 0; synsetIn.hasNextLine(); i++) {
            String sRead = synsetIn.readLine();
            String[] sLine = sRead.split(",");
            String[] syns = sLine[1].split(" ");
            int synID = Integer.parseInt(sLine[0]);
            for (int j = 0; j < syns.length; j++) {
                if (nounsMapSI.containsKey(syns[j])) {
                    nounsMapSI.get(syns[j]).add(synID);
                } else {
                    HashSet<Integer> synIDs = new HashSet<Integer>();
                    synIDs.add(synID);
                    nounsMapSI.put(syns[j], synIDs);
                }
                nounsMapIS.put(synID, sLine[1]);
            }
        }

        /** Instantiates a new graph once synsets have been established */
        graph = new Digraph(nounsMapSI.keySet().size());

        In hyponymIn = new In(hyponymFilename);
        /** A continuation of the synsets map that now connect to their hyponyms */
        for (int i = 0; hyponymIn.hasNextLine(); i++) {
            String hRead = hyponymIn.readLine();
            String[] hLine = hRead.split(",");
            for (int j = 0; j < hLine.length - 1; j++) {
                graph.addEdge(Integer.parseInt(hLine[0]), Integer.parseInt(hLine[j + 1]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounsMapSI.keySet().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounsMapSI.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> wordIDs = nounsMapSI.get(word);
        Set<Integer> hyponymIDs = GraphHelper.descendants(graph, wordIDs);
        HashSet<String> result = new HashSet<String>();
        for (Integer id : hyponymIDs) {
            String[] inside = nounsMapIS.get(id).split(" ");
            for (String s : inside) {
                result.add(s);
            }
        }
        return result;
    }

}
