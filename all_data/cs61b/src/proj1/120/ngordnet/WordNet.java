package ngordnet;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.introcs.In;

/**
 * An object that stores the WordNet graph in a manner useful for extracting all
 * hyponyms of a word.
 * 
 * @author Yitz Deng
 */
public class WordNet {
    /** A map from integer (index of synset) to a set of strings (the synset) */
    private HashMap<Integer, HashSet<String>> synMap;

    /** A map from synset index to a set of synsets */
    private HashMap<Integer, HashSet<Integer>> hypMap;

    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     * 
     */
    public WordNet(String synsetFilename, String hyponymFilename) {

        // hyponymFilename, synsetFilename can't start with . in eclipse. Odd.
        // If autograder errors then check here.

        In hypScan, synScan;
        hypScan = new In(hyponymFilename);
        synScan = new In(synsetFilename);
        this.synMap = new HashMap<Integer, HashSet<String>>();
        this.hypMap = new HashMap<Integer, HashSet<Integer>>();
        while (!synScan.isEmpty()) {
            String line = synScan.readLine();
            String[] lineElements = line.split(",");
            String[] synonyms = lineElements[1].split(" ");
            HashSet<String> synSet = new HashSet<String>();
            for (String word : synonyms) {
                synSet.add(word);
            }
            this.synMap.put(Integer.valueOf(lineElements[0]), synSet);
        }
        while (!hypScan.isEmpty()) {

            String line = hypScan.readLine();
            String[] lineStrings = line.split(",");
            int numOfhyps = lineStrings.length - 1;
            Integer hyponym = Integer.valueOf(lineStrings[0]);
            HashSet<Integer> hypList = new HashSet<Integer>();

            for (int i = 0; i < numOfhyps; i++) {
                hypList.add(Integer.valueOf(lineStrings[i + 1]));
            }
            if (this.hypMap.containsKey(hyponym)) {
                this.hypMap.get(hyponym).addAll(hypList);
            } else {
                this.hypMap.put(hyponym, hypList);
            }
        }
    }

    /**
     * Returns true if NOUN is a word in some synset.
     * 
     * @param noun
     *            Check this noun
     * @return boolean is noun in a synset
     */
    public boolean isNoun(String noun) {
        Collection<HashSet<String>> words = this.synMap.values();
        for (HashSet<String> set : words) {
            if (set.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the set of all nouns.
     *
     * 
     * @return Set<String>
     */
    public Set<String> nouns() {
        Collection<HashSet<String>> words = this.synMap.values();
        HashSet<String> wordSet = new HashSet<String>();
        for (HashSet<String> set : words) {
            for (String word : set) {
                wordSet.add(word);
            }

        }
        return wordSet;

    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     * 
     * @param word
     * @return Set<String>
     */
    public Set<String> hyponyms(String word) {
        if (!this.isNoun(word)) {
            return null;
        }
        HashSet<String> wordSet = new HashSet<String>();
        Collection<HashSet<String>> synCollection = this.synMap.values();
        for (HashSet<String> set : synCollection) {
            if (set.contains(word)) {
                for (String noun : set) {
                    wordSet.add(noun);
                }
            }

        }

        Set<Integer> hypCollection = this.synMap.keySet();
        Set<Integer> containing = new HashSet<Integer>();

        for (Integer set2 : hypCollection) {
            if (this.synMap.get(set2).contains(word)) {
                containing.add(set2);
            }
        }

        for (Integer hyponymID : containing) {

            wordSet.addAll(this.getHyponymFromSynset(hyponymID));
        }

        return wordSet;
    }

    private Set<String> getHyponymFromSynset(Integer synsetID) {

        Set<String> synset = this.synMap.get(synsetID);

        Set<Integer> hyponyms = this.hypMap.get(synsetID);

        Set<String> wordSet = new HashSet<String>(synset);

        if (hyponyms == null) {
            return wordSet;
        }
        for (Integer synsetInHyponym : hyponyms) {

            Set<String> lowerHyponym = this
                    .getHyponymFromSynset(synsetInHyponym);
            if (lowerHyponym == null) {
                continue;
            }
            wordSet.addAll(lowerHyponym);
        }
        return wordSet;
    }
}
