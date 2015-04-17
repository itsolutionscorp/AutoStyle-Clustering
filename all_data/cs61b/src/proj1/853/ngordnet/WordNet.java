package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private HashMap<Integer, String> idSynset = new HashMap<Integer, String>();
    private MultiHashMap<String, Integer> wordId = new MultiHashMap<String, Integer>();
    private Digraph g;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        String[] line;
        int id;
        String synset;
        String[] words;

        while (!synsetFile.isEmpty()) {
            line = synsetFile.readLine().split(",");
            id = Integer.parseInt(line[0]);
            
            synset = line[1];
            idSynset.put(id, synset);

            words = line[1].split(" ");
            for (String w : words) {
                wordId.put(w, id);
            }
        }

        int parent;
        int child;
        g = new Digraph(idSynset.size());
        while (!hyponymFile.isEmpty()) {
            line = hyponymFile.readLine().split(",");
            parent = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                child = Integer.parseInt(line[i]);
                g.addEdge(parent, child);
            }
        }


    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (wordId.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> returnValue = new HashSet<String>();
        for (String i : wordId.keySet()) {
            returnValue.add(i);
        }
        return returnValue;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> d = GraphHelper.descendants(g, wordId.get(word));
        HashSet<String> returnSet = new HashSet<String>();
        for (int i : d) {
            String[] synsetArray = idSynset.get(i).split(" ");
            for (String j : synsetArray) {
                returnSet.add(j);
            }
        }
        return returnSet;
    }
}
