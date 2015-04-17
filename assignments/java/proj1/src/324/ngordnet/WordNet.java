package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /*
     * synsets will be stored in order of their synset number
     * with all Nouns in a synset stored in String[] 
     */
    private Map<Integer, String[]> synsets;
    /* Each noun will be mapped to its synset number in nouns */
    private Map<String, LinkedList<Integer>> nouns;
    private Digraph wordWeb;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nouns = new HashMap<String, LinkedList<Integer>>();
        synsets = new HashMap<Integer, String[]>();
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        while (synsetIn.hasNextLine()) {
            String currLine = synsetIn.readLine();
            /*
             * splits at commas: 0th el is number, 1st is all Nouns,
             * 2nd is definition 
             */
            String[] synPieces = currLine.split(",");
            Integer synNum = Integer.parseInt(synPieces[0]);
            String[] words = synPieces[1].split(" ");
            for (String word : words) {
                if (nouns.containsKey(word)) {
                    nouns.get(word).add(synNum);
                } else {
                    LinkedList<Integer> synNums = new LinkedList<Integer>();
                    synNums.add(synNum);
                    nouns.put(word, synNums);
                }
            }
            synsets.put(synNum, words);
        }
        /* Now creating vertices and edges in wordWeb */
        wordWeb = new Digraph(synsets.size());
        while (hyponymIn.hasNextLine()) {
            String[] strNums = hyponymIn.readLine().split(",");
            int[] nums = new int[strNums.length];
            for (int i = 0; i < strNums.length; i += 1) {
                nums[i] = Integer.parseInt(strNums[i]);
            }
            /*
             * num in 0th position linked to all other poitions
             * add edges accordingly
             */
            for (int i = 1; i < nums.length; i += 1) {
                wordWeb.addEdge(nums[0], nums[i]);
            }

        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new HashSet<String>();
        if (this.isNoun(word)) {
            LinkedList<Integer> wordKeys = nouns.get(word);
            /* adding synonyms */
            Set<Integer> vertsWithWord = new TreeSet<Integer>();
            for (Integer key : wordKeys) {
                vertsWithWord.add(key);
                String[] synonyms = synsets.get(key);
                for (String synonym : synonyms) {
                    if (!synonym.equals(word)) {
                        toReturn.add(synonym);
                    }
                }
            }
            Set<Integer> hyponyms = GraphHelper.descendants(wordWeb, vertsWithWord);
            for (Integer hyponymSetNum : hyponyms) {
                String[] hyponymSet = synsets.get(hyponymSetNum);
                for (String hyponym : hyponymSet) {
                    toReturn.add(hyponym);
                }
            }
        }
        toReturn.add(word);
        return toReturn;
    }
}
