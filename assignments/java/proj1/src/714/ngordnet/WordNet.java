
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import com.google.common.collect.HashMultimap;
import java.util.TreeSet;
import java.util.Set;
//import java.io.FileNotFoundException;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMultimap<Integer, String> idToWord;
    private HashMultimap<String, Integer> wordToId;
    private Set nounSet;
    private Digraph relationships;

    //maybe throw an exception here for testing purposes
    public WordNet(String synsetFilename, String hyponymFilename) {

        idToWord = HashMultimap.create();
        wordToId = HashMultimap.create();

        nounSet = new TreeSet<String>();
        int diagraphSize = 0;

        In createIds = new In(synsetFilename);
        while (createIds.hasNextLine()) {
            String[] currentlinesynsets = createIds.readLine().split(",");
            String[] synsetList = currentlinesynsets[1].split(" ");
            int currentID = Integer.parseInt(currentlinesynsets[0]);

            for (int i = 0; i < synsetList.length; i++) {
                idToWord.put(currentID, synsetList[i]);
                wordToId.put(synsetList[i], currentID);
                nounSet.add(synsetList[i]);
            }
            diagraphSize += 1;
        }

        relationships = new Digraph(diagraphSize);
        In matchIds = new In(hyponymFilename);
        while (matchIds.hasNextLine()) {
            String[] currentlinehyponyms = matchIds.readLine().split(",");
            int parent = Integer.parseInt(currentlinehyponyms[0]);
            for (int i = 1; i < currentlinehyponyms.length; i++) {
                int child = Integer.parseInt(currentlinehyponyms[i]);
                relationships.addEdge(parent , child);
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return idToWord.containsValue(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

        Set<String> setOfHyponyms = new TreeSet<String>();
        setOfHyponyms.add(word);
        Set<Integer> findIds = wordToId.get(word);
        for (Integer spot : GraphHelper.descendants(relationships, findIds)) {
            for (String descendant : idToWord.get(spot)) {
                setOfHyponyms.add(descendant);
            }
        }
        return setOfHyponyms;
    }
}

