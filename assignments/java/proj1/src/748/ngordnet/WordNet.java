package ngordnet;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Arrays;
// import ngordnet.SynsetMap;
// import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

/* Vocabulary:
 *   Synset - one or more words with the same meaning
 *       Words can belong to multiple synsets
 *   Collocation - words that tend to appear next to each other
 *   Hyponym - a more specific synset - "change" is a hyponym of "action"
 *   Hypernym - a more general synset - "change" is a hypernym of "demotion"
 *
 *
 * Read in the provided synset/hypernum datafiles (.csv)
 *   - synsets.txt - SYNSET_ID (int), synset (separated by spaces), dictionary definition
 *                 - Will not fomrally use the definition
 *   - hyponyms.txt - SYNSET_ID (int), Hyponym_relationships ... (int)
 *                  - 79537, 38611, 9007 - synset 79537 has two hyponyms, 38611, 9007
 * Store in Digraph
 *   - Digraph requires number of vertices in advance
 *   - # vertices = # of synsets
 *   - # edges represent hyponym relationships
 *   - # for each hyponym, save first int, add the edges to each of the next ones
 * 
 * We need a map of the words to the digraph indices. Keys will be words, value will be IDs
 * We need a map of the digraph indices to the words. Keys will be IDs, value will be words.
 */
public class WordNet {
    private Digraph ourGraph;
    private SynsetMap ourMap = new SynsetMap();

    public WordNet(String synsetFile, String hyponymFile) {
        try {
            // Read in synset data.
            FileReader synsetFR = new FileReader(synsetFile);
            BufferedReader synsetReader = new BufferedReader(synsetFR);
            String line;
            int lineCounter = 0;
            String[] lineArgs = new String[3];
            int synsetCounter = 0;
            // Place each line into our map
            while ((line = synsetReader.readLine()) != null) {
                ourMap.put(line);
                lineCounter += 1;
            }

            ourGraph = new Digraph(lineCounter);
            
            // Read in hyponym data.
            FileReader hyponymFR = new FileReader(hyponymFile);
            BufferedReader hyponymReader = new BufferedReader(hyponymFR);
            while ((line = hyponymReader.readLine()) != null) {
                String[] lineArgs2 = line.split(",");
                for (int i = 1; i < lineArgs2.length; i++) {
                    ourGraph.addEdge(Integer.parseInt(lineArgs2[0]),
                                     Integer.parseInt(lineArgs2[i]));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Returns whether the word is a noun.
    // I believe all the synsets are nouns, so as long as this word exists, it is a noun
    // Given a word, see if this key exists. If exists, then return true.
    public boolean isNoun(String word) {
        return ourMap.containsWord(word);
    }

    // Returns all nouns
    public Set<String> nouns() {
        Set<String> output = new HashSet<>(Arrays.asList(ourMap.getAllWords()));
        return output;
    }

    // Returns all hyponyms as well as all synonyms of the word.
    public Set<String> hyponyms(String word) {
        Set<Integer> wordIndices = ourMap.getIndicesForWord(word);
        // Get the indices of the synset objects we are interested in
        Set<Integer> queryIndices = new TreeSet<Integer>();
        for (int i : wordIndices) {
            queryIndices.add(i);
        }
        Set<Integer> descendantIndices = GraphHelper.descendants(ourGraph, queryIndices);

        // Take all the indices, and get words
        Set<String> outputWords = new HashSet<String>();
        // Get the synonyms
        for (int i : wordIndices) {
            String[] synonyms = ourMap.getWordArrayForIndex(i);
            for (int x = 0; x < synonyms.length; x++) {
                outputWords.add(synonyms[x]);
            }
        }
        // Get the child synset words + synonyms
        for (int i : descendantIndices) {
            String[] wordArrayTemp = ourMap.getWordArrayForIndex(i);
            for (int j = 0; j < wordArrayTemp.length; j++) {
                outputWords.add(wordArrayTemp[j]);
            }
        }
        return outputWords;
    }
}
