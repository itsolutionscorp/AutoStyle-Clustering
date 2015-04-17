package ngordnet;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Arrays;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

/** WordNet class takes in two files, a synset file and a hyponym file
 *  and uses pre-existing ADTs to store the information..
 *  @author Jean Coronejo
 */ 

public class WordNet {
    private Digraph g;
    private String[] definitions; // not yet implemented
    private HashMap<Integer, String[]> hMap = new HashMap<Integer, String[]>();
    /* hMap is a HashMap that stores 
     * Key --> synset ID#
     * Value --> synset words
     */

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In readSyn = new In(synsetFilename);
        In readHyp = new In(hyponymFilename);
        String in;
      
        while (readSyn.hasNextLine()) { 
            in = readSyn.readLine();
            String[] s = in.split(","); // first element always #, then synset, then definition
            String[] sepWords = s[1].split(" ");
            hMap.put(Integer.parseInt(s[0]), sepWords); 
        }

        // String[] definitions = new String[hMap.size()];
        // definitions[hMap.size()-1] = s[2];

        // Create new DIGRAPH
        g = new Digraph(hMap.size());

        while (readHyp.hasNextLine()) { 
            in = readHyp.readLine();
            String[] h = in.split(","); 
        
            for (String n:h) {
                if (Integer.parseInt(n) != Integer.parseInt(h[0])) {
                    g.addEdge(Integer.parseInt(h[0]), Integer.parseInt(n));
                }
            }
        } 
    }

    /* Returns true if NOUN is a word in some synset. 
     * check if word is not null or is invalid
     * check if word is in some synset 
     */ 
    public boolean isNoun(String noun) {
        if (noun != null) {
            return nouns().contains(noun);
        } else {
            return false;
        }
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        // creates String[] of all hMap's values
        Set<String[]> valuesArr = new HashSet<>(hMap.values()); 
        Set<String> nounSet = new HashSet<>(); // returning nounSet
        /* for every String[] in ValuesArr, 
         * split Strings of words and add them to nounSet
         */
        for (String[] strOfWords:valuesArr) {
            for (String s:strOfWords) {
                nounSet.add(s);
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
        Set<String> hyponymSet = new TreeSet<String>();
        Set<Integer> ids = new HashSet<Integer>();
        Set<Integer> desc = new HashSet<Integer>();
      
        if (isNoun(word)) {
            /* Checks if word is in nounSet
             * If so, adds key to ids Set */
            for (int i = 0; i < hMap.size(); i++) { 
                if (Arrays.asList(hMap.get(i)).contains(word)) {
                    ids.add(i); 
                }
            }

            /* Adding synset word and synonyms to hyponymSet*/
            for (int i:ids) { 
                String[] sArr = hMap.get(i);
                for (String s:sArr) {
                    hyponymSet.add(s);
                }
            }
            /* Find all descendants of ids in ids Set*/
            desc = GraphHelper.descendants(g, ids);
            
            /* Find descendants values and add those words to hyponymSet*/
            for (int d:desc) { 
                String[] dArr = hMap.get(d); // get the String[] Values of key D
                for (String s: dArr) {
                    hyponymSet.add(s);
                }
            }
        } // end of if statement

        return hyponymSet;
        // throw new UnsupportedOperationException();
    }
}
