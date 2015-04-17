package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

public class WordNet {
    // Array of strings, where index = synsetID and string = synset, i.e [String]
    // Will be instatiated after we determine how many synsets there are
    private String[] synsets;

    // Map linking word(String) -> synsetIDs(Set<Integer>)
    private HashMap<String, Set<Integer>> wordToSynsetIDsMap = new HashMap<String, Set<Integer>>();

    // Digraph storing our synset relations
    private Digraph digraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);

        // Parse the synset file and populate our maps
        // Read all lines into a string array
        String[] synsetFileLines = synsetFile.readAllLines();
        synsets = new String[synsetFileLines.length];

        // For each line
        for (String line: synsetFileLines) {
            // Get the components of the line
            String[] lineParts = line.split(",");

            // Get the parts
            Integer synsetID = Integer.valueOf(lineParts[0]);
            String synset = lineParts[1];

            // Update synsets
            synsets[synsetID] = synset;

            // For each word in the the synset, update its entry within wordToSynsetIDsMap
            for (String word: synset.split(" ")) {
                Set<Integer> wordSynsetIDSet;

                // If word is already inside the map, then get its set entry, else create new set
                if (wordToSynsetIDsMap.containsKey(word)) {
                    wordSynsetIDSet = wordToSynsetIDsMap.get(word);
                } else {
                    wordSynsetIDSet = new HashSet<Integer>();
                }

                // Update the set to include our new synsetID
                wordSynsetIDSet.add(synsetID);
                wordToSynsetIDsMap.put(word, wordSynsetIDSet);
            }
        }

        // Create our digraph with the number of vertices being the same as the number of synsets
        digraph = new Digraph(synsets.length);

        // Read from hyponymFile
        while (hyponymFile.hasNextLine()) {
            String[] lineParts = hyponymFile.readLine().split(",");
            int synsetID = Integer.parseInt(lineParts[0]);

            for (int i = 1; i < lineParts.length; ++i) {
                int hyponymID = Integer.parseInt(lineParts[i]);
                digraph.addEdge(synsetID, hyponymID);
            }
        }
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToSynsetIDsMap.containsKey(noun);
    }   

    // /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToSynsetIDsMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> result = new HashSet<String>();

        // If word not in our database, then return empty result
        if (!isNoun(word)) {
            return result;
        }

        // Get all initial synsets that contain word
        Set<Integer> wordSynsets = wordToSynsetIDsMap.get(word);

        // Add on additional synsets that are reachable from the word
        for (Integer newSynsetID: GraphHelper.descendants(digraph, wordSynsets)) {
            wordSynsets.add(newSynsetID);
        }

        // Loop through all synsets and add their words to result
        for (Integer synsetID: wordSynsets) {
            String[] synsetWords = synsets[synsetID].split(" ");
            result.addAll(Arrays.asList(synsetWords));
        }

        // Remove word from result
        return result;
    }
}
