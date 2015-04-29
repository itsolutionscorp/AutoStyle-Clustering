package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, List<String>> synsets;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In reader = new In(synsetFilename);
        synsets = new HashMap<Integer, List<String>>();
        int synsetCount = 0;
        while (reader.hasNextLine()) {
            String line = reader.readLine();
            String[] tokens = line.split(",");
            Integer id = Integer.parseInt(tokens[0]);
            List<String> words = new LinkedList<String>();
            for (String word : tokens[1].split(" ")) {
                words.add(word);
            }
            synsets.put(id, words);
            synsetCount++;
        }
        hyponyms = new Digraph(synsetCount);
        reader = new In(hyponymFilename);
        while (reader.hasNextLine()) {
            String line = reader.readLine();
            String[] tokens = line.split(",");
            int baseId = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                hyponyms.addEdge(baseId, Integer.parseInt(tokens[i]));
            }
        }
    }

    public boolean isNoun(String noun) {
        for (List<String> synset : synsets.values()) {
            if (synset.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (List<String> synset : synsets.values()) {
            nouns.addAll(synset);
        }
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIds = new HashSet<Integer>();
        for (Integer id : synsets.keySet()) {
            if (synsets.get(id).contains(word)) {
                synsetIds.add(id);
            }
        }
        synsetIds.addAll(GraphHelper.descendants(hyponyms, synsetIds));
        Set<String> hyponymsOutput = new HashSet<String>();
        for (Integer id : synsetIds) {
            hyponymsOutput.addAll(synsets.get(id));
        }
        return hyponymsOutput;
    }
}
