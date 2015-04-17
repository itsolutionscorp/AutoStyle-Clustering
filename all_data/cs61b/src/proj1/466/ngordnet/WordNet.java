package ngordnet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/** Read input files and store them in ADTs of your choice that allow for rapid queries.
 *  @author NT 
 */
public class WordNet {
    /** Map to hold synset values. */
    private Map<Integer, String> sMap = new LinkedHashMap<Integer, String>();

    /** */
    private Set<String> sSet = new LinkedHashSet<String>();

    /** Digraph of WordNet files. */
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Manage synsetFilename.
        synStream(synsetFilename);
        synsets();
        
        // Manage hyponymFilename.
        g = new Digraph(sMap.size());
        hypStream(hyponymFilename);
    }

    /** Organizes synset files. */
    private void synStream(String file) {
        In input = new In(file);

        // Read through the entire file, organizing input.
        while (input.hasNextLine()) {
            String str = input.readLine();
            String[] strArray = str.split(",");
            sMap.put(Integer.parseInt(strArray[0]), strArray[1]);
        }
    }

    /** Organizes hyponym files. */
    private void hypStream(String file) {
        In input = new In(file);

        // Read through the entire file, add edges.
        while (input.hasNextLine()) {
            int count = 1;
            String str = input.readLine();
            String[] strArray = str.split(",");
            while (count < strArray.length) {
                g.addEdge(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[count]));
                count += 1;
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nSet = new LinkedHashSet<String>();

        // Convert synsets to words.
        for (String synset : sSet) {
            String[] each = synset.split("\\s+");
            for (int i = 0; i < each.length; i += 1) {
                nSet.add(each[i]);
            }
        }
        return nSet;
    }

    /** Map to hold synsets. */
    private void synsets() {
        String sStr = sMap.values().toString();
        String[] sStrArray = sStr.split(", ");
        for (int i = 0; i < sStrArray.length; i += 1) {
            sStrArray[i] = sStrArray[i].replaceAll("\\[|\\]", "");
            sSet.add(sStrArray[i]);
        }
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hSet = new LinkedHashSet<String>();
        Set<Integer> v = new TreeSet<Integer>();
        Set<String> wordBank = new TreeSet<String>();

        // Get synsets of WORD.
        for (String synset : sSet) {
            String[] arWords = synset.split("\\s+");
            for (int i = 0; i < arWords.length; i += 1) {
                if (arWords[i].equals(word)) {
                    wordBank.add(synset);
                }
            }
        }

        // Get synset IDs of words in word bank.
        for (Integer id : sMap.keySet()) {
            for (String each : wordBank) {
                if (sMap.get(id).equals(each)) {
                    v.add(id);
                }
            }
        }

        // Get hyponyms from synset ID.
        Set<Integer> hypDes = GraphHelper.descendants(g, v);
        Integer[] desInt = hypDes.toArray(new Integer[hypDes.size()]); 
        for (int i = 0; i < desInt.length; i += 1) {
            String hStr = sMap.get(desInt[i]);
            String[] hStrArray = hStr.split("\\s+");
            for (int j = 0; j < hStrArray.length; j += 1) {
                hSet.add(hStrArray[j]);
            }
        }
        return hSet;
    }
}
