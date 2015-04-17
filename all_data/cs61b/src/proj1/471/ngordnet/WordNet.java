package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private In synset,
               hyponym;
    private HashMap<Integer, String> synsetWordIds;
    private HashMap<String, HashSet<Integer>> synsetInverse;
    private Digraph hypograph; 

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename);

        /** Construct id-to-word & word-to-id synsets. */
        synsetWordIds = new HashMap<Integer, String>();
        synsetInverse = new HashMap<String, HashSet<Integer>>();

        /** ID-to-word synset */
        while (synset.hasNextLine()) {
            String nextSynset = synset.readLine();
            String[] synsetItems = nextSynset.split(",");
            synsetWordIds.put(Integer.parseInt(synsetItems[0]), synsetItems[1]);
        }

        /** Word-to-ID(s) synset */
        for (Map.Entry<Integer, String> entry : synsetWordIds.entrySet()) {
            String[] splitSynset = entry.getValue().split(" ");
            for (int i = 0; i < splitSynset.length; i++) {
                String single = splitSynset[i];
                HashSet set = synsetInverse.get(single);
                if (set == null) {
                    HashSet<Integer> nSet = new HashSet<Integer>();
                    nSet.add(entry.getKey());
                    synsetInverse.put(single, nSet);
                } else {
                    set.add(entry.getKey());
                }
            }
        }

        /** Construct hypograph Hashmap */
        hypograph = new Digraph(synsetWordIds.size());

        while (hyponym.hasNextLine()) {
            String nextHyponym = hyponym.readLine();
            String[] hyponymDraft = nextHyponym.split(",");
            int[] hyponymIDs = new int[hyponymDraft.length];
            for (int i = 0; i < hyponymDraft.length; i++) {
                hyponymIDs[i] = Integer.parseInt(hyponymDraft[i]);
            }
            int vertice = hyponymIDs[0];
            for (int i = 1; i < hyponymIDs.length; i++) {
                hypograph.addEdge(vertice, hyponymIDs[i]);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetInverse.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> idList = synsetInverse.get(word);
        Set<Integer> descended = GraphHelper.descendants(hypograph, idList);
        HashSet<String> finalHyp = new HashSet<String>();
        for (int id : descended) {
            if (synsetWordIds.get(id).contains(" ")) {
                String[] synsetWords = synsetWordIds.get(id).split(" ");
                for (String sWord : synsetWords) {
                    finalHyp.add(sWord);
                }
            } else {
                finalHyp.add(synsetWordIds.get(id));
            }
        }
        return finalHyp;
    }
}



