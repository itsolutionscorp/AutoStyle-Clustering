package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
/** 
 *  @author Hubert Pham
 */

public class WordNet {
    private String synsetFile;
    private String hyponymFile;
    private Map<Integer, String> synsetId;
    private Map<String, Set<Integer>> nounId;
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsetFile = synsetFilename;
        this.hyponymFile = hyponymFilename;
        synsetId = new HashMap<Integer, String>();
        nounId = new HashMap<String, Set<Integer>>();
        readSynset(synsetFile);
        g = new Digraph(synsetId.size());
        readHyponym(hyponymFile);
    }

    private void readSynset(String filename) {
        In reader = new In(filename);
        while (reader.hasNextLine()) {
            String[] read = reader.readLine().split(",");
            Integer num = Integer.valueOf(read[0]);
            String str = read[1];
            synsetId.put(num, str);
            String[] nouns = str.split(" ");
            for (String i:nouns) {
                Set<Integer> id = nounId.get(i);
                if (id != null) {
                    id.add(num);
                    nounId.put(i, id);
                } else {
                    id = new HashSet<Integer>();
                    id.add(num);
                    nounId.put(i, id);
                }
            }
        }
    }

    private void readHyponym(String filename) {
        In reader = new In(filename);
        while (reader.hasNextLine()) {
            String[] read = reader.readLine().split(",");
            Integer num = Integer.valueOf(read[0]);
            for (int i = 1; i < read.length; i++) {
                g.addEdge(num, Integer.valueOf(read[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounId.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounId.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        Set<Integer> id = nounId.get(word);
        Set<Integer> ids = GraphHelper.descendants(g, id);
        Set<String> result = new HashSet<String>();
        for (Integer i: ids) {
            String[] str = synsetId.get(i).split(" ");
            for (String s: str) {
                result.add(s);
            }
        }
        return result;
    }

      
}
