package ngordnet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private final HashMap<Integer, Synset> synsets;
    private final Digraph digraph;
    private final Set<String> nouns;

    public WordNet(String synsetFilename, String hypernymFilename) {
        // Read synsets
        synsets = new HashMap<Integer, Synset>();
        nouns = new HashSet<String>();
        In synsetFile = new In(synsetFilename);
        while (synsetFile.hasNextLine()) {
            addSynset(synsetFile.readLine());
        }
        // Read hypernyms and build digraph
        digraph = new Digraph(synsets.size());
        In hypernymFile = new In(hypernymFilename);
        while (hypernymFile.hasNextLine()) {
            addHypernym(hypernymFile.readLine());
        }
    }

    private void addSynset(String line) {
        // Split line into chunks
        String[] chunks = line.split(",");
        if (chunks.length < 3) {
            System.out.println(Arrays.toString(chunks));
            throw new RuntimeException("Cannot read synset (too few args)");
        }
        // Parse individual chunks and instantiate Synset
        int id = Integer.parseInt(chunks[0]);
        String[] words = chunks[1].split(" ");
        Synset prev = synsets.put(id, new Synset(id, words, null)); // Definition
                                                                    // unused
                                                                    // for now
        if (prev != null) {
            throw new RuntimeException("Duplicate synset IDs");
        }
        // Also add to set of all nouns
        for (String word : words) {
            nouns.add(word);
        }
    }

    private void addHypernym(String line) {
        String[] chunks = line.split(",");
        int parent = Integer.parseInt(chunks[0]);
        for (int i = 1; i < chunks.length; i++) {
            digraph.addEdge(parent, Integer.parseInt(chunks[i]));
        }
    }

    public Set<String> nouns() {
        return nouns;
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /**
     * Returns hyponyms of word in all synsets.
     */
    public Set<String> hyponyms(String word) {
        HashSet<String> hyponyms = new HashSet<String>();
        // For each synset this word belongs to...
        for (Integer synsetId : getMatchingIds(word)) {
            // Add all synonyms (and this word itself)
            for (String synonym : synsets.get(synsetId).words) {
                hyponyms.add(synonym);
            }
            // Add all hyponyms using DFS
            Queue<Integer> synsetsToCheck = new LinkedList<Integer>();
            synsetsToCheck.add(synsetId);
            while (synsetsToCheck.peek() != null) {
                Integer currentSynsetId = synsetsToCheck.poll();
                for (Integer hyponymId : digraph.adj(currentSynsetId)) {
                    for (String hyponym : synsets.get(hyponymId).words) {
                        hyponyms.add(hyponym);
                    }
                    synsetsToCheck.add(hyponymId);
                }
            }
        }
        return hyponyms;
    }

    // Returns the IDs of all synsets containing the given word
    private Set<Integer> getMatchingIds(String word) {
        Set<Integer> ids = new HashSet<Integer>();
        for (Integer i : synsets.keySet()) {
            Synset synset = synsets.get(i);
            if (arrayContains(word, synset.words)) {
                ids.add(i);
            }
        }
        return ids;
    }

    private static <T> boolean arrayContains(T target, T[] array) {
        for (T item : array) {
            if (item.equals(target)) {
                return true;
            }
        }
        return false;
    }

    private static class Synset {

        private int id;
        private String[] words;
        private String gloss; // Unused

        public Synset(int synsetId, String[] synsetWords, String synsetGloss) {
            super();
            id = synsetId;
            words = synsetWords;
            gloss = synsetGloss;
        }

    }

}
