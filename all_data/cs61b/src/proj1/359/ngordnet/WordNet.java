package ngordnet;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Synset[] synsets;
    private Map<String, ArrayList<Integer>> wordsToIds = new HashMap<String, ArrayList<Integer>>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In file = new In(synsetFilename);
        ArrayList<Synset> synsetList = new ArrayList<Synset>();
        while (file.hasNextLine()) {
            String[] tokens = file.readLine().split(",");

            int id = Integer.parseInt(tokens[0]);
            String[] words = tokens[1].split(" ");
            String definition = tokens[2];

            synsetList.add(new Synset(words, definition));
            for (String word : words) {
                ArrayList<Integer> ids = wordsToIds.get(word);
                if (ids == null) {
                    ids = new ArrayList<Integer>();
                    wordsToIds.put(word, ids);
                }
                ids.add(id);
            }
        }
        file.close();

        synsets = synsetList.toArray(new Synset[synsetList.size()]);

        file = new In(hyponymFilename);
        while (file.hasNextLine()) {
            String[] tokens = file.readLine().split(",");

            int id = Integer.parseInt(tokens[0]);
            Synset synset = synsets[id];
            for (int j = 1; j < tokens.length; ++j) {
                synset.addHyponym(Integer.parseInt(tokens[j]));
            }
        }
        file.close();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordsToIds.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordsToIds.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> results = new HashSet<String>();
        for (Integer id : wordsToIds.get(word)) {
            addHyponymsById(id, results);
        }
        return results;
    }

    /* Add all hyponyms of synset identified by ID to RESULTS using DFS. */
    private void addHyponymsById(Integer id, Set<String> results) {
        Synset synset = synsets[id];
        for (String word : synset.words()) {
            results.add(word);
        }
        for (Integer hyponymId : synset.hyponyms()) {
            addHyponymsById(hyponymId, results);
        }
    }

    public class Synset {
        private String[] words;
        private String definition;
        private Collection<Integer> hyponyms = new ArrayList<Integer>();

        public Synset(String[] w, String def) {
            words = w;
            definition = def;
        }

        public String[] words() {
            return words;
        }

        public String definition() {
            return definition;
        }

        public void addHyponym(int id) {
            hyponyms.add(id);
        }

        public Collection<Integer> hyponyms() {
            return hyponyms;
        }
    }
}
