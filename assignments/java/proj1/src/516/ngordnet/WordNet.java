package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author VincentEscueta
 */
public class WordNet {
    private HashSet<String> nouns;
    private HashMap<Integer, String> synsetIDs;
    private int digraphSize;
    private int synsetIDsKey;
    private Digraph hyponymDigraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        nouns = new HashSet<String>();
        synsetIDs = new HashMap<Integer, String>();
        while (synsetFile.hasNextLine()) {
            String synsetLine = synsetFile.readLine();
            String[] synsetList = synsetLine.split(",");
            synsetIDsKey = Integer.parseInt(synsetList[0]);
            synsetIDs.put(synsetIDsKey(), synsetList[1]);
            if (synsetList[1].contains(" ")) {
                String[] words = synsetList[1].split(" ");
                for (String word : words) {
                    nouns.add(word);
                }
            } else {
                nouns.add(synsetList[1]);
            }
        }
        digraphSize = synsetIDsKey() + 1;
        hyponymDigraph = new Digraph(digraphSize());
        while (hyponymFile.hasNextLine()) {
            String hyponymLine = hyponymFile.readLine();
            String[] hyponymList = hyponymLine.split(",");
            int v = Integer.parseInt(hyponymList[0]);
            for (int i = 1; i < hyponymList.length; i += 1) {
                int w = Integer.parseInt(hyponymList[i]);
                hyponymDigraph.addEdge(v, w);
            }
        }

    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String words) {
        Set<Integer> wordsSet = new HashSet<Integer>();
        for (int i = 0; i < synsetIDs().size(); i += 1) {
            String[] splitWords = synsetIDs().get(i).split(" ");
            for (String word: splitWords) {
                if (word.contentEquals(words)) {
                    wordsSet.add(i);
                }
            }
        }
        Set<Integer> hyponymIDs = GraphHelper.descendants(hyponymDigraph(), wordsSet);
        Set<String> hyponyms = new HashSet<String>();
        for (int hyponymID: hyponymIDs) {
            String temp = synsetIDs().get(hyponymID);
            if (temp.contains(" ")) {
                String[] tempList = temp.split(" ");
                for (String word: tempList) {
                    hyponyms.add(word);
                }
            } else {
                hyponyms.add(temp);
            }
        }
        return hyponyms;
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Return size Digraph must be. */
    private int digraphSize() {
        return digraphSize;
    }

    /** Return Map connecting ID with synset. */
    private Map<Integer, String> synsetIDs() {
        return synsetIDs;
    }

    /** Return latest integer stored that would later be put into key
     * for synsetIDs. */
    private int synsetIDsKey() {
        return synsetIDsKey;
    }

    /** Return hyponymDigraph */
    private Digraph hyponymDigraph() {
        return hyponymDigraph;
    }
}
