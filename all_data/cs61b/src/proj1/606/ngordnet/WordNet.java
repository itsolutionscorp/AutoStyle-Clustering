package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    
    private Map<Integer, String> synsets = new HashMap<Integer, String>();
    private Digraph wordnet;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetfile = new In(synsetFilename);
        In hyponymfile = new In(hyponymFilename);
        int vertices = 0;
        String synsetLine = synsetfile.readLine();
        while (synsetLine != null) {
            String[] synsetLineArr = synsetLine.split(",");
            int id = Integer.parseInt(synsetLineArr[0]);
            String noun = synsetLineArr[1];
            vertices += 1;
            synsets.put(id, noun);
            synsetLine = synsetfile.readLine();
        }

        wordnet = new Digraph(vertices);
        String hyponymLine = hyponymfile.readLine();
        while (hyponymLine != null) {
            String[] hyponymLineArr = hyponymLine.split(",");
            int id = Integer.parseInt(hyponymLineArr[0]);
            int[] hyponymsId = new int[hyponymLineArr.length - 1];
            int j = 0;
            for (int i = 1; i < hyponymLineArr.length; i += 1) {
                hyponymsId[j] = Integer.parseInt(hyponymLineArr[i]);
                j += 1;
            }
            for (int i = 0; i < hyponymsId.length; i += 1) {
                wordnet.addEdge(id, hyponymsId[i]);
            }
            hyponymLine = hyponymfile.readLine();
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int key : synsets.keySet()) {
            String[] nouns = synsets.get(key).split(" ");
            for (int i = 0; i < nouns.length; i += 1) {
                if (nouns[i].equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (int key : synsets.keySet()) {
            String[] nouns = synsets.get(key).split(" ");
            for (int i = 0; i < nouns.length; i += 1) {
                if (!allNouns.contains(nouns[i])) {
                    allNouns.add(nouns[i]);
                }
            }
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    //Recieved help from Nikita Rau (api)//
    public Set<String> hyponyms(String word) {
        Set<String> allHyponyms = new HashSet<String>();
        Set<Integer> hyponymKeys = new HashSet<Integer>();
        Set<Integer> synsetIDs = new HashSet<Integer>();
        
        for (int key : synsets.keySet()) {
            String[] nouns = synsets.get(key).split(" ");
            for (int i = 0; i < nouns.length; i += 1) {
                if (nouns[i].equals(word)) {
                    for (int j = 0; j < nouns.length; j++) {
                        allHyponyms.add(nouns[j]);
                    }
                    synsetIDs.add(key);
                    Set<Integer> descendants = GraphHelper.descendants(wordnet, synsetIDs);
                    for (int descendantID : descendants) {
                        String[] desNouns = synsets.get(descendantID).split(" ");
                        for (int k = 0; k < desNouns.length; k += 1) {
                            allHyponyms.add(desNouns[k]);
                        }
                    }
                }
            }
        }
        return allHyponyms;
    }
}
