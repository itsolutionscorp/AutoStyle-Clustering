package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
//import java.lang.String;


public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private String[] ssName, hnName, ssVal, hnVal;
    private int[] ssKey, hnKey;
    // shp is ids to words
    private HashMap<Integer, ArrayList<String>> shp = new HashMap<Integer, ArrayList<String>>();
    // whp is words to ids
    private HashMap<String, ArrayList<Integer>> whp = new HashMap<String, ArrayList<Integer>>();
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename)  {
        In x = new In(synsetFilename);
        ssName = x.readAllLines(); // read the lines and put them into string array
        ssKey = new int[ssName.length];
        ssVal = new String[ssName.length];
        // go over all those array indexes to take out
        // the int corresponding to each line
        for (int i = 0; i < ssName.length; i++) { // learned how to split from stackoverflow
            ArrayList<Integer> al = new ArrayList<Integer>();
            ArrayList<String> sl = new ArrayList<String>();
            String[] parts = ssName[i].split(",");
            ssKey[i] = Integer.parseInt(parts[0]);
            ssVal[i] = parts[1];
            String[] wparts = ssVal[i].split(" ");
            for (String part : wparts) {
                if (shp.containsKey(ssKey[i])) {
                    shp.get(ssKey[i]).add(part);
                } else {
                    sl.add(part);
                    shp.put(ssKey[i], sl);
                }
            }
            // put the key and val into the hashmap
            for (String part : wparts) {
                if (whp.containsKey(part)) {
                    whp.get(part).add(ssKey[i]);
                } else {
                    al.add(ssKey[i]);
                    whp.put(part, al);
                }
            }
        }
        // words to ids and ids to ss ^
        In y = new In(hyponymFilename);
        hnName = y.readAllLines();
        g = new Digraph(ssName.length);
        for (int j = 0; j < hnName.length; j++) {
            String[] parts = hnName[j].split(",");
            int z = parts.length;
            for (int i = 1; i < z; i++) {
                g.addEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
      // if the noun is equal to one of the val in hashmap (synsets file), return true
        for (String key : whp.keySet()) {
      // if noun is the same as the key
            String[] parts = key.split(" ");
            for (String part : parts) {
                if (noun.equals(part)) {
                    return true;
                }
            }
        // since at most 1 space can be in per word, the partition be only 2 at most
        }
        return false; // else false
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> noun = new HashSet<String>();
        for (String key : whp.keySet()) {
            noun.add(key);
        }
            
        return noun;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> iD = new HashSet<Integer>();
      // get the id of the word from words to ids
        for (Integer key : shp.keySet()) {
      // if noun is the same as the key
            //Tanvi Mongia
            ArrayList<String> words = shp.get(key); 
            for (String theWord : words) {
                if (word.equals(theWord)) { // if word and part are the same, get the id
                    iD.add(key);
                }
            }
        }
        // once the id is obtained
        Set<Integer> hypoId = GraphHelper.descendants(g, iD);
        Set<String> hypo = new HashSet<String>();
        // check shp for the id
        for (int i : hypoId) {
            for (String part : shp.get(i)) {
                hypo.add(part);
            }

        }

        return hypo;
    }
}
