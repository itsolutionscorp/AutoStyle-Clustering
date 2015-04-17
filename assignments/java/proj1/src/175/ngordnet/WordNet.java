package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.ArrayList;

public class WordNet {
    private Map<Integer, ArrayList<Integer>> indexMap;
    private Map<ArrayList<String>, ArrayList<ArrayList<String>>> wordMap;
    private Set<String> wordSet;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In hyponym = new In(hyponymFilename);
        In synset = new In(synsetFilename);
        this.indexMap = new TreeMap<Integer, ArrayList<Integer>>();
        this.wordMap = new HashMap<ArrayList<String>, ArrayList<ArrayList<String>>>();
        this.wordSet = new HashSet<String>();

        while (hyponym.hasNextLine()) {
            String line = hyponym.readLine();
            String[] rawTokens = line.split(",");
            Integer indexKey = Integer.parseInt(rawTokens[0]);
            ArrayList<Integer> indexOfhyponyms = new ArrayList<Integer>();
            for (int i = 1; i < rawTokens.length; i++) {
                indexOfhyponyms.add(Integer.parseInt(rawTokens[i]));
            }
            if (!indexMap.containsKey(indexKey)) {
                indexMap.put(indexKey, indexOfhyponyms);
            } else {
                ArrayList<Integer> curValue = indexMap.get(indexKey);
                curValue.addAll(indexOfhyponyms);
                indexMap.put(indexKey, curValue);
            }
        }
        /* put synsets into a map. Key: hypernym synset; Value: hyponym synset. */
        ArrayList<String[]> lineSaver = new ArrayList<String[]>();
        while (synset.hasNextLine()) {
            String line = synset.readLine();
            String[] rawTokens = line.split(",");
            lineSaver.add(rawTokens);
        }
        for (Map.Entry<Integer, ArrayList<Integer>> entry : indexMap.entrySet()) {
            Integer k = entry.getKey(); // index of word as key
            ArrayList<Integer> v = entry.getValue(); // indices of word as val
            ArrayList<String> hyperKey = new ArrayList<String>();
            if (lineSaver.get(k)[1].contains(" ")) {
                String[] words = lineSaver.get(k)[1].split(" ");
                for (String w : words) {
                    hyperKey.add(w);
                }
            } else {
                hyperKey.add(lineSaver.get(k)[1]);
            }
            ArrayList<ArrayList<String>> hypoVal = new ArrayList<ArrayList<String>>();
            ArrayList<String> temp = new ArrayList<String>();
            for (Integer index : v) {
                if (lineSaver.get(index)[1].contains(" ")) {
                    String[] words = lineSaver.get(index)[1].split(" ");
                    for (String w : words) {
                        temp.add(w);
                    }
                } else {
                    temp.add(lineSaver.get(index)[1]);
                }
                hypoVal.add(temp);
                wordMap.put(hyperKey, hypoVal);
                temp = new ArrayList<String>();
            }
        }
        /* put all the words from synset into a set: wordSet. */
        In synsetAgain = new In(synsetFilename);
        while (synsetAgain.hasNextLine()) {
            String line = synsetAgain.readLine();
            String[] rawTokens = line.split(",");
            if (rawTokens[1].contains(" ")) {
                String[] words = rawTokens[1].split(" ");
                for (int i = 0; i < words.length; i++) {
                    wordSet.add(words[i]);
                }
            } else {
                String[] words = new String[1];
                words[0] = rawTokens[1];
                for (int i = 0; i < words.length; i++) {
                    wordSet.add(words[i]);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (this.wordSet.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordSet;
    }

    /* return all the hyponyms of words in a synset. */
    private Set<String> hyponymsOfKey(ArrayList<String> keys) {
        Set<String> toBeReturned = new HashSet<String>();
        for (String key : keys) {
            // System.out.println(key);
            toBeReturned.add(key);
        }
        if (wordMap.containsKey(keys)) {
            ArrayList<ArrayList<String>> valsArray = wordMap.get(keys);
            for (ArrayList<String> vals : valsArray) {
                toBeReturned.addAll(hyponymsOfKey(vals));
            }
        }
        return toBeReturned;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> toBeReturned = new HashSet<String>(); // initialize return
                                                          // Set<String>
        Set<ArrayList<String>> keys = wordMap.keySet(); // get the keys from
                                                        // wordMap, assuming the
                                                        // word is in the keySet
        for (ArrayList<String> key : keys) {
            // System.out.println(key);
            if (key.contains(word)) { // check if the key synset contains the
                // word
                toBeReturned.addAll(hyponymsOfKey(key));
            }
        }
        // when word is not in the key set
        if (toBeReturned.size() == 0) {
            for (Map.Entry<ArrayList<String>, ArrayList<ArrayList<String>>> entry : wordMap
                    .entrySet()) {
                ArrayList<ArrayList<String>> valsArray = entry.getValue();
                for (ArrayList<String> vals : valsArray) {
                    if (vals.contains(word)) {
                        for (String val : vals) {
                            toBeReturned.add(val);
                        }
                    }
                }
            }
        }
        return toBeReturned;
    }
}
