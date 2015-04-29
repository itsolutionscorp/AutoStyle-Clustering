package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordNet {
    private final Map<Integer, String[]> synsetIdMap = new HashMap<Integer, String[]>();
    private final Map<String, Set<Integer>> synsetWordMap = new HashMap<String, Set<Integer>>();
    private Digraph hyponymDigraph;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            // Read and parse synsets
            BufferedReader synsetFile = Files.newBufferedReader(
                Paths.get(synsetFilename), StandardCharsets.UTF_8);
            String synsetLine = synsetFile.readLine();

            while (synsetLine != null) {
                String[] synsetParts = synsetLine.split(",");
                int id = Integer.parseInt(synsetParts[0]);
                String[] synset = synsetParts[1].split(" ");

                synsetIdMap.put(id, synset);
                for (String word : synset) {
                    if (!synsetWordMap.containsKey(word)) {
                        synsetWordMap.put(word, new HashSet<Integer>());
                    }

                    synsetWordMap.get(word).add(id);
                }

                synsetLine = synsetFile.readLine();
            }

            synsetFile.close();

            // Read and parse hyponyms
            BufferedReader hyponymFile = Files.newBufferedReader(
                Paths.get(hyponymFilename), StandardCharsets.UTF_8);
            String hyponymLine = hyponymFile.readLine();

            hyponymDigraph = new Digraph(synsetIdMap.size());

            while (hyponymLine != null) {
                String[] hyponymParts = hyponymLine.split(",", 2);
                int id = Integer.parseInt(hyponymParts[0]);
                String[] hyponymStrs = hyponymParts[1].split(",");

                // Add each hyponym word into the digraph
                for (String str : hyponymStrs) {
                    int wordId = Integer.parseInt(str);
                    hyponymDigraph.addEdge(id, wordId);
                }

                hyponymLine = hyponymFile.readLine();
            }

            hyponymFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms. */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        hyponyms.add(word);

        // No hyponyms exist for a word not found
        if (!synsetWordMap.containsKey(word)) {
            return hyponyms;
        }

        // Add synonyms
        Set<Integer> wordIds = synsetWordMap.get(word);

        for (int id : wordIds) {
            String[] synset = synsetIdMap.get(id);
            for (String synonym : synset) {
                hyponyms.add(synonym);
            }
        }

        // Add hyponyms
        Set<Integer> hyponymIds = GraphHelper.descendants(hyponymDigraph, wordIds);

        for (int id : hyponymIds) {
            String[] synset = synsetIdMap.get(id);
            for (String synonym : synset) {
                hyponyms.add(synonym);
            }
        }

        return hyponyms;
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String word : synsetWordMap.keySet()) {
            if (noun.equals(word)) {
                return true;
            }
        }

        return false;
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetWordMap.keySet();
    }
}
