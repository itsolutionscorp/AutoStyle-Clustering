package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * An object that stores the WordNet graph in a manner useful for extracting all hyponyms of a
 * word.
 *
 * @author Bryan Nguyen
 */
public class WordNet {
    // Stores the ID numbers of synsets and their hyponym relationships.
    private final Digraph digraph;
    // Maps synset ID numbers to synsets.
    private final Map<Integer, Set<String>> synsetIds;
    // Stores pairs of synsets and synset ID numbers.
    private final List<SynsetAndId> synsets;
    // The non-unique number of nouns in the WordNet.
    private int nounCount;
    // The set of all nouns in the WordNet.
    private Set<String> nouns;

    /**
     * Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME
     *
     * @param synsetFilename  the String containing the path to the file of synsets
     * @param hyponymFilename the String containing the path to the file of hyponyms
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nounCount = 0;
        /* Read the list of synsets. */
        File synsetFile = new File(synsetFilename);
        int synsetCount = countLines(synsetFile);
        synsetIds = new HashMap<>(synsetCount);
        synsets = new ArrayList<>(synsetCount);
        readSynsets(synsetFile);
        /* Read the list of hyponyms. */
        digraph = new Digraph(synsetCount);
        File hyponymFile = new File(hyponymFilename);
        readHyponyms(hyponymFile);
        /* Create the set of nouns. */
        generateNouns();
    }

    /**
     * Returns true if the given NOUN is in the WordNet.
     *
     * @param noun the String to look for
     * @return true if the given String is in the WordNet
     */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /**
     * Returns a Set containing all the nouns in the WordNet.
     *
     * @return the Set containing all the nouns in the WordNet
     */
    public Set<String> nouns() {
        return nouns;
    }

    /**
     * Returns a Set containing all the hyponyms of the given WORD.
     *
     * @param word the String whose hyponyms are to be found
     * @return the Set containing all the hyponyms of the given word
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> hypernyms = new TreeSet<>();
        for (SynsetAndId synset : synsets) {
            if (synset.synonymSet.contains(word)) {
                hypernyms.add(synset.synsetId);
            }
        }
        Set<Integer> hyponymIds = GraphHelper.descendants(digraph, hypernyms);
        Set<String> hyponyms = new HashSet<>();
        for (int hyponymId : hyponymIds) {
            for (String hyponym : synsetIds.get(hyponymId)) {
                hyponyms.add(hyponym);
            }
        }
        return hyponyms;
    }

    // Counts the number of lines in the specified file.
    private static int countLines(File file) {
        int lines = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((reader.readLine()) != null) {
                lines += 1;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Reads lines from the specified file, reading from each line the synset ID and the list of
    // synonyms in the synset, storing the synonyms in a Set, and adding pairs of synset IDs and
    // synsets to the WordNet's map. Also adds the number of synonyms found to nounCount.
    private void readSynsets(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] synset = line.split(",");
                if (synset.length < 2) {
                    continue;
                }
                String[] synonyms = synset[1].split(" ");
                int synsetId = Integer.parseInt(synset[0]);
                Set<String> synonymSet = new HashSet<>(Arrays.asList(synonyms));
                synsetIds.put(synsetId, synonymSet);
                synsets.add(new SynsetAndId(synonymSet, synsetId));
                nounCount += synonyms.length;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads lines from the specified file, reading from each line the hypernym and hyponym IDs
    // and adding an edge representing each hyponymy to the digraph.
    private void readHyponyms(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] hyponymy = line.split(",");
                int hypernym = Integer.parseInt(hyponymy[0]);
                for (int i = 1; i < hyponymy.length; i += 1) {
                    int hyponym = Integer.parseInt(hyponymy[i]);
                    digraph.addEdge(hypernym, hyponym);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Searches synsetIds for all nouns and adds them to nouns.
    private void generateNouns() {
        nouns = new HashSet<>(nounCount);
        for (Set<String> synonyms : synsetIds.values()) {
            for (String synonym : synonyms) {
                nouns.add(synonym);
            }
        }
    }

    // A synset together with its synset ID.
    private class SynsetAndId {
        private final Set<String> synonymSet;
        private final int synsetId;

        private SynsetAndId(Set<String> mySynonymSet, int mySynsetId) {
            this.synonymSet = mySynonymSet;
            this.synsetId = mySynsetId;
        }
    }
}
