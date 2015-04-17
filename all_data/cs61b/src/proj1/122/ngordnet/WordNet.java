package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {
  /** Private instance variables for the class. */
    private Digraph dg;
    private TreeMap<Integer, String[]> idSynsetMap = new TreeMap<>();
    private TreeMap<String, TreeSet<Integer>> synsetIdMap = new TreeMap<>();
    private In synsetFile;
    private In hyponymFile;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = new In(synsetFilename);
        hyponymFile = new In(hyponymFilename);
        int numOfNouns = 0;

        while (synsetFile.hasNextLine()) {
            String synsetLine = synsetFile.readLine();
            String[] synsetLineSplit = synsetLine.split(",");
            int synsetId = Integer.parseInt(synsetLineSplit[0]);
            String synset = synsetLineSplit[1];
            String[] words = synset.split(" ");
            for (String noun: words) {
                if (!synsetIdMap.containsKey(noun)) {
                    TreeSet<Integer> firstNounID = new TreeSet();
                    firstNounID.add(synsetId);
                    synsetIdMap.put(noun, firstNounID);
                    numOfNouns++; 
                } else {
                    synsetIdMap.get(noun).add(synsetId);
                }
            } 

          // Putting DATA into my IDSYNSETMAP to store ID - ArrayOfWords(SYNSET) relationships.
            idSynsetMap.put(synsetId, words);
        }

        // Instantiating the Digraph for numOfNouns vertices to store ID relationships.
        dg = new Digraph(numOfNouns);

        // Putting DATA into my DIGRAPH to store ID - ID relationships.
        while (hyponymFile.hasNextLine()) {
            String hyponymLine = hyponymFile.readLine();
            // An array of Strings
            String[] parsedHyponymLine = hyponymLine.split(",");
            String hypernym = parsedHyponymLine[0];
            for (int i = 1; i < parsedHyponymLine.length; i++) {
                dg.addEdge(Integer.parseInt(hypernym), Integer.parseInt(parsedHyponymLine[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsetIdMap.keySet().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetIdMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        TreeSet<String> hyponymsResult = new TreeSet<>();
        TreeSet<Integer> wordIDs = synsetIdMap.get(word);
        TreeSet<Integer> hyponymIDs = (TreeSet<Integer>) GraphHelper.descendants(dg, wordIDs);
        for (int id: hyponymIDs) {
            String[] wordsOfID = idSynsetMap.get(id);
            for (String noun: wordsOfID) {
                if (!hyponymsResult.contains(noun)) {
                    hyponymsResult.add(noun);
                }
            }
        }
        return (Set<String>) hyponymsResult;
    }
}
