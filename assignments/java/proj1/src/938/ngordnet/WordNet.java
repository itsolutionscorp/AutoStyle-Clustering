package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;

public class WordNet {

    // Match noun to hyponyms and synonyms
    private Map<String, Set<String>> hypoMap = new HashMap<String, Set<String>>(); 
    // Match id to synsets
    private Map<Integer, Set<String>> synsetMap = new HashMap<Integer, Set<String>>();      

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        // Read in synonym file as an array of strings (for each line)
        In syn = new In(synsetFilename);
        String[] synsetArray = syn.readAllLines(); 

        // Read in hyponym file as an array of strings (for each line)
        In hyp = new In(hyponymFilename);
        String[] hypArray = hyp.readAllLines();

        // Create digraph of the hyponym file
        Digraph wn = new Digraph(synsetArray.length);
        for (int i = 0; i < hypArray.length; i += 1) { // For each row in array
            String[] hypRow = hypArray[i].split(","); 
            int parent = Integer.parseInt(hypRow[0]);
            for (int j = 1; j < hypRow.length; j += 1) {
                wn.addEdge(parent, Integer.parseInt(hypRow[j]));
            }
        }

        // For each noun (each line (entry) in synsetArray):
        for (int i = 0; i < synsetArray.length; i += 1) {
            // Get an array of its synonyms (2nd column)
            String[] synRow = synsetArray[i].split(","); 
            String[] synList = synRow[1].split(" "); 

            // Map id to synset
            Set<String> synset = new HashSet<String>();
            for (int j = 0; j < synList.length; j += 1) {
                synset.add(synList[j]);
            }
            synsetMap.put(i, synset);

            // Map each noun in line to its synonyms
            for (int j = 0; j < synList.length; j += 1) {
                if (hypoMap.containsKey(synList[j])) {        
                    Set<String> oldSynset = hypoMap.get(synList[j]);
                    Set<String> newSynset = new HashSet<String>(oldSynset);
                    newSynset.addAll(synset);
                    hypoMap.put(synList[j], newSynset);
                } else {
                    hypoMap.put(synList[j], synset);
                }
            }
        }

        // Add hyponyms to hypoMap for nouns that have descendents
        for (int i = 0; i < synsetArray.length; i += 1) {
            Set<Integer> descendSet = new TreeSet<Integer>();
            descendSet.add(i);
            Set<Integer> descendList = GraphHelper.descendants(wn, descendSet);
            if (descendList.size() > 1) {
                Set<String> newHypAndSyn = new HashSet<String>();
                for (Integer n : descendList) {
                    Set<String> newHyp = synsetMap.get(n);
                    newHypAndSyn.addAll(newHyp);
                }
                Set<String> entries = synsetMap.get(i);
                for (String s : entries) {
                    Set<String> oldHypAndSyn = hypoMap.get(s);
                    Set<String> updatedHyp = new HashSet<String>(newHypAndSyn);
                    updatedHyp.addAll(oldHypAndSyn);
                    hypoMap.put(s, updatedHyp);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return hypoMap.containsKey(noun);      
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return hypoMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        return hypoMap.get(word);
    }
}
