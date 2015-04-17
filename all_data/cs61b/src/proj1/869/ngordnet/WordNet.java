package ngordnet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;

public class WordNet {
    /** Map with word to set of ids that contain it */
    private HashMap<String, HashSet<Integer>> wordIDs;
    /** Map with id to set of words it refers to */
    private HashMap<Integer, String> synsets;
    /** Represents edges/relations between words */
    private Digraph wdRelations;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);
        
        wordIDs = new HashMap<String, HashSet<Integer>>();
        synsets = new HashMap<Integer, String>();
        int numVertices = 0;            // Counts vertices to create Digraph later
        while (synFile.hasNextLine()) {   // Adds each synset line to the map
            String line = synFile.readLine();
            /** Array 'split' of the form "|id|word1,word2...|definition|" */
            String[] split = line.split(",");
            
            Integer id = Integer.parseInt(split[0]);
            synsets.put(id, split[1]);

            /** Array 'synset' of the form "|word1|word2|...|" */
            String[] synset = split[1].split(" ");

            for (int i = 0; i < synset.length; i++) {
                String key = synset[i];
                if (wordIDs.containsKey(key)) {
                    HashSet<Integer> vals = wordIDs.get(key);
                    vals.add(id);
                    wordIDs.put(key, vals);
                } else {
                    HashSet<Integer> vals = new HashSet<Integer>();
                    vals.add(id);
                    wordIDs.put(key, vals);
                }
            }
            numVertices++;
        }

        /** Add 'edges' to Digraph, with each node represented by the synset ID */
        wdRelations = new Digraph(numVertices);
        while (hypFile.hasNextLine()) {
            String line = hypFile.readLine();
            /** Array 'hyponyms' of the form "word ID|hyponym1 ID| hyponym2 ID|..." */
            String[] hyponyms = line.split(",");
            
            int wordID = Integer.parseInt(hyponyms[0]);
            for (int i = 1; i < hyponyms.length; i++) {
                int hyponymID = Integer.parseInt(hyponyms[i]);
                wdRelations.addEdge(wordID, hyponymID);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        HashSet<Integer> ids = wordIDs.get(word);
        Set<Integer> hyponymIDs = GraphHelper.descendants(wdRelations, ids);
        HashSet<String> hyponyms = new HashSet<String>();
        for (Integer id : hyponymIDs) {
            String[] arr = synsets.get(id).split(" ");
            for (String s : arr) {
                hyponyms.add(s);
            }
        }
        return hyponyms;
    }

    /** to be written */
    public boolean isNoun(String noun) {
        // If 'noun' is in our synset, return true
        return wordIDs.containsKey(noun);
    }

    /** to be written */
    public Set<String> nouns() {
        HashSet<String> nouns = new HashSet<String>();
        for (Integer key :  synsets.keySet()) {
            String[] arr = synsets.get(key).split(" ");
            for (String s : arr) {
                nouns.add(s);
            }
        }

        return nouns;
    }
}
