package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class WordNet {

    private final Set<String> nouns;
    private final Map<String, Set<Integer>> wordIdMap;
    private final Map<Integer, String[]> idSynsetMap;
    private final Digraph wordnet;

    public WordNet(String synsetFilename, String hyponymFilename) {
        wordIdMap = new HashMap<>();
        idSynsetMap = new HashMap<>();
        nouns = new HashSet<>();
        readSynsets(synsetFilename);

        wordnet = new Digraph(idSynsetMap.size());
        readHyponyms(hyponymFilename);
    }

    private void readSynsets(String synsetFilename) {
        In in = new In(synsetFilename);

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] columns = line.split(",");

            int id = Integer.parseInt(columns[0]);
            String[] words = columns[1].split(" ");

            addSynset(words, id);
        }
    }

    private void addSynset(String[] words, int id) {
        idSynsetMap.put(id, words);
        Collections.addAll(nouns, words);

        for (String word : words) {
            Set<Integer> ids;
            if (wordIdMap.containsKey(word)) {
                ids = wordIdMap.get(word);
            } else {
                ids = new HashSet<>();
            }
            ids.add(id);
            wordIdMap.put(word, ids);
        }
    }

    private void readHyponyms(String hyponymFilename) {
        In in = new In(hyponymFilename);

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] columns = line.split(",");
            int id = Integer.parseInt(columns[0]);

            for (int i = 1; i < columns.length; i++) {
                wordnet.addEdge(id, Integer.parseInt(columns[i]));
            }
        }
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of
     * these synsets. See http://goo.gl/EGLoys for an example. Do not include
     * hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            throw new IllegalArgumentException("Word \"" + word + "\" not found in wordnet");
        }

        Set<Integer> ids = wordIdMap.get(word);

        Set<Integer> hyponymIds = GraphHelper.descendants(wordnet, ids);

        Set<String> hyponyms = new HashSet<>();
        for (Integer id : hyponymIds) {
            String[] words = idSynsetMap.get(id);
            Collections.addAll(hyponyms, words);
        }

        return hyponyms;
    }

    public boolean isNoun(String word) {
        return nouns.contains(word);
    }

    public Set<String> nouns() {
        return nouns;
    }

}
