package ngordnet;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
 * @author Harry He
 *
 */
public class WordNet {

    private Digraph                           hyponymsRelation;
    private TreeMap<String, TreeSet<Integer>> indexesByWord;
    private TreeMap<Integer, TreeSet<String>> wordsByIndex;

    /**
     * Creates a WordNet using files form SYNSETFILENAME and
     * HYPONYMFILENAME
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        wordsByIndex = new TreeMap<Integer, TreeSet<String>>();
        indexesByWord = new TreeMap<String, TreeSet<Integer>>();

        In synsetsIn = new In(synsetFilename);
        while (synsetsIn.hasNextLine()) {
            String line = synsetsIn.readLine();
            String[] tokens = line.split("\n|,");
            int id = Integer.valueOf(tokens[0]);
            String words = tokens[1];
            // String description = tokens[2]; // desciption not used
            String[] wordTokens = words.split("\\s|,");

            TreeSet<String> wordsSet = wordsByIndex.get(id);
            if (wordsSet == null) {
                wordsSet = new TreeSet<String>();
                for (String word : wordTokens) {
                    wordsSet.add(word);
                }
                wordsByIndex.put(id, wordsSet);
            } else {
                for (String word : wordTokens) {
                    wordsSet.add(word);
                }
            }

            for (String word : wordsSet) {
                TreeSet<Integer> indexesSet = indexesByWord.get(word);
                if (indexesSet == null) {
                    TreeSet<Integer> set = new TreeSet<Integer>();
                    set.add(id);
                    indexesByWord.put(word, set);
                } else {
                    indexesSet.add(id);
                }
            }
        }
        synsetsIn.close();

        hyponymsRelation = new Digraph(wordsByIndex.size());
        In hyponymsIn = new In(hyponymFilename);
        while (hyponymsIn.hasNextLine()) {
            String line = hyponymsIn.readLine();
            String[] tokens = line.split("\\s|,");
            int synsetId = Integer.valueOf(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                int hyponymId = Integer.valueOf(tokens[i]);
                hyponymsRelation.addEdge(synsetId, hyponymId);
            }
        }
        hyponymsIn.close();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example. Do
     * not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> toRet = new TreeSet<String>();
        toRet.addAll(getHyponyms(word));
        toRet.addAll(getSynonyms(word));
        return toRet;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return indexesByWord.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return indexesByWord.keySet();
    }

    private Set<String> getHyponyms(String word) {
        Set<String> toRet = new TreeSet<String>();
        Set<Integer> indexes = getIndexes(word);
        Set<Integer> descendants =
            GraphHelper.descendants(hyponymsRelation, indexes);
        for (int index : descendants) {
            toRet.addAll(getWords(index));
        }
        return toRet;
    }

    // Return the corresbonding set.
    // if word DNE in the WordNet, return empty set.
    private Set<Integer> getIndexes(String word) {
        Set<Integer> toRet = indexesByWord.get(word);
        if (toRet == null) {
            return new TreeSet<Integer>();
        }
        return toRet;
    }

    private Set<String> getSynonyms(String word) {
        Set<String> toRet = new TreeSet<String>();
        Set<Integer> indexes = getIndexes(word);
        for (int index : indexes) {
            toRet.addAll(getWords(index));
        }
        return toRet;
    }

    // Return the corresbonding set.
    // if index DNE in the WordNet, return empty set.
    private Set<String> getWords(int index) {
        Set<String> toRet = wordsByIndex.get(index);
        if (toRet == null) {
            return new TreeSet<String>();
        }
        return toRet;
    }
}
