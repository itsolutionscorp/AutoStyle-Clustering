package ngordnet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<String, Set<Integer>> wordToID;
    private Map<Integer, Set<String>> idToWord;

    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        wordToID = new HashMap<>();
        idToWord = new HashMap<>();

        In in = new In(synsetFilename);
        while (in.hasNextLine()) {
            String[] info = in.readLine().split(",");

            int id = Integer.parseInt(info[0]);
            String[] words = info[1].split(" ");
            HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));

            idToWord.put(id, wordSet);
            for (String word : words) {
                if (wordToID.containsKey(word)) {
                    wordToID.get(word).add(id);
                } else {
                    HashSet<Integer> set = new HashSet<Integer>();
                    set.add(id);
                    wordToID.put(word, set);
                }
            }
        }
        in.close();

        g = new Digraph(idToWord.size());

        in = new In(hyponymFilename);
        while (in.hasNextLine()) {
            String[] entries = in.readLine().split(",");
            int first = Integer.parseInt(entries[0]);
            for (int i = 1; i < entries.length; i++) {
                g.addEdge(first, Integer.parseInt(entries[i]));
            }
        }
        in.close();
    }

    public boolean isNoun(String noun) {
        return wordToID.containsKey(noun);
    }

    public Set<String> nouns() {
        return wordToID.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        if (!wordToID.containsKey(word)) {
            return new HashSet<String>();
        }
        Set<Integer> vertices = GraphHelper.descendants(g, wordToID.get(word));
        HashSet<String> hyponyms = new HashSet<>();
        for (int id : vertices) {
            hyponyms.addAll(idToWord.get(id));
        }
        return hyponyms;
    }
}
