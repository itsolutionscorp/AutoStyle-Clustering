package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.io.File;
import java.util.HashMap;
import java.util.Set; 
import java.util.HashSet;
import java.util.Arrays;

public class WordNet  {
    private HashMap<Integer, HashSet<String>> synSetMap;
    private HashSet<String> synSetWords;
    private Digraph digraph;
    private In synSetFile;
    private In hypFile;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synSetFile = new In(new File(synsetFilename));
        hypFile = new In(new File(hyponymFilename));
        synSetMap = new HashMap<Integer, HashSet<String>>();
        synSetWords = new HashSet<String>(); 

        while (synSetFile.hasNextLine()) {
            String[] synSetFileLine = synSetFile.readLine().split(",");
            int id = Integer.parseInt(synSetFileLine[0]);
            String[] textArray = synSetFileLine[1].split(" ");
            String definition = synSetFileLine[2];
            synSetMap.put(id, new HashSet<String>(Arrays.asList(textArray)));
            synSetWords.addAll(Arrays.asList(textArray));
        }

        digraph = new Digraph(synSetMap.size());
        
        while (hypFile.hasNextLine()) {
    
            String[] hypFileLine = hypFile.readLine().split(",");
            int idHype = Integer.parseInt(hypFileLine[0]);
            for (int i = 1; i < hypFileLine.length; i++) {
                digraph.addEdge(idHype, Integer.parseInt(hypFileLine[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synSetWords.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synSetWords; 
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> ids = new HashSet<Integer>();
        for (Integer key: synSetMap.keySet()) {
            if (synSetMap.get(key).contains(word)) {
                ids.add(key);
            }

        }
        Set<Integer> descendents = GraphHelper.descendants(digraph, ids);
        HashSet<String> hyponyms = new HashSet<String>();
        for (Integer descendent: descendents) {
            hyponyms.addAll(synSetMap.get(descendent));
        }

        return hyponyms;

    }
}
