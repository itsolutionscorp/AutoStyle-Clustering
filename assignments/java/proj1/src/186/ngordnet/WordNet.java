package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;


/**
 * @author Patrick Lorio
 */
public class WordNet {
    private final Map<Integer, Synset> synsets;
    private final Map<String, Set<Integer>> nounSynsetIds;
    private final Digraph graph;

    public WordNet(String synsetsFile, String hyponymsFile) {
        int v = 0;
        String[] lines;

        synsets = new HashMap<>();
        nounSynsetIds = new HashMap<>();

        lines = new In(synsetsFile).readAllLines();
        for (String line : lines) {
            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]);
            String word = parts[1];
            String definition = parts[2];

            Synset synset = new Synset(id, word, definition);
            synsets.put(id, synset);

            for (String noun : synset.nouns) {
                Set<Integer> items;
                if (!nounSynsetIds.containsKey(noun)) {
                    items = new HashSet<>();
                    nounSynsetIds.put(noun, items);
                } else {
                    items = nounSynsetIds.get(noun);
                }

                items.add(id);
            }

            if (synset.nouns.length > 1) {
                Set<Integer> items = new HashSet<>();
                items.add(id);
                nounSynsetIds.put(parts[1], items);
            }

            ++v;
        }

        graph = new Digraph(v);

        lines = new In(hyponymsFile).readAllLines();
        for (String line : lines) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                graph.addEdge(id, Integer.parseInt(parts[i]));
            }
        }
    }

    public boolean isNoun(String word) {
        return !word.contains(" ") && nounSynsetIds.containsKey(word);
    }

    public Set<String> nouns() {
        Set<String> strings = nounSynsetIds.keySet();
        Set<String> res = new HashSet<>();
        for (String string : strings) {
            if (string.contains(" ")) {
                continue;
            }
            res.add(string);
        }
        return res;
    }

    public Set<String> hyponyms(String word) {
        Set<String> res = new HashSet<>();
        Set<Integer> ids = nounSynsetIds.get(word);
        Set<Integer> descendants = GraphHelper.descendants(graph, ids);
        for (int id : descendants) {
            res.addAll(Arrays.asList(synsets.get(id).nouns));
        }
        return res;
    }

    private static class Synset {
        private final int id;
        private final String word;
        private final String[] nouns;
        private final String definition;

        public Synset(int idValue, String wordValue, String definitionValue) {
            id = idValue;
            word = wordValue;
            nouns = wordValue.split(" ");
            definition = definitionValue;
        }

        public int getId() {
            return id;
        }

        public String getWord() {
            return word;
        }

        public String[] getNouns() {
            return nouns;
        }

        public String getDefinition() {
            return definition;
        }
    }
}
