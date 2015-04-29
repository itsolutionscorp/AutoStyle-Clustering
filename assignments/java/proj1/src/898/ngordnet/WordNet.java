package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    private Map<Integer, Set<String>> ids;
    private Map<String, Set<Integer>> nouns;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        ids = new HashMap<Integer, Set<String>>();
        nouns = new HashMap<String, Set<Integer>>();
        try {
            BufferedReader synsetbr = new BufferedReader(new FileReader(synsetFilename));
            String line = synsetbr.readLine();
            while (line != null) {
                String[] synset = line.split(",");
                String[] synonyms = synset[1].split("\\s");
                Integer id = Integer.valueOf(synset[0]);
                Set<String> idsValue = new HashSet<String>();
                ids.put(id, idsValue);
                for (String synonym : synonyms) {
                    idsValue.add(synonym);
                    Set<Integer> relatedIds = nouns.get(synonym);
                    if (relatedIds == null) {
                        relatedIds = new TreeSet<Integer>(); 
                        nouns.put(synonym, relatedIds);
                    } 
                    relatedIds.add(id);
                }
                line = synsetbr.readLine();
            }
            hyponyms = new Digraph(ids.size());
            BufferedReader hyponymbr = new BufferedReader(new FileReader(hyponymFilename));
            line = hyponymbr.readLine();
            while (line != null) {
                String[] hyponym = line.split(",");
                int hypernym = Integer.parseInt(hyponym[0]);
                for (int i = 1; i < hyponym.length; i++) {
                    hyponyms.addEdge(hypernym, Integer.parseInt(hyponym[i]));
                }
                line = hyponymbr.readLine();
            }
        } catch (IOException e) {
            return;
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return this.nouns.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> result = new HashSet<String>();
        for (Integer id : GraphHelper.descendants(this.hyponyms, this.nouns.get(word))) {
            result.addAll(this.ids.get(id));
        }
        return result;
    }

}
