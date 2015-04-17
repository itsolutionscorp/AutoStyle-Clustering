package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Collection;

public class WordNet {
    private HashMap<Integer, TreeSet<String>> synset = new HashMap<Integer, TreeSet<String>>();
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        while (synsetFile.hasNextLine()) {
            String current = synsetFile.readLine();
            String[] rawTokens = current.split(",");
            Integer id = Integer.parseInt(rawTokens[0]);
            String[] tokens = rawTokens[1].split(" ");
            TreeSet<String> syns = new TreeSet<String>();
            for (String token : tokens) {
                syns.add(token);
            }
            synset.put(id, syns);
        }
        g = new Digraph(synset.size());

        In hyponymFile = new In(hyponymFilename);
        while (hyponymFile.hasNextLine()) {
            String current = hyponymFile.readLine();
            String[] tokens = current.split(",");
            int hypernymId = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i += 1) {
                g.addEdge(hypernymId, Integer.parseInt(tokens[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Collection<String> words : synset.values()) {
            if (words.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> result = new TreeSet<String>();
        for (Collection<String> words : synset.values()) {
            result.addAll(words);
        }
        return result;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        TreeSet<String> result = new TreeSet<String>();
        TreeSet<Integer> hypernymId = new TreeSet<Integer>();
        
        for (Integer key : synset.keySet()) {
            if (synset.get(key).contains(word)) {
                hypernymId.add(key);
            }
        }

        Set<Integer> hyponymId = GraphHelper.descendants(g, hypernymId);
        for (Integer key : hyponymId) {
            result.addAll(synset.get(key));
        }
        return result;
    }
}
