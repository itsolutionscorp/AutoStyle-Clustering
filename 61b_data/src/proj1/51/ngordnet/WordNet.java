package ngordnet;

import edu.princeton.cs.introcs.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordNet {
    private Map<String, Set<String>> synsetMap = new HashMap<>();
    private Map<String, Set<String>> hyponymMap = new HashMap<>();
    private Set<String> hyponym = new HashSet<>();

    // private Set<String> a = new HashSet<>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In inS = new In(synsetFilename);
        In inH = new In(hyponymFilename);
        while (!inS.isEmpty()) {
            String line = inS.readLine();
            String[] tempLine = line.split(",");
            String number = tempLine[0];
            String word = tempLine[1];
            String[] testWord = word.split(" ");
            Set<String> sWordSet = new HashSet<>();
            for (int k = 0; k < testWord.length; k += 1) {
                sWordSet.add(testWord[k]);
            }
            synsetMap.put(number, sWordSet);
        }
        while (!inH.isEmpty()) {
            String nextLine = inH.readLine();
            String[] nextLineArray = nextLine.split(",");
            String num = nextLineArray[0];
            Set<String> hyponyms = new HashSet<>();
            for (int i = 1; i < nextLineArray.length; i += 1) {
                hyponyms.add(nextLineArray[i]);
            }
            if (hyponymMap.containsKey(num)) {
                Set<String> a = hyponymMap.get(num);
                for (String ele : a) {
                    hyponyms.add(ele);
                }
            }
            hyponymMap.put(num, hyponyms);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> keys = synsetMap.keySet();
        for (String key : keys) {
            if (synsetMap.get(key).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> noun = new HashSet<>();
        Set<String> keys = synsetMap.keySet();
        for (String key : keys) {
            Set<String> values = synsetMap.get(key);
            for (String word : values) {
                noun.add(word);
            }
        }
        return noun;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> a = new HashSet<>();
        Set<String> groupKey = new HashSet<>();
        Set<String> toBeChanged = new HashSet<>();
        Set<String> keys = synsetMap.keySet();
        Set<String> numKeys = hyponymMap.keySet();
        for (String key : keys) {
            if (synsetMap.get(key).contains(word)) {
                groupKey.add(key);
                toBeChanged.add(key);
                Set<String> values = synsetMap.get(key);
                for (String x : values) {
                    hyponym.add(x);
                }
            }
        }

        for (String iter : groupKey) {
            for (String numKey : numKeys) {
                if (numKey.equals(iter)) {
                    a = hyponymMap.get(numKey);
                    for (String x : a) {
                        Set<String> b = synsetMap.get(x);
                        toBeChanged.add(x);
                        for (String c : b) {
                            hyponym.add(c);
                        }
                    }
                }
            }
        }

        while (groupKey.size() != toBeChanged.size()) {
            for (String u : toBeChanged) {
                groupKey.add(u);
            }
            toBeChanged = helper(groupKey, numKeys, toBeChanged);
        }
        Set<String> temp = new HashSet<>();
        for (String el : hyponym) {
            temp.add(el);
        }
        hyponym.clear();
        return temp;
    }

    private Set<String> helper(Set<String> k, Set<String> l, Set<String> o) {
        for (String ite : k) {
            for (String numKey : l) {
                if (numKey.equals(ite)) {
                    Set<String> d = hyponymMap.get(numKey);
                    for (String y : d) {
                        Set<String> e = synsetMap.get(y);
                        o.add(y);
                        for (String f : e) {
                            hyponym.add(f);
                        }
                    }
                }
            }
        }
        return o;
    }
}
