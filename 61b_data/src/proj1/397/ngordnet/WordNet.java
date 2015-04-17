package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;

/** 
 *  @author Chuhan Zhang, Josh Hug
 *  utilize the data structure developed in HW ULLMap
 */

public class WordNet {
    
    // this is the map of number and word.
    private ULLMap<Integer, String> wordmapOne = new ULLMap<Integer, String>();
    // the invert of wordmap_one.
    private ULLMap<String, Integer> wordmapinOne = new ULLMap<String, Integer>();
    // this is the map of number and definition.
    private ULLMap<Integer, String> wordmapTwo = new ULLMap<Integer, String>();
    // this is the map of numbers of corresponding hyponyms.
    private ULLMap<Integer, Integer> hypomapOne = new ULLMap<Integer, Integer>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetf = new In(synsetFilename);
        In hyponymf = new In(hyponymFilename);
        while (synsetf.hasNextLine()) {
            String line = synsetf.readLine();
            String delim = ",";
            String[] tokens = line.split(delim);
            String[] synset = tokens[1].split(" ");
            for (int i = 0; i < synset.length; i++) {
                wordmapOne.put(Integer.parseInt(tokens[0]), synset[i]);
                wordmapinOne.put(synset[i], Integer.parseInt(tokens[0]));
            }
            wordmapTwo.put(Integer.parseInt(tokens[0]), tokens[2]);
        }
        while (hyponymf.hasNextLine()) {
            String line = hyponymf.readLine();
            String delim = ",";
            String[] tokens = line.split(delim);
            for (int i = 1; i < tokens.length; i++) {
                hypomapOne.put(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordmapinOne.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordmapOne.valueSet();    
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
        * WORD. If WORD belongs to multiple synsets, return all hyponyms of
        * all of these synsets. See http://goo.gl/EGLoys for an example.
        * Do not include hyponyms of synonyms.
        */
    public Set<String> hyponyms(String word) {
        Set<Integer> keys = wordmapinOne.getall(word);
        Set<String> hypos = new HashSet<String>();
        hypos.add(word);
        if (keys.isEmpty()) {
            return hypos;
        }
        for (int key:keys) {
            Set<Integer> hyps = hypomapOne.getall(key);  
            for (int s : hyps) {
                for (String w:wordmapOne.getall(s)) {
                    hypos.addAll(hyponyms(w));
                }
            }
            for (String w:wordmapOne.getall(key)) {
                hypos.add(w);
            }
        }
        return hypos;
    }

}

