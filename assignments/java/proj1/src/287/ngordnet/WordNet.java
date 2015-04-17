// Inspiration for Helper class (updateID) in hyponyms from Justin Liu
package ngordnet;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.List;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, String> synADT;
    private Digraph digraph;
    private Set<Integer> hyponymID;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synADT = new HashMap<Integer, String>();
        HashMap<Integer, String> hyponymADT = new HashMap<Integer, String>();
        In synFile = new In(synsetFilename); // Uses In to assign file and use In methods.
        In hyponymFile = new In(hyponymFilename);
        String[] lines = synFile.readAllLines();
        String[] lines2 = hyponymFile.readAllLines();
        
        for (int i = 0; i < lines.length; i += 1) {
            String line = lines[i];
            String[] split = line.split(",");
            synADT.put(Integer.parseInt(split[0]), split[1]);
        }

        for (int i = 0; i < lines2.length; i += 1) {
            String line2 = lines2[i];
            String line3;
            String[] split2 = line2.split(",");
            Integer key = Integer.parseInt(split2[0]);
            if (hyponymADT.containsKey(key)) {
                line2 = hyponymADT.get(key) + "," + line2.replace(key + ",", "");
            }
            hyponymADT.remove(key);
            hyponymADT.put(key, line2);
        }

        digraph = new Digraph(synADT.size());

        for (Map.Entry<Integer, String> entry : hyponymADT.entrySet()) {
            Integer hypId = entry.getKey();
            String hypValue = entry.getValue();
            String[] split = hypValue.split(",");
            for (int j = 1; j < split.length; j += 1) {
                digraph.addEdge(hypId, Integer.parseInt(split[j]));
            }
        }

        hyponymID = new HashSet<Integer>();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < synADT.size(); i += 1) {
            String wanted = synADT.get(i);
            String[] split = wanted.split(" ");
            List<String> list = Arrays.asList(split);

            if (list.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();

        for (String value: synADT.values()) {
            String[] split = value.split(" ");

            for (int i = 0; i < split.length; i += 1) {
                nounSet.add(split[i]);
            }
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    /** TODO
      * Add digraph
      */

    public Set<String> hyponyms(String word) {
        Set<String> hypoSet = new TreeSet<String>();
        updateHyponyms(word);
        for (Integer id : hyponymID) {
            String[] split = synADT.get(id).split(" ");
            
            for (int i = 0; i < split.length; i += 1) {
                hypoSet.add(split[i]);
            }
        }
        hyponymID.clear();
        return hypoSet;
    }

    private void updateHyponyms(String word) {
        for (Map.Entry<Integer, String> entry : synADT.entrySet()) {
            Integer key = entry.getKey();
            String[] split = entry.getValue().split(" ");
            for (int i = 0; i < split.length; i += 1) {
                if (split[i].equals(word)) {
                    updateID(key);
                }
            }
        }
    }

    private void updateID(int id) {
        if (!hyponymID.contains(id)) {
            hyponymID.add(id);
        } 

        Iterable<Integer> iter = digraph.adj(id);
        for (Integer newID : iter) {
            hyponymID.add(newID);
            updateID(newID);
        }
    }
}
