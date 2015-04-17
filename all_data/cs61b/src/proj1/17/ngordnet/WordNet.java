package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private HashMap<Integer, Synset> synsets;
    private Digraph relationships;

    private int size;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new HashMap<Integer, Synset>();
        System.out.println("Parsing synsets file " + synsetFilename);
        // parse synsets
        In synsetIn = new In(synsetFilename);
        String line;
        int id = 0;
        while ((line = synsetIn.readLine()) != null) {
            String[] rawTokens = line.split(",");
            id = Integer.parseInt(rawTokens[0]);
            String synonyms = rawTokens[1];
            String definition = rawTokens[2];
            Synset synsetObj = new Synset(id, synonyms, definition);
            synsets.put(id, synsetObj);
        }
        size = id + 1;

        relationships = new Digraph(size);

        System.out.println("Parsing hyponym file " + hyponymFilename);
        In hyponymIn = new In(hyponymFilename); // open file for reading
        while ((line = hyponymIn.readLine()) != null) {
            String[] rawTokens = line.split(",");
            id = Integer.parseInt(rawTokens[0]); // String->int, first int is top level ID
            for (int i = 1; i < rawTokens.length; i++) {
                int hyponymID = Integer.parseInt(rawTokens[i]);
                relationships.addEdge(id, hyponymID); // add relationship to Digraph
            }
        }
    }

    public Set<String> hyponyms(String word) {
        // Returns the set of all hyponyms of WORD as well as synonyms of WORD.
        HashSet<String> returnSet = new HashSet<String>();
        TreeSet<Integer> hyponymSet = new TreeSet<Integer>();

        // Loops through all synsets, searching for word.
        // When found, adds synonyms to returnSet and stores hyponyms
        for (Map.Entry<Integer, Synset> entry : synsets.entrySet()) {
            Synset synset = entry.getValue();
            if (synset.containsWord(word)) {
                hyponymSet.add(entry.getKey());
                if (synset.synonyms(word) != null) {
                    returnSet.addAll(synset.synonyms(word));
                }
            }
        }

        // Looks through stored hyponyms and adds those synsets to returnSets
        Set<Integer> descendants = ngordnet.GraphHelper.descendants(relationships, hyponymSet);
        for (Integer id : descendants) {
            returnSet.addAll(synsets.get(id).synset());
        }
        return returnSet;
    }

    public boolean isNoun(String noun) {
        return (nouns().contains(noun));
    }

    public Set<String> nouns() {
        HashSet<String> returnSet = new HashSet<String>();
        for (Map.Entry<Integer, Synset> entry : synsets.entrySet()) {
            returnSet.addAll(entry.getValue().synset());
        }

        return returnSet;
    }

    private final class Synset {
        private final int id;
        // HashSet of synonyms in this synset
        private HashSet<String> synset;
        private final String definition;

        public Synset(int newID, String unformattedSynset, String wordsDefinition) {
            this.id = newID;
            this.definition = wordsDefinition;
            String[] synonyms = unformattedSynset.split(" ");
            this.synset = new HashSet<String>(Arrays.asList(synonyms));
        }

        public boolean containsWord(String word) {
            return synset.contains(word);
        }

        public HashSet<String> synonyms(String word) {
            // returns all synonyms, but not word itself. returns null if only word in synset
            if (synset.size() == 1) {
                return null;
            }
            HashSet<String> temp = new HashSet<String>(synset);
            temp.remove(word);
            return temp;
        }

        public int id() {
            return id;
        }

        public HashSet<String> synset() {
            return synset;
        }

        public String definition() {
            return definition;
        }
    }
}
