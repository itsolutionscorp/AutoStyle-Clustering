package ngordnet;
import edu.princeton.cs.algs4.Digraph;
// import edu.princeton.cs.introcs.In;.
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map; 


public class WordNet {

    private Map<Integer, ArrayList<String>> synsetNouns;
    private Digraph hyponym;
    private Set<String> nouns;

  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetScanner = new In(synsetFilename);

        synsetNouns = new HashMap<Integer, ArrayList<String>>();
        nouns = new HashSet<String>();

        while (synsetScanner.hasNextLine()) {
            ArrayList<String> synsetArrayList = new ArrayList<String>();
            String row = synsetScanner.readLine();
            String[] tokens = row.split(",");
            String[] synset = tokens[1].split(" ");
            for (int i = 0; i < synset.length; i++) {
                synsetArrayList.add(synset[i]);
                nouns.add(synset[i]);
            }
            synsetNouns.put(Integer.parseInt(tokens[0]), synsetArrayList);
        }

        In hyponymScanner = new In(hyponymFilename);
        hyponym = new Digraph(synsetNouns.size());
        while (hyponymScanner.hasNextLine()) {
            String row = hyponymScanner.readLine();
            String[] tokens = row.split(",");
            for (int i = 1; i < tokens.length; i++) {
                hyponym.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
    }

  /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

  /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

  /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> ids = new HashSet<Integer>();
        for (Integer key: synsetNouns.keySet()) {
            if (synsetNouns.get(key).contains(word)) {
                ids.add(key);
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(hyponym, ids);
        for (Integer j: descendants) {
            for (String value: synsetNouns.get(j)) {
                hyponyms.add(value);
            }
        }
        return hyponyms;
    }
}










