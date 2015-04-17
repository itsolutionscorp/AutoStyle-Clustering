package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class WordNet {

    /* Most important data structure for hyponyms method. */
    private Digraph digraph;

    /* reverseSMap stores id, nouns. */
    private Map<String, String[]> reverseSMap = new HashMap<String, String[]>();

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        Set<String> synsetFileInfo = new HashSet<String>();
        /* Read in the file.*/
        In synsetIn = new In(synsetFilename);
        /* Store all the lines into the HashSet. */
        while (synsetIn.hasNextLine()) {
            synsetFileInfo.add(synsetIn.readLine());
        }       
        /* Go through the HashSet and split on commas. */
        for (String s: synsetFileInfo) {
            String[] words = s.split(",");
            String[] nouns = words[1].split("\\s+");
            reverseSMap.put(words[0], nouns);
        }

        /* Instaniate digraph. */
        digraph = new Digraph(reverseSMap.size());

        Set<String> hyponymFileInfo = new HashSet<String>();
        /* Read in the file.*/
        In hyponymIn = new In(hyponymFilename);
        /* Store all the lines into the HashSet. */
        while (hyponymIn.hasNextLine()) {
            hyponymFileInfo.add(hyponymIn.readLine());
        }
        // Go through the HashSet and split on commas. 
        for (String s: hyponymFileInfo) {
            String[] words = s.split(",");
            for (int i = 1; i < words.length; i += 1) {
                digraph.addEdge(Integer.parseInt(words[0]), Integer.parseInt(words[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Map.Entry<String, String[]> entry : reverseSMap.entrySet()) {
            String[] values = entry.getValue();
            for (String v: values) {
                if (v.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> s = new HashSet<String>();
        for (Map.Entry<String, String[]> entry : reverseSMap.entrySet()) {
            String[] values = entry.getValue();
            for (String v: values) {
                s.add(v);
            }
        }
        return s;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> s = new HashSet<String>();
        String hyperId;

        /* Adds synonyms to s. */
        for (Map.Entry<String, String[]> entry : reverseSMap.entrySet()) {
            String id = entry.getKey();
            String[] nouns = entry.getValue();

            for (String noun: nouns) {
                if (word.equals(noun)) {
                    for (String n: nouns) {
                        s.add(n);
                    }
                    continue;
                }
            }

            Set<Integer> hypoIds = new HashSet<Integer>();
            /* Stores the id of WORD in hyperId. */
            for (String noun: nouns) {
                if (word.equals(noun)) {
                    hypoIds.add(Integer.parseInt(id));
                    /* GraphHelper.descendants returns a Set of Integers with all the hypoIds. */ 
                    for (Integer i: GraphHelper.descendants(digraph, hypoIds)) {
                        String[] values = reverseSMap.get(String.valueOf(i));
                        for (String val: values) {
                            s.add(val);
                        }
                    }
                    break;
                }
            }
        }
        return s;
    }
}
